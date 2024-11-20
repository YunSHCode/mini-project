let currentCategory = '';
let currentKeyword = '';
/**
 * 게시글 데이터를 가져와 테이블에 렌더링합니다.
 * @param {number} page - 페이지 번호
 * @param {string} category - 검색 카테고리
 * @param {string} keyword - 검색 키워드
 */
function loadBoards(page = 1, category = '', keyword = '') {
    $.ajax({
        url: '/board/board.do',
        type: 'GET',
        data: { page, searchCategory: category, searchKeyword: keyword },
        dataType: 'json',
        success: function (data) {
            renderTableBody(data.postList); // 테이블 데이터 렌더링

        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

/**
 * 서버에서 받은 데이터를 테이블의 tbody에 렌더링합니다.
 * @param {Array} boards - 게시글 데이터 배열
 */
function renderTableBody(boards) {
    const tbody = document.getElementById('boardTableBody');
    tbody.innerHTML = ''; // 기존 내용 비우기

    if (boards.length === 0) {
        tbody.innerHTML = `<tr><td colspan="3" class="text-center">게시글이 없습니다.</td></tr>`;
        return;
    }

    boards.forEach(board => {
        const row = document.createElement('tr');

        // 게시글 제목 (상세 페이지 링크 포함)
        const titleCell = document.createElement('td');
        titleCell.innerHTML = `<a href="/board/${board.boardId}">${board.boardTitle}</a>`;
        row.appendChild(titleCell);

        // 사용자 ID
        const userCell = document.createElement('td');
        userCell.textContent = board.userId;
        row.appendChild(userCell);

        // 작성일
        const dateCell = document.createElement('td');
        dateCell.textContent = formatDate(board.boardCreateDt); // 날짜 포맷 함수 사용
        row.appendChild(dateCell);

        // 행 추가
        tbody.appendChild(row);
    });
}

function renderPagination(pageDTO) {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = ''; // 기존 페이징 버튼 비우기

    // 이전 버튼
    if (pageDTO.prev) {
        const prevButton = document.createElement('button');
        prevButton.textContent = '이전';
        prevButton.classList.add('btn', 'btn-primary', 'me-2');
        prevButton.onclick = () => loadBoards(pageDTO.pageIndex - 1, currentCategory, currentKeyword);
        pagination.appendChild(prevButton);
    }

    // 페이지 번호 버튼
    for (let i = pageDTO.startPage; i <= pageDTO.endPage; i++) {
        const pageButton = document.createElement('button');
        pageButton.textContent = i;
        pageButton.classList.add('btn', 'btn-secondary', i === pageDTO.pageIndex ? 'active' : '');
        pageButton.onclick = () => loadBoards(i, currentCategory, currentKeyword);
        pagination.appendChild(pageButton);
    }

    // 다음 버튼
    if (pageDTO.next) {
        const nextButton = document.createElement('button');
        nextButton.textContent = '다음';
        nextButton.classList.add('btn', 'btn-primary', 'ms-2');
        nextButton.onclick = () => loadBoards(pageDTO.pageIndex + 1, currentCategory, currentKeyword);
        pagination.appendChild(nextButton);
    }
}


function searchBoard() {
    const category = document.getElementById('searchCategory').value;
    const keyword = document.getElementById('searchKeyword').value.trim();

    if (!keyword) {
        alert('검색어를 입력하세요.');
        return;
    }

    // 검색 상태 업데이트
    currentCategory = category;
    currentKeyword = keyword;

    // 검색 결과 로드
    loadBoards(1, category, keyword);
}

/**
 * 날짜 형식을 YYYY-MM-DD로 포맷합니다.
 * @param {string} dateStr - ISO 형식의 날짜 문자열
 * @returns {string} 포맷된 날짜 문자열
 */
function formatDate(dateStr) {
    const date = new Date(dateStr);
    const now = new Date();

    // 날짜 비교를 위해 년, 월, 일을 가져옵니다.
    const isToday =
        date.getFullYear() === now.getFullYear() &&
        date.getMonth() === now.getMonth() &&
        date.getDate() === now.getDate();

    if (isToday) {
        // 오늘인 경우 HH:mm 형식 반환
        return `${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
    } else {
        // 오늘이 아닌 경우 MM-DD 형식 반환
        return `${(date.getMonth() + 1).toString().padStart(2, '0')}-${date.getDate().toString().padStart(2, '0')}`;
    }
}

// 초기 데이터 로드
document.addEventListener('DOMContentLoaded', () => {
    loadBoards(1); // 페이지 1부터 데이터 로드
});
