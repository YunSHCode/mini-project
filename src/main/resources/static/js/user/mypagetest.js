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
                data.currentMembers.forEach((member, index) => {
                    currentMembersTable.append(`
                        <tr>
                            <td>${index + 1}</td>
                            <td>${member.name}</td>
                            <td>${member.email}</td>
                            <td>${member.phone}</td>
                            <td>
                                <button class="btn btn-sm btn-danger" onclick="removeMember(${communityId}, ${member.userKey})">추방</button>
                            </td>
                        </tr>
                    `);
                });

                // 신청 중인 인원 목록 채우기
                pendingMembersTable.empty();
                data.pendingMembers.forEach((member, index) => {
                    pendingMembersTable.append(`
                        <tr>
                            <td>${index + 1}</td>
                            <td>${member.name}</td>
                            <td>${member.email}</td>
                            <td>${member.phone}</td>
                            <td>
                                <button class="btn btn-sm btn-success" onclick="approveMember(${communityId}, ${member.userKey})">승인</button>
                            </td>
                        </tr>
                    `);
                });
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
    function removeMember(communityId, memberId) {
        if (!confirm("정말로 추방하시겠습니까?")) return;

        $.ajax({
            url: `/group/${communityId}/member/${memberId}/remove`,
            type: "DELETE",
            success: function () {
                alert("인원이 추방되었습니다.");
                loadGroupMembers(communityId); // 목록 갱신
            },
            error: function (xhr, status, error) {
                alert("추방에 실패했습니다.");
                console.error(error);
            }
        });
    }

    // 특정 신청 인원을 승인
    function approveMember(communityId, memberId) {
        $.ajax({
            url: `/group/${communityId}/member/${memberId}/approve`,
            type: "POST",
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
            type: "DELETE",
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