function updatePreview(event) {
    const input = event.target;
    const previewImage = document.getElementById('previewImage');

    // Check if a file is selected
    if (input.files && input.files[0]) {
        const reader = new FileReader();

        reader.onload = function (e) {
            previewImage.src = e.target.result; // Update the image source
        };

        reader.readAsDataURL(input.files[0]); // Read the file content
    }
}
