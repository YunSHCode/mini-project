document.addEventListener("DOMContentLoaded", function () {
    // Initialize Toast UI Editor
    const editor = new toastui.Editor({
        el: document.querySelector('#editor'), // Editor container
        height: '400px', // Editor height
        initialEditType: 'markdown', // Initial editor type (markdown or wysiwyg)
        previewStyle: 'vertical', // Split view (editor & preview)
        placeholder: '글을 입력하세요',
        /* start of hooks */
        hooks: {
            addImageBlobHook(blob, callback) {  // 이미지 업로드 로직 커스텀
                console.log(blob);
                console.log(callback);
            }
        }
        /* end of hooks */
    });

    // Sync editor content with hidden input on form submit
    document.querySelector('#boardForm').addEventListener('submit', function () {
        const content = editor.getMarkdown(); // Get Markdown content
        document.querySelector('#boardContent').value = content;
    });
});