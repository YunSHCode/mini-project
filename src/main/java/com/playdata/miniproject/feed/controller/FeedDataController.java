package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedCommentsDTO;
import com.playdata.miniproject.feed.service.FeedCommentService;
import com.playdata.miniproject.feed.service.FeedService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FeedDataController {

    private final FeedService feedService;
    private final FeedCommentService feedCommentService;

    @DeleteMapping("/feed/")
    public String delete(@RequestParam(value = "feedId", required = true) Integer feedId) {
        feedService.deleteFeedCommentByFeedId(feedId);
        feedService.deleteFeed(feedId);
        return "test";
    }

    @PostMapping("/feed/comment")
    public String writeComment(
            @SessionAttribute(name = "user", required = false) UserDTO userDTO,
            @RequestParam(name = "feedCommentContent") String feedCommentContent,
            @RequestParam(name = "feedId") Integer feedId,

            RedirectAttributes redirectAttributes) {


        if (userDTO == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }

        if (feedCommentContent == null || feedCommentContent.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("feedComment", "댓글을 입력해 주세요");
            return "redirect:/feed/";
        }

        FeedCommentsDTO feedCommentsDTO = new FeedCommentsDTO();
        feedCommentsDTO.setUserKey(userDTO.getUserKey());
        feedCommentsDTO.setFeedId(feedId);
        feedCommentsDTO.setUserKey(userDTO.getUserKey());

       /* feedCommentsDTO.setFeedCommentStep(feedCommentStep);
        feedCommentsDTO.setFeedCommentGroup(feedCommentGroup);*/
        feedCommentsDTO.setFeedCommentContent(feedCommentContent);

        System.out.println("===================");
        System.out.println("DTO: " + feedCommentsDTO);
        System.out.println("내용: " + feedCommentContent);
        System.out.println("아이디: " + feedId);
        System.out.println("===================");

        feedCommentService.insertComment(feedCommentsDTO);
        return "redirect:/feed/";
    }

    @GetMapping("/feed/comments")
    @ResponseBody
    public List<FeedCommentsDTO> getComments(@RequestParam("feedId") Integer feedId) {
        return feedCommentService.getCommentsByFeedId(feedId);
    }
}