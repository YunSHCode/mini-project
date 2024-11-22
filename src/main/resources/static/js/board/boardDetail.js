document.addEventListener("DOMContentLoaded", function () {
    // 서버에서 가져온 마크다운 데이터 (예제)
    const markdownContent = /*[[${board.boardContent}]]*/'';
    console.log(markdownContent);

    // Toast UI Viewer 초기화
    const viewer = new toastui.Editor.factory({
        el: document.querySelector('#viewer'),
        viewer: true, // Viewer 모드 활성화
        initialValue: markdownContent // 서버에서 받아온 마크다운 데이터
    });
});