document.addEventListener("DOMContentLoaded", function () {
    // 메뉴 수량 변경 함수
    window.updateQuantity = function (menuId, change) {
        const input = document.getElementById(`quantity-${menuId}`);
        if (!input) {
            console.error(`Input field not found for menuId: ${menuId}`);
            return;
        }

        let quantity = parseInt(input.value || 0) + change;

        if (quantity < 0) quantity = 0;

        input.value = quantity;

        let hiddenInput = document.getElementById(`hidden-${menuId}`);
        if (quantity > 0) {
            if (!hiddenInput) {
                hiddenInput = document.createElement('input');
                hiddenInput.type = 'hidden';
                hiddenInput.id = `hidden-${menuId}`;
                hiddenInput.name = `menuItems[${menuId}]`;
                hiddenInput.value = quantity;
                document.getElementById('menuList').appendChild(hiddenInput);
            } else {
                hiddenInput.value = quantity;
            }
        } else {
            if (hiddenInput) hiddenInput.remove();
        }

        // 총 금액 업데이트 호출
        updateTotalPrice();
    };

    // 총 금액 계산 함수
    function updateTotalPrice() {
        let total = 0;

        // 모든 메뉴의 수량과 가격을 가져와 총 금액 계산
        document.querySelectorAll("div.menu-item").forEach(menuItem => {
            const quantityInput = menuItem.querySelector("input[id^='quantity-']");
            const quantity = parseInt(quantityInput.value) || 0;

            const priceSpan = menuItem.querySelector("p span");
            const price = parseInt(priceSpan.textContent) || 0;

            total += quantity * price;
        });

        // 총 금액 업데이트
        const totalPriceElement = document.getElementById("totalPrice");
        if (totalPriceElement) {
            totalPriceElement.textContent = total.toLocaleString(); // 천 단위 콤마 추가
        }
    }

    // 모달 관련 요소
    const modalCustomerName = document.getElementById("modalCustomerName");
    const modalCustomerPhone = document.getElementById("modalCustomerPhone");
    const modalMenuList = document.getElementById("modalMenuList");
    const modalTotalPrice = document.getElementById("modalTotalPrice");

    // 예약하기 버튼 클릭 시 모달 창 데이터 업데이트
    document.getElementById("reservationForm").addEventListener("submit", function (event) {
        event.preventDefault(); // 기본 폼 제출 동작 방지

        // 수령 시간 확인
        const pickupTime = document.getElementById("pickupTime").value; // 수령 시간 값
        if (!pickupTime) {
            alert("수령 시간을 선택해주세요.");
            return; // 수령 시간이 선택되지 않았다면 종료
        }

        // 메뉴 선택 확인
        let menuSelected = false; // 메뉴 선택 여부 플래그
        document.querySelectorAll("div.menu-item").forEach(menuItem => {
            const quantity = menuItem.querySelector("input[id^='quantity-']").value; // 각 메뉴의 수량 확인
            if (parseInt(quantity) > 0) {
                menuSelected = true; // 수량이 0보다 크면 메뉴가 선택된 것으로 판단
            }
        });

        // 예약자 정보 업데이트
        modalCustomerName.textContent = document.getElementById("customerName").textContent;
        modalCustomerPhone.textContent = document.getElementById("customerPhone").textContent;


        // 선택한 메뉴와 수량 업데이트
        modalMenuList.innerHTML = ""; // 기존 데이터 초기화
        document.querySelectorAll("div.menu-item").forEach(menuItem => {
            const menuName = menuItem.querySelector("h6").textContent;
            const quantity = menuItem.querySelector("input[id^='quantity-']").value;
            if (parseInt(quantity) > 0) {
                const price = menuItem.querySelector("p span").textContent;
                modalMenuList.innerHTML += `
                    <p>${menuName} x ${quantity}개 (${price}원)</p>
                `;
            }
        });

        // 총 금액 업데이트
        modalTotalPrice.textContent = document.getElementById("totalPrice").textContent;

        // 모달 창 표시
        const reservationModal = new bootstrap.Modal(document.getElementById("reservationModal"));
        reservationModal.show();
    });

    // 예약 확인 버튼 클릭 시
    document.getElementById("confirmReservation").addEventListener("click", function () {
        // 1. 유저 정보 및 카페 정보 가져오기
        const userKey = document.querySelector("input[name='userKey']").value;
        const urlPath = window.location.pathname;
        const cafeId = urlPath.split('/')[3]; // URL의 두 번째 슬래시 뒤에 있는 값

        const pickupTime = document.getElementById("pickupTime").value;

        if (!pickupTime) {
            alert("수령 시간을 선택해주세요.");
            return;
        }

        // 2. 메뉴 데이터 가져오기
        const menuData = [];
        document.querySelectorAll(".menu-item").forEach(item => {
            const menuId = item.querySelector("button").getAttribute("onclick").match(/\d+/)[0];
            const quantity = item.querySelector("input").value;

            if (parseInt(quantity) > 0) {
                menuData.push({ menuId: parseInt(menuId), quantity: parseInt(quantity) });
            }
        });

        if (menuData.length === 0) {
            alert("메뉴를 선택해주세요.");
            return;
        }

        // 3. 서버로 전송할 데이터 구성
        const requestData = {
            userKey: userKey,
            cafeId: cafeId,
            pickupTime: pickupTime,
            menuData: menuData
        };

        // 4. Ajax 요청
        $.ajax({
            url: '/cafe/reservation.do',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(requestData),
            success: function (response) {
                alert(response.message);
                window.location.href = response.redirectUrl;
            },
            error: function (xhr, status, error) {
                console.error("Error:", error);
                alert("예약 중 오류가 발생했습니다.");
            }
        });
    });
});
