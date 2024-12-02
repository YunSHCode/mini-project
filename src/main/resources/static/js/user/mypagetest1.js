document.addEventListener('DOMContentLoaded', function () {
    // 모든 페이지 링크에 클릭 이벤트 추가
    addPaginationListeners();

    function addPaginationListeners() {
        document.querySelectorAll('.pagination a.page-link').forEach(pageLink => {
            pageLink.addEventListener('click', function (event) {
                event.preventDefault(); // 기본 이동 동작 방지
                const targetPage = this.getAttribute('data-page');
                const currentTab = document.querySelector('.nav-link.active').getAttribute('data-bs-target');

                loadTabData(currentTab, targetPage);
            });
        });
    }

    function loadTabData(targetTab, page = 0) {
        let url = '';
        switch (targetTab) {
            case '#posts':
                url = `/user/mypage/posts?page=${page}`;
                break;
            case '#reservations':
                url = `/user/mypage/reservations?page=${page}`;
                break;
            case '#myGroups':
                url = `/user/mypage/my-groups?page=${page}`;
                break;
            case '#myParticipatedGroups':
                url = `/user/mypage/participated-groups?page=${page}`;
                break;
            case '#myPendingGroups':
                url = `/user/mypage/pending-groups?page=${page}`;
                break;
            default:
                return;
        }

        $.ajax({
            url: url,
            type: "GET",
            success: function (data) {
                $(targetTab + 'Content').html(data);
                addPaginationListeners(); // 새로운 페이지네이션 링크에 이벤트 추가
            },
            error: function (xhr, status, error) {
                console.error("Error loading tab data:", error);
                $(targetTab + 'Content').html("Failed to load data.");
            }
        });
    }

    // 초기 로드 시 선택된 탭 데이터 로드
    const initialTab = document.querySelector('.nav-link.active').getAttribute('data-bs-target');
    loadTabData(initialTab);
});
