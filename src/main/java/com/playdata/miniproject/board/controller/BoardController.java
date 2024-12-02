package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.service.BoardFileService;
import com.playdata.miniproject.board.service.BoardServiceImpl;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/board")
@Slf4j
@RequiredArgsConstructor
public class BoardController {
    private final BoardServiceImpl boardServiceImpl;
    @GetMapping("/cafe")
    public String cafe() {
        return "/cafe/cafe_reservation";
    }

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
    public String showBoardForm(@SessionAttribute(value = "user", required = false) UserDTO user, RedirectAttributes redirectAttributes) {
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }
        return "/board/boardForm";
    }

    @PostMapping("/insertBoard")
    public String createBoard(@RequestParam String boardTitle,
                              @RequestParam String boardContent,
                              @SessionAttribute(value = "user", required = false) UserDTO user) {
        boardServiceImpl.insertBoard(boardTitle, boardContent, user.getUserKey());
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
        BoardWithUserDTO board = boardServiceImpl.getBoardById(id);
        model.addAttribute("board", board);
        return "board/updateForm";
    }

    @PostMapping("/edit/{id}")
    public String editBoard(@PathVariable int id,
                            @RequestParam String boardTitle,
                            @RequestParam String boardContent,
                            @SessionAttribute(value = "user", required = false) UserDTO user) {
        boardServiceImpl.updateBoard(boardTitle, boardContent, id);
        return "redirect:/board/" + id;
    }

    @GetMapping("/delete/{id}")
    public String deleteBoard(@PathVariable int id, @SessionAttribute(name = "user", required = false) UserDTO user, RedirectAttributes redirectAttributes) {
        if (!boardServiceImpl.checkWriter(id, user.getUserKey())) {
            redirectAttributes.addFlashAttribute("errorMessage", "해당 게시글 삭제 권한이 없습니다.");
            System.out.println("redirectAttributes = " + redirectAttributes);
            return "redirect:/board/boardList";
        }
        boardServiceImpl.deleteBoard(id);
        return "redirect:/board/boardList";
    }
}
