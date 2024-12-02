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
});