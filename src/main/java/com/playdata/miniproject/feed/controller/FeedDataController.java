package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.service.FeedCommentService;
import com.playdata.miniproject.feed.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class FeedDataController {

    private final FeedService feedService;

    @DeleteMapping("/feed/")
    public String delete(@RequestParam(value = "feedId" , required = true) Integer feedId) {

        feedService.deleteFeedCommentByFeedId(feedId);
        feedService.deleteFeed(feedId);
        return "test";
    }

//
//
//    @PostMapping("/write")
//    public String writeComment(
//            @RequestBody FeedCommentService feedCommentService,
//            @SessionAttribute(name = "user", required = false) Integer userKey,
//            @RequestParam(name = "cafeId") Integer cafeId,
//
//    ){
//
//    }


}
