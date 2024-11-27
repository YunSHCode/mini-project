$(document).ready(function () {
    const boardId = $('#boardData').data('board-id');

    //댓글 목록 가져오기
    function loadComments() {
        $.ajax({
            url: '/comment/list',
            type: 'GET',
            data: { boardId: boardId },
            success: function (response) {
                // 댓글 목록 렌더링
                const commentList = $('.comment-list');
                commentList.empty();
                response.forEach(comment => {
                    let indentClass = '';
                    if (comment.commentStep > 0) {
                        indentClass = 'comment-reply'; // 답글일 경우 들여쓰기 스타일 추가
                    }
                    const newComment = `
    <div class="comment-item p-3 mb-3 bg-white rounded ${indentClass}">
        <div class="comment-header d-flex justify-conten t-between align-items-center">
            <div class="comment-author">
                <strong>${comment.userRealname}</strong>
                <span class="text-muted">${comment.commentCreateDt}</span>
            </div>
            <button class="btn btn-sm btn-outline-primary reply-button" data-comment-id="${comment.commentId}">
                답글
            </button>
        </div>
        
        <div class="comment-body mt-2">
            <p>${comment.commentContent}</p>
        </div>
    </div>`;
                    commentList.append(newComment);
                });
            },
            error: function () {
                alert('댓글 목록을 불러오는 데 실패했습니다.');
            }
        });
    }

    //페이지 로딩시 댓글 로드
    loadComments();

    // 댓글 작성 버튼 클릭시
    $('#submitComment').click(function () {
        const boardDataElement = document.getElementById('boardData');
        const boardId = boardDataElement.dataset.boardId;
        const commentContent = $('#commentContent').val();

        if (commentContent.trim() === '') {
            alert('댓글 내용을 입력하세요.');
            return;
        }

        $.ajax({
            url: '/comment/write',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                boardId: boardId,
                commentContent: commentContent
            }),
            success: function (response) {
                // 댓글 목록에 새로운 댓글 추가
                $('#commentContent').val('');
                loadComments();
                // const newComment = `
                //                     <div class="comment-item p-3 mb-3 bg-white rounded">
                //                         <p class="mb-1">
                //                             <strong>${response.userName}</strong>
                //                             <span class="text-muted">${response.commentCreateDt}</span>
                //                         </p>
                //                         <p>${response.commentContent}</p>
                //                     </div>`;
                // $('#comment-list').append(newComment);
                // $('#commentContent').val('');
            },
            error: function () {
                alert('댓글 작성에 실패했습니다. 다시 시도해주세요.');
            }
        });
    });

    // 답글 버튼 클릭 이벤트
    $(document).on('click', '.reply-button', function () {
        const commentId = $(this).data('comment-id');
        console.log(commentId);
        const replyForm = `
        <div class="reply-form mt-2">
            <textarea class="form-control reply-content" rows="2" placeholder="답글을 입력하세요..."></textarea>
            <button class="btn btn-primary btn-sm mt-1 submit-reply" data-parent-id="${commentId}">답글 작성</button>
            <button class="btn btn-secondary btn-sm mt-1 cancel-reply">작성 취소</button>
        </div>`;

        // 답글 버튼을 숨기고, 답글 폼을 추가
        const commentItem = $(this).closest('.comment-item'); // 해당 댓글의 comment-item 요소 선택
        const replyButton = $(this).detach(); // reply-button을 임시로 분리
        commentItem.append(replyForm); // 해당 댓글 요소 아래에 답글 폼 추가
        commentItem.data('replyButton', replyButton); // 분리한 버튼을 comment-item에 저장
    });

    // 답글 작성 취소 버튼 클릭 시
    $(document).on('click', '.cancel-reply', function () {
        // 작성 취소 버튼을 누르면 답글 폼을 제거하고, 원래 있던 위치에 답글 버튼을 다시 추가
        const commentItem = $(this).closest('.comment-item');
        $(this).closest('.reply-form').remove(); // 답글 폼을 삭제
        const replyButton = commentItem.data('replyButton'); // 이전에 저장한 답글 버튼을 가져옴
        commentItem.find('.comment-header').append(replyButton); // 댓글 헤더 부분에 답글 버튼을 다시 추가
    });

    // 답글 작성 버튼 클릭 시
    $(document).on('click', '.submit-reply', function () {
        const parentId = $(this).data('parent-id');
        const replyContent = $(this).siblings('.reply-content').val();
        console.log(parentId);
        if (replyContent.trim() === '') {
            alert('답글 내용을 입력하세요.');
            return;
        }

        $.ajax({
            url: '/comment/write',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                boardId: boardId,
                commentContent: replyContent,
                commentGroup: parentId, // 부모 댓글 ID를 그룹 ID로 설정
                commentStep: 1             // 답글의 경우 step 증가
            }),
            success: function () {
                loadComments(); // 답글 작성 후 목록 다시 불러오기
            },
            error: function () {
                alert('답글 작성에 실패했습니다. 다시 시도해주세요.');
            }
        });
    });
});