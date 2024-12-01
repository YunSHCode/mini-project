function loadMembers(groupId) {
    // 멤버 정보를 비동기로 가져옴
    $.ajax({
        url: `/group/members/${groupId}`, // 요청 URL
        method: 'GET', // HTTP 메서드
        dataType: 'json', // 응답 데이터 형식
        success: function (data) {
            console.log("멤버 데이터 로드 성공:", data); // 응답 데이터 로그 출력
            const memberList = $('#memberList'); // jQuery로 요소 선택
            memberList.empty(); // 기존 내용을 초기화

            data.forEach(function (member) {
                const li = $('<li></li>') // 새로운 <li> 요소 생성
                    .addClass('list-group-item d-flex align-items-center'); // Flexbox로 정렬

                // 프로필 이미지 태그 생성
                const img = $('<img>')
                    .addClass('rounded-circle me-3') // 원형 이미지 및 오른쪽 여백 추가
                    .attr('src', `/upload/user/${member.userProfilePictureGenerated}`) // 이미지 경로
                    .attr('alt', `${member.userNickname} 프로필 사진`)
                    .css({
                        width: '40px',
                        height: '40px',
                        objectFit: 'cover'
                    });

                // 닉네임 텍스트 노드 생성
                const span = $('<span></span>').text(member.userNickname);

                // <li>에 이미지와 닉네임 추가
                li.append(img);
                li.append(span);

                // <ul>에 <li> 추가
                memberList.append(li);
            });
        },
        error: function (xhr, status, error) {
            console.error('멤버 정보를 가져오는 중 오류 발생:', error);
            console.error('상태 코드:', xhr.status);
            console.error('응답 텍스트:', xhr.responseText);
        }
    });
}

document.getElementById('joinButton').addEventListener('click', function (event) {
    event.preventDefault(); // 기본 동작 막기

    // 확인창 표시
    const confirmJoin = confirm('정말 해당 모임에 참여하시겠습니까?');
    if (!confirmJoin) {
        return; // 사용자가 취소를 누르면 아무 동작도 하지 않음
    }

    const communityId = this.getAttribute('href').split('/').pop(); // URL에서 communityId 추출

    // AJAX 요청
    $.ajax({
        url: `/group/join/${communityId}`, // 요청 URL
        type: 'POST', // HTTP 메서드
        contentType: 'application/json', // 요청 헤더 설정
        success: function () {
            alert('참여 요청이 성공적으로 등록되었습니다!\n관리자가 수락 시 해당 모임에 참여됩니다.');
            location.reload(); // 페이지 새로고침
        },
        error: function (xhr) {
            if (xhr.status === 401) {
                alert('로그인이 필요합니다. 로그인 페이지로 이동합니다.');
                window.location.href = '/login'; // 로그인 페이지로 이동
            } else {
                alert(xhr.responseText || '참여 요청 처리 중 오류가 발생했습니다.');
            }
        }
    });
});
