document.addEventListener('DOMContentLoaded', function () {
    const reservationModal = document.getElementById('reservationModal');
    reservationModal.addEventListener('show.bs.modal', function (event) {
        const currentReservationId = $(this).data('reservation-id');
        // 버튼 클릭 이벤트 설정
        $('[data-reservation-id]').on('click', function () {
            const reservationId = $(this).data('reservation-id');
            $('#cancelReservationBtn').data('reservation-id', reservationId);
            // AJAX 요청
            $.ajax({
                url: `/user/mypage/reservationResult/${reservationId}`, // 백엔드 엔드포인트
                type: 'GET',
                dataType: 'json',
                success: function (data) {
                    // 모달에 데이터 채우기
                    $('#modalCafeName').text(data.cafeName);
                    $('#modalPickupTime').text(formatDateTime(data.reservationPickupTime));
                    // 메뉴 리스트 HTML 생성
                    const menuHtml = data.menuList.map(menu => `
        <li>
            <img src="/images/menu/${menu.menuPictureGenerated}" alt="${menu.menuName}" style="width:50px;height:50px;border-radius:5px; margin-right:10px;"/>
            <span>${menu.menuName} - ${menu.menuPrice}원 (${menu.reservationMenuQuantity}개)</span>
        </li>
    `).join('');

                    $('#modalMenuList').html(menuHtml); // 모달의 ul 요소에 추가
                },
                error: function (xhr, status, error) {
                    console.error('Error fetching reservation details:', error);
                    $('#modalCafeName').text('데이터를 불러올 수 없습니다.');
                    $('#modalPickupTime').text('');
                    $('#modalMenu').text('');
                }
            });
        });

        //예약 취소 버튼 클릭 이벤트
        $('#cancelReservationBtn').on('click', function () {
            const reservationId = $(this).data('reservation-id');
            if (confirm('정말 예약을 취소하시겠습니까?')) {
                // AJAX 요청으로 예약 취소 처리
                $.ajax({
                    url: `/cafe/cancelReservation/${reservationId}`, // 예약 취소 엔드포인트
                    type: 'POST',
                    success: function () {
                        alert('예약이 성공적으로 취소되었습니다.');

                        // 모달 초기화 및 닫기
                        $('#modalCafeName').text('');
                        $('#modalPickupTime').text('');
                        $('#modalMenuList').empty();
                        $('#reservationModal').modal('hide');
                        location.reload();
                    },
                    error: function (xhr, status, error) {
                        console.error('Error canceling reservation:', error);
                        alert('예약 취소 중 문제가 발생했습니다.');
                    }
                });
            }
        });
    });
    // 인원 관리 모달에서 참여/신청 인원 로드
    function loadGroupMembers(communityId) {
        $.ajax({
            url: `/group/${communityId}/members`,
            type: "GET",
            dataType: "json",
            success: function (data) {
                const currentMembersTable = $("#currentMembers");
                const pendingMembersTable = $("#pendingMembers");

                // 참여 중인 인원 목록 채우기
                currentMembersTable.empty();
                if (data.currentMembers.length === 0) {
                    currentMembersTable.append(`<tr><td colspan="4">참여 중인 멤버가 없습니다.</td></tr>`);
                } else {
                    data.currentMembers.forEach((member) => {
                        currentMembersTable.append(`
                        <tr>
                            <td>
                                <img src="'/upload/user/' + ${member.userProfilePictureGenerated}" alt="${member.userNickname}" class="img-thumbnail" style="width: 40px; height: 40px; object-fit: cover;">
                            </td>
                            <td>${member.userNickname}</td>
                            <td>${member.userPhoneNumber || "전화번호 없음"}</td>
                            <td>
                                ${member.memberStatus === '개설자'
                            ? '<span class="badge bg-primary">Admin</span>'
                            : `<td><button class="btn btn-sm btn-danger" onclick="removeMember(${communityId}, ${member.userKey}, true)">추방</button></td>`}
                            </td>
                        </tr>
                    `);
                    });
                }

                // 신청 중인 인원 목록 채우기
                pendingMembersTable.empty();
                if (data.pendingMembers.length === 0) {
                    pendingMembersTable.append(`<tr><td colspan="4">신청 중인 멤버가 없습니다.</td></tr>`);
                } else {
                    data.pendingMembers.forEach((member) => {
                        pendingMembersTable.append(`
                        <tr>
                            <td>
                                <img src="'/upload/user/' + ${member.userProfilePictureGenerated}" alt="${member.userNickname}" class="img-thumbnail" style="width: 40px; height: 40px; object-fit: cover;">
                            </td>
                            <td>${member.userNickname}</td>
                            <td>${member.userPhoneNumber || "전화번호 없음"}</td>
                            <td style="white-space: nowrap;">                            
                                <button class="btn btn-sm btn-success" style="display: inline-block;" onclick="approveMember(${communityId}, ${member.userKey})">승인</button>
                                <button class="btn btn-sm btn-danger" style="display: inline-block;" onclick="removeMember(${communityId}, ${member.userKey}, false)">거절</button>                   
                            </td>
                        </tr>
                    `);
                    });
                }
            },
            error: function (xhr, status, error) {
                alert("인원 정보를 불러오는데 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 모달이 열릴 때 인원 정보를 로드
    $('#manageMembersModal').on('show.bs.modal', function (event) {
        const button = $(event.relatedTarget); // 인원 관리 버튼
        const communityId = button.data('group-id'); // 그룹 ID 가져오기
        loadGroupMembers(communityId);
    });

    // 특정 인원을 추방
    function removeMember(communityId, memberId, isExpelled) {
        const actionMessage = isExpelled ? "정말로 추방하시겠습니까?" : "정말로 신청을 거절하시겠습니까?";
        const successMessage = isExpelled ? "추방이 완료되었습니다." : "신청이 거절되었습니다.";

        if (!confirm(actionMessage)) return;

        $.ajax({
            url: `/group/removemember`,
            type: "POST",
            data:{ communityId: communityId, userKey: memberId, isExpelled: isExpelled }, // 데이터를 JSON 형식으로 변환
            success: function () {
                alert(successMessage);
                loadGroupMembers(communityId); // 목록 갱신
                // 모임 인원 수 조정 (화면에서 바로 수정)
                if (isExpelled) {
                    const memberCountElement = document.getElementById(`communityMemberCount-${communityId}`);
                    if (memberCountElement) {
                        let [currentCount, maxCount] = memberCountElement.textContent.split(' / ').map(Number);
                        currentCount = Math.max(currentCount - 1, 0);
                        memberCountElement.textContent = `${currentCount} / ${maxCount}`;
                    }
                }
            },
            error: function (xhr, status, error) {
                alert("작업에 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 멤버 탈퇴 및 신청 취소
    function cancelMember(communityId, status) {
        const actionMessage = status === '탈퇴' ? "정말로 탈퇴하시겠습니까?" : "정말로 신청을 취소하시겠습니까?";
        const successMessage = status === '탈퇴' ? "탈퇴가 완료되었습니다." : "신청이 취소되었습니다.";

        if (!confirm(actionMessage)) return;

        $.ajax({
            url: `/group/removemember`,
            type: "POST",
            data: { communityId: communityId },
            success: function () {
                alert(successMessage);
                window.location.href = '/user/mypage'; //다시 마이페이지로
            },
            error: function (xhr, status, error) {
                alert("작업에 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 특정 신청 인원을 승인
    function approveMember(communityId, memberId) {
        console.log("Approve Member - communityId:", communityId, "userKey:", memberId);
        $.ajax({
            url: `/group/approve`, // 경로를 간단히 변경
            type: "POST",
            data: { communityId: communityId, userKey: memberId }, // 데이터를 JSON으로 변환하여 전달
            success: function () {
                alert("인원이 승인되었습니다.");
                loadGroupMembers(communityId); // 목록 갱신
            },
            error: function (xhr, status, error) {
                alert("승인에 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 모임 삭제
    function deleteGroup(communityId) {
        if (!confirm("정말로 삭제하시겠습니까?")) return;

        $.ajax({
            url: `/group/delete/${communityId}`,
            type: "POST",
            success: function () {
                alert("모임이 삭제되었습니다.");
                location.reload(); // 페이지 갱신
            },
            error: function (xhr, status, error) {
                alert("삭제에 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 날짜 변환 함수
    function formatDateTime(dateTime) {
        const date = new Date(dateTime);
        const year = date.getFullYear();
        const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1
        const day = String(date.getDate()).padStart(2, '0');
        const hours = String(date.getHours()).padStart(2, '0');
        const minutes = String(date.getMinutes()).padStart(2, '0');
        return `${year}-${month}-${day} ${hours}:${minutes}`;
    }
    // 전역으로 함수 등록
    window.loadGroupMembers = loadGroupMembers;
    window.removeMember = removeMember;
    window.approveMember = approveMember;
    window.deleteGroup = deleteGroup;
});