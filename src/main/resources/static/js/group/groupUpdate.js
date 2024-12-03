document.addEventListener("DOMContentLoaded", function () {
    // 초기 폼 필드의 값을 저장
    const initialFormState = {
        communityTitle: document.getElementById("communityTitle").value,
        communityContents: document.getElementById("communityContents").value,
        communityMemberMax: document.getElementById("communityMemberMax").value,
        communityPicture: document.getElementById("communityPicture").value
    };

    // 수정 버튼 클릭 시 수정 여부 확인
    document.getElementById("editCommunityForm").addEventListener("submit", function (event) {
        // 현재 폼 필드의 값을 가져오기
        const currentFormState = {
            communityTitle: document.getElementById("communityTitle").value,
            communityContents: document.getElementById("communityContents").value,
            communityMemberMax: document.getElementById("communityMemberMax").value,
            communityPicture: document.getElementById("communityPicture").value
        };

        // 초기 값과 현재 값을 비교하여 변경 여부 확인
        const isModified =
            currentFormState.communityTitle !== initialFormState.communityTitle ||
            currentFormState.communityContents !== initialFormState.communityContents ||
            currentFormState.communityMemberMax !== initialFormState.communityMemberMax ||
            currentFormState.communityPicture !== ""; // 이미지 변경 시에는 파일 업로드 요소가 비어있지 않음

        if (!isModified) {
            // 수정된 내용이 없으면 경고 메시지를 표시하고 제출을 막음
            alert("수정된 내용이 없습니다.");
            event.preventDefault();
        }
    });
});

function updatePreview(event) {
    // 이벤트가 발생한 input 요소를 가져옵니다.
    const input = event.target;
    const file = input.files[0]; // 선택한 파일을 가져옵니다.

    if (file) {
        const reader = new FileReader();

        // 파일 읽기가 완료되었을 때 호출되는 함수
        reader.onload = function(e) {
            // 미리보기 이미지를 설정합니다.
            const previewImage = document.getElementById("previewImage");
            previewImage.src = e.target.result; // 읽은 파일의 데이터 URL을 이미지 src에 설정
        }

        // 선택한 파일을 읽습니다. 파일이 이미지이므로 Data URL로 읽습니다.
        reader.readAsDataURL(file);
    }
}