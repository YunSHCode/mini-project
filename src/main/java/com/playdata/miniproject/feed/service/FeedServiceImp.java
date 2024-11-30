package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FeedServiceImp {


        // 1. 피드 생성
        int FeedUpload(FeedDTO feed, List<FeedfileDTO> files);
        void feedUpload(MultipartFile[] uploadFiles, String content, String[] diet);

        // 2. 피드 목록 조회
        List<FeedDTO> feedList();

        // 7. 피드 검색 (태그 및 키워드)
        List<FeedDTO> search(String tag, String keyword);

        // 4. 피드 수정
        int FeedUpdate(FeedDTO feed);

        // 5. 피드 삭제
        int FeedDelete(int feed_id);




}
