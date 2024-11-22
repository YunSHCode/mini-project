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
        url: '/board/list',
        type: 'GET',
        data: { page, searchCategory: category, searchKeyword: keyword },
        dataType: 'json',
        success: function (data) {
            renderTable(data.boardList);
            renderPagination(data.pagination);
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
        }
    });
}

function renderPagination(pagination) {
    const paginationDiv = document.getElementById('pagination');
    paginationDiv.innerHTML = '';

    if (pagination.hasPrev) {
        paginationDiv.innerHTML += `<button onclick="loadBoards(${pagination.currentPageNo - 1})">이전</button>`;
    }

    for (let i = pagination.firstPageNoOnPageList; i <= pagination.lastPageNoOnPageList; i++) {
        paginationDiv.innerHTML += `
            <button onclick="loadBoards(${i})" class="${i === pagination.currentPageNo ? 'active' : ''}">
                ${i}
            </button>`;
    }

    if (pagination.hasNext) {
        paginationDiv.innerHTML += `<button onclick="loadBoards(${pagination.currentPageNo + 1})">다음</button>`;
    }
}

/**
 * 게시글 데이터를 테이블에 렌더링합니다.
 * @param {Array} boardList - 게시글 목록
 */
function renderTable(boardList) {
    const tableBody = document.getElementById('boardTableBody');
    tableBody.innerHTML = ''; // 기존 데이터를 초기화합니다.

    if (boardList.length === 0) {
        tableBody.innerHTML = `
            <tr>
                <td colspan="5" style="text-align: center;">게시글이 없습니다.</td>
            </tr>`;
        return;
    }

    boardList.forEach((board) => {
        const row = document.createElement('tr');

        // 게시글 데이터를 테이블에 추가합니다.
        row.innerHTML = ` 
            <td>${board.boardTitle}</td>
            <td>${board.userName}</td>
            <td>${formatDate(board.boardCreateDt)}</td>
        `;

        tableBody.appendChild(row);
    });
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
 * @param {string} dateStr - 날짜 문자열
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
