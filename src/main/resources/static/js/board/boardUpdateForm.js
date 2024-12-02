document.addEventListener("DOMContentLoaded", function () {
    const editor = new toastui.Editor({
        el: document.querySelector('#editor'),
        height: '700px',
        initialEditType: 'markdown',
        previewStyle: 'vertical',
        placeholder: '글을 입력하세요',
        initialValue: document.querySelector('#boardContent').value, // Hidden input에 전달된 값 로드
        hooks: {
            addImageBlobHook(blob, callback) {
                const validExtensions = ['jpg', 'jpeg', 'png', 'gif', 'webp'];
                const fileName = blob.name;
                const fileExtension = fileName.split('.').pop().toLowerCase();

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
                    success: function (data) {
                        callback(data, '사진 대체 텍스트 입력');
                    },
                    error: function (e) {
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
    });
});
