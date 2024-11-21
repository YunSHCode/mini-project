package com.playdata.miniproject.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int currentPageNo;          // 현재 페이지 번호
    private int recordCountPerPage;     // 한 페이지당 항목 수
    private int pageSize;               // 페이지 리스트에 표시할 페이지 수
    private int totalRecordCount;       // 전체 항목 수

    private int firstPageNoOnPageList;  // 현재 페이지 리스트의 첫 번째 페이지 번호
    private int lastPageNoOnPageList;   // 현재 페이지 리스트의 마지막 페이지 번호
    private int realEndPage;            // 실제 마지막 페이지 번호
    private boolean hasPrev;            // 이전 페이지 리스트 존재 여부
    private boolean hasNext;            // 다음 페이지 리스트 존재 여부

    // 기본 생성자
    public Pagination() {
        this.pageSize = 10;             // 기본 페이지 크기 설정
    }

    /**
     * 페이징 계산 로직
     */
    public void calculatePagination() {
        // 실제 마지막 페이지 계산
        this.realEndPage = (int) Math.ceil((double) totalRecordCount / recordCountPerPage);

        // 현재 페이지 리스트의 마지막 번호
        this.lastPageNoOnPageList = (int) Math.ceil((double) currentPageNo / pageSize) * pageSize;

        // 현재 페이지 리스트의 첫 번째 번호
        this.firstPageNoOnPageList = this.lastPageNoOnPageList - pageSize + 1;

        // 마지막 페이지가 실제 페이지 수를 초과하지 않도록 조정
        if (lastPageNoOnPageList > realEndPage) {
            this.lastPageNoOnPageList = realEndPage;
        }

        // 이전, 다음 버튼 활성화 여부
        this.hasPrev = firstPageNoOnPageList > 1;
        this.hasNext = lastPageNoOnPageList < realEndPage;
    }

    /**
     * SQL Offset 계산
     * @return 첫 번째 데이터의 인덱스
     */
    public int getFirstRecordIndex() {
        return (currentPageNo - 1) * recordCountPerPage;
    }

    // Getters and setters
}

