package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.springframework.ui.Model;

import java.util.List;

public interface FeedServiceImp {
        // 1. 피드 생성
        int FeedUpload(FeedDTO feed, List<FeedfileDTO> files); // 파일 포함

        // 2. 피드 목록 조회
        List<FeedDTO> feedList();

        // 3. 피드 상세 조회
        FeedDTO getFeedInfo(int feed_id);

        // 4. 피드 수정
        int FeedUpdate(FeedDTO feed);

        // 5. 피드 삭제
        int FeedDelete(int feed_id);

        // 6. 피드 검색 (단일 키워드)
        List<FeedDTO> search(String keyword);

        // 7. 피드 검색 (태그 및 키워드)
        List<FeedDTO> search(String tag, String keyword);

        // 8. 피드 파일 조회
        List<FeedfileDTO> getFileList(int feed_id);

        // 9. 특정 파일 조회
        FeedfileDTO getFile(int file_id);
}
