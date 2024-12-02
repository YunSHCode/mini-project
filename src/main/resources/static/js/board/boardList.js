let currentCategory = '';
let currentKeyword = '';

/**
 * 게시글 데이터를 가져와 테이블에 렌더링합니다.
 * @param {number} page - 페이지 번호
 * @param {string} category - 검색 카테고리
 * @param {string} keyword - 검색 키워드
 */
function loadBoards(page = 1, category = '', keyword = '') {
    sessionStorage.setItem('boardCurrentPage', page);
    sessionStorage.setItem('boardCurrentCategory', category);
    sessionStorage.setItem('boardCurrentKeyword', keyword);
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

    // Bootstrap pagination container
    const ul = document.createElement('ul');
    ul.className = 'pagination justify-content-center';

    if (pagination.hasPrev) {
        // 이전 버튼을 클릭하면 현재 페이지 -10 또는 첫 번째 페이지로 이동
        const prevPage = Math.max(pagination.currentPageNo - 10, 1);
        ul.innerHTML += `
            <li class="page-item">
                <a class="page-link" href="#" onclick="loadBoards(${prevPage},currentCategory, currentKeyword)">이전</a>
            </li>`;
    } else {
        ul.innerHTML += `
            <li class="page-item disabled">
                <a class="page-link" href="#">이전</a>
            </li>`;
    }

    // 페이지 번호 버튼
    for (let i = pagination.firstPageNoOnPageList; i <= pagination.lastPageNoOnPageList; i++) {
        ul.innerHTML += `
            <li class="page-item ${i === pagination.currentPageNo ? 'active' : ''}">
                <a class="page-link" href="#" onclick="loadBoards(${i},currentCategory, currentKeyword)">${i}</a>
            </li>`;
    }

    // 다음 버튼
    if (pagination.hasNext) {
        // 다음 버튼을 클릭하면 현재 페이지 +10 또는 마지막 페이지로 이동
        const nextPage = Math.min(pagination.currentPageNo + 10, pagination.realEndPage);
        ul.innerHTML += `
            <li class="page-item">
                <a class="page-link" href="#" onclick="loadBoards(${nextPage},currentCategory, currentKeyword)">다음</a>
            </li>`;
    } else {
        ul.innerHTML += `
            <li class="page-item disabled">
                <a class="page-link" href="#">다음</a>
            </li>`;
    }

    paginationDiv.appendChild(ul);
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
            <td><a href="/board/${board.boardId}">${board.boardTitle}</a></td>
            <td>${board.userNickname}</td>
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
    const savedPage = sessionStorage.getItem('boardCurrentPage') || 1;
    const savedCategory = sessionStorage.getItem('boardCurrentCategory');
    const savedKeyword = sessionStorage.getItem('boardCurrentKeyword');

    // 검색창과 카테고리 값 설정
    if (savedKeyword) {
        document.getElementById('searchKeyword').value = savedKeyword;
        currentKeyword = savedKeyword;
    }
    if (savedCategory) {
        document.getElementById('searchCategory').value = savedCategory;
        currentCategory = savedCategory;
    }

    // 저장된 값으로 게시판 목록 로드
    loadBoards(Number(savedPage), savedCategory || '', savedKeyword || '');
});

document.getElementById('resetBoard').addEventListener('click', function () {
    // 게시판 상태 초기화
    sessionStorage.removeItem('boardCurrentPage');
    sessionStorage.removeItem('boardCurrentCategory');
    sessionStorage.removeItem('boardCurrentKeyword');
});
