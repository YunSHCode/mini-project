package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.dto.BoardCommentRequest;
import com.playdata.miniproject.board.dto.BoardCommentResponse;
import com.playdata.miniproject.board.service.BoardCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class BoardCommentController {

    private final BoardCommentService commentService;
    @PostMapping("/write")
    public String writeComment(
            @RequestBody BoardCommentRequest commentRequest
            ,@SessionAttribute(name = "userKey", required = false) Integer userKey) {
        // 세션에서 사용자 ID 가져오기
//        if (userKey == null) {
//            int userId = 1;
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
        int userId = (userKey == null) ? 1 : userKey;
        userId = 1;

        // 댓글 저장 로직 (서비스 클래스를 통해 처리)
        commentRequest.setUserKey(userId);
        commentService.saveComment(commentRequest);

        // 응답으로 저장된 댓글 정보 반환
        return "success";
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardCommentResponse>> getComments(@RequestParam int boardId) {
        List<BoardCommentResponse> comments = commentService.getCommentsByBoardId(boardId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
