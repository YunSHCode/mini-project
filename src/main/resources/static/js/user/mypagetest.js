document.addEventListener('DOMContentLoaded', function () {
    const reservationModal = document.getElementById('reservationModal');

    reservationModal.addEventListener('show.bs.modal', function (event) {
        const button = event.relatedTarget; // Modal을 열게 한 버튼
        const reservationJson = button.getAttribute('data-reservation'); // JSON 문자열

        // JSON 문자열을 객체로 변환
        let reservationData;
        try {
            reservationData = JSON.parse(reservationJson);
        } catch (e) {
            console.error("JSON 파싱 오류:", e);
            return; // JSON 파싱에 실패하면 아무것도 하지 않고 종료
        }

        // Modal 내용 업데이트
        document.getElementById('modalCafeName').textContent = reservationData.cafeName || '정보 없음';
        document.getElementById('modalPickupTime').textContent = reservationData.reservationPickupTime || '정보 없음';

        // 메뉴 리스트 업데이트 (menuList가 존재할 때만)
        const menuList = reservationData.menuList;
        const menuListContainer = document.getElementById('modalMenuList');
        menuListContainer.innerHTML = ''; // 기존 메뉴 초기화

        if (menuList && Array.isArray(menuList)) {
            menuList.forEach(menu => {
                const menuItem = document.createElement('li');
                menuItem.textContent = `${menu.menuName} x ${menu.reservationMenuQuantity}`;
                menuListContainer.appendChild(menuItem);
            });
        } else {
            const emptyMessage = document.createElement('li');
            emptyMessage.textContent = '메뉴 정보가 없습니다.';
            menuListContainer.appendChild(emptyMessage);
        }
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
                                <img src="${member.userProfilePictureGenerated}" alt="${member.userNickname}" class="img-thumbnail" style="width: 40px; height: 40px; object-fit: cover;">
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
                                <img src="${member.userProfilePictureGenerated}" alt="${member.userNickname}" class="img-thumbnail" style="width: 40px; height: 40px; object-fit: cover;">
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

    // 전역으로 함수 등록
    window.loadGroupMembers = loadGroupMembers;
    window.removeMember = removeMember;
    window.approveMember = approveMember;
    window.deleteGroup = deleteGroup;
});