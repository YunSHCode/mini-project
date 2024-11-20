package com.playdata.miniproject.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int currentPageNo = 1;          // 현재 페이지 번호
    private int recordCountPerPage = 10;   // 한 페이지당 항목 수
    private int pageSize = 10;             // 페이지 리스트에 표시할 페이지 수
    private int totalRecordCount;          // 전체 항목 수

    private boolean prev;                  // 이전 버튼 여부
    private boolean next;                  // 다음 버튼 여부
    private int firstPageNoOnPageList;     // 현재 페이지 리스트의 첫 번째 페이지 번호
    private int lastPageNoOnPageList;      // 현재 페이지 리스트의 마지막 페이지 번호
    private int realEnd;                   // 전체 페이지 중 마지막 번호

    // 계산 로직
    public void calculatePagination() {
        realEnd = (int) Math.ceil((double) totalRecordCount / recordCountPerPage);
        lastPageNoOnPageList = (int) Math.ceil((double) currentPageNo / pageSize) * pageSize;
        firstPageNoOnPageList = Math.max(1, lastPageNoOnPageList - pageSize + 1);

        if (lastPageNoOnPageList > realEnd) {
            lastPageNoOnPageList = realEnd;
        }

        prev = firstPageNoOnPageList > 1;
        next = lastPageNoOnPageList < realEnd;
    }

    public int getFirstRecordIndex() {
        return (currentPageNo - 1) * recordCountPerPage;
    }

    // Getters and Setters
}


