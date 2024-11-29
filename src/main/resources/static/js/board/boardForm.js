document.addEventListener("DOMContentLoaded", function () {
    // Initialize Toast UI Editor
    const editor = new toastui.Editor({
        el: document.querySelector('#editor'), // Editor container
        height: '700px', // Editor height
        initialEditType: 'markdown', // Initial editor type (markdown or wysiwyg)
        previewStyle: 'vertical', // Split view (editor & preview)
        placeholder: '글을 입력하세요',
        /* start of hooks */
        hooks: {
            addImageBlobHook(blob, callback) {  // 이미지 업로드 로직 커스텀

                const validExtensions = ['jpg', 'jpeg', 'png', 'gif', 'webp']; // 허용 확장자 목록

                // 파일 확장자 검사
                const fileName = blob.name; // Blob 객체에서 파일명 추출
                const fileExtension = fileName.split('.').pop().toLowerCase(); // 확장자 추출 및 소문자로 변환

                if (!validExtensions.includes(fileExtension)) {
                    alert('업로드 가능한 파일 형식은 jpg, jpeg, png, gif, webp 입니다.');
                    return;
                }
                const formData = new FormData();
                formData.append('image', blob);

                $.ajax({
                    type: 'POST',
                    enctype: 'multipart/form-data',
                    url: '/tui_editor/image-upload',
                    data: formData,
                    dataType: 'text',
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function(data) {
                        //console.log('ajax 이미지 업로드 성공');
                        let imgUrl = `/tui_editor/image-print?filename=${data.filename}`;
                        // callback : 에디터(마크다운 편집기)에 표시할 텍스트, 뷰어에는 imageUrl 주소에 저장된 사진으로 나옴
                        // 형식 : ![대체 텍스트](주소)
                        console.log(data);
                        callback(data, '사진 대체 텍스트 입력');
                    },
                    error: function(e) {
                        //console.log('ajax 이미지 업로드 실패');
                        //console.log(e.abort([statusText]));

                        callback('image_load_fail', '사진 대체 텍스트 입력');
                    }
                });
            }
        }
    });

    // Sync editor content with hidden input on form submit
    document.querySelector('#boardForm').addEventListener('submit', function () {
        const content = editor.getMarkdown(); // Get Markdown content
        if (!content.trim()) { // Check if content is empty or whitespace
            event.preventDefault(); // Prevent form submission
            alert('내용을 입력해주세요!'); // Notify user
            return;
        }
        document.querySelector('#boardContent').value = content;
        // 게시판 상태 초기화
        sessionStorage.removeItem('boardCurrentPage');
        sessionStorage.removeItem('boardCurrentCategory');
        sessionStorage.removeItem('boardCurrentKeyword');
    });
});