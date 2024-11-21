package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String readBoard() {
        return "/board/boardList";
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> getBoardList(
            @RequestParam int page,
            @RequestParam(required = false) String searchCategory,
            @RequestParam(required = false) String searchKeyword) {
        return boardService.getBoardList(page, searchCategory, searchKeyword);
    }

    @GetMapping("/boardForm")
    public String showBoardForm() {
        return "/board/boardForm";
    }

    @PostMapping("/insertBoard")
    public String createBoard(@RequestParam String boardTitle,
                              @RequestParam String boardContent,
                              @RequestParam int userId) {
        boardService.insertBoard(boardTitle, boardContent, userId);
        return "redirect:/board/boardList";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable int id, Model model) {
        // 게시글 정보를 데이터베이스에서 가져옵니다.
        BoardWithUserDTO board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        System.out.println("board = " + board);
        return "board/board"; // Thymeleaf 템플릿 경로
    }

    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable int id, Model model) {
        return "board/updateForm";
    }

}
