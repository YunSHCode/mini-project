package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/boardList")
    public String readBoard(Model model) {
        model.addAttribute("boards", boardService.readBoard());
        return "/board/boardList";
    }

    @GetMapping("/boardForm")
    public String showBoardForm() {
        return "/board/boardForm";
    }

    @PostMapping("/insertBoard")
    public String createBoard(@RequestParam String boardTitle,
                              @RequestParam String boardContent,
                              @RequestParam Long userId) {
        boardService.insertBoard(boardTitle, boardContent, userId);
        return "redirect:/board/boardList";
    }

}
