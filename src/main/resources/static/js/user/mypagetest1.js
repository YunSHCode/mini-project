$(document).ready(function () {
    // 초기 로드 시 선택된 탭 데이터 로드
    const initialTab = $('.nav-link.active').data('bs-target');
    loadTabData(initialTab);

    // 탭 클릭 이벤트 리스너 추가
    $('.nav-link').on('click', function () {
        const targetTab = $(this).data('bs-target');
        loadTabData(targetTab);
    });

    // 페이지네이션 클릭 처리 함수
    window.loadPage = function (targetPage) {
        const currentTab = $('.nav-link.active').data('bs-target');
        loadTabData(currentTab, targetPage);
    };

    // 탭 데이터 로드 함수
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
                $(`${targetTab}Content`).html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading tab data:", error);
                $(`${targetTab}Content`).html("Failed to load data.");
            }
        });
    }
});