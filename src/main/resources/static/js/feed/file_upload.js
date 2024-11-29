let selectImages = [];
let currentIndex = 0;

function filePreview(input) {
    const filePreviewArea = document.getElementById("file_preview_area");
    const fileCount = document.getElementById("img_count");

    // 입력된 파일 리스트 가져오기
    const files = input.files;

    // 최대 파일 업로드 제한
    if (files.length + selectImages.length > 5) {
        alert("최대 5개의 파일만 업로드할 수 있습니다.");
        return;
    }

    for (let i = 0; i < files.length; i++) {
        const file = files[i];

        // 중복 파일 방지
        if (selectImages.some(image => image.name === file.name)) {
            alert(`파일 "${file.name}"은(는) 이미 선택되었습니다.`);
            continue;
        }

        selectImages.push(file);

        // 리스트 아이템 생성
        const listItem = document.createElement("li");
        const img = document.createElement("img");
        const deleteButton = document.createElement("button");

        // 이미지 미리보기 생성
        const imgURL = URL.createObjectURL(file);
        img.src = imgURL;
        img.alt = file.name;

        // 삭제 버튼 생성
        deleteButton.innerHTML = '<i class="material-icons">delete</i>';
        deleteButton.onclick = () => {
            // 파일 삭제 로직
            selectImages = selectImages.filter(image => image.name !== file.name);
            filePreviewArea.removeChild(listItem);
            URL.revokeObjectURL(imgURL); // 메모리 누수 방지

            // 슬라이드 인덱스 조정
            if (currentIndex >= selectImages.length && currentIndex > 0) {
                currentIndex--;
            }

            // 파일 개수 업데이트
            updateFileCount();
            updateSlide();
        };

        // 리스트 아이템 구성
        listItem.appendChild(img);
        listItem.appendChild(deleteButton);
        listItem.style.display = "none"; // 초기에는 숨기기

        // 미리보기 영역에 추가
        filePreviewArea.appendChild(listItem);
    }

    // 파일 개수 및 슬라이드 업데이트
    updateFileCount();
    updateSlide();
}

// 파일 개수 업데이트 함수
function updateFileCount() {
    const fileCount = document.getElementById("img_count");
    fileCount.textContent = selectImages.length;

}

// 슬라이드 업데이트 함수
function updateSlide() {
    const filePreviewArea = document.getElementById("file_preview_area");
    const items = filePreviewArea.querySelectorAll("li");

    items.forEach((item, index) => {
        item.style.display = index === currentIndex ? "block" : "none";
    });
}

// Prev 버튼 동작
document.getElementById("btn_prev").addEventListener("click", () => {
    if (currentIndex > 0) {
        currentIndex--;
        updateSlide();
    }
});

// Next 버튼 동작
document.getElementById("btn_next").addEventListener("click", () => {
    if (currentIndex < selectImages.length - 1) {
        currentIndex++;
        updateSlide();
    }
});
