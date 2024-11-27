package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.service.BoardFileService;
import com.playdata.miniproject.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceImpl boardServiceImpl;
    private final BoardFileService boardFileService;

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
        return boardServiceImpl.getBoardList(page, searchCategory, searchKeyword);
    }

    @GetMapping("/boardForm")
    public String showBoardForm() {
        return "/board/boardForm";
    }

    @PostMapping("/insertBoard")
    public String createBoard(@RequestParam String boardTitle,
                              @RequestParam String boardContent,
                              @RequestParam int userId) {
        boardServiceImpl.insertBoard(boardTitle, boardContent, userId);
        return "redirect:/board/boardList";
    }

    @GetMapping("/{id}")
    public String getBoardDetail(@PathVariable int id, Model model) {
        // 게시글 정보를 데이터베이스에서 가져옵니다.
        BoardWithUserDTO board = boardServiceImpl.getBoardById(id);
        model.addAttribute("board", board);
        System.out.println("board = " + board);
        return "board/board";
    }

    @GetMapping("/edit/{id}")
    public String editBoard(@PathVariable int id, Model model) {
        return "board/updateForm";
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id) {
        boardServiceImpl.deleteBoard(id);
        return "redirect:/board/boardList";
    }

}
