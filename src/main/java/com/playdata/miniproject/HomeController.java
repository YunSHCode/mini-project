package com.playdata.miniproject;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.service.BoardService;
import com.playdata.miniproject.cafe.dto.ReservationSuccess;
import com.playdata.miniproject.cafe.service.ReservationService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;
    private final ReservationService reservationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/myPage")
    public String myPage(@SessionAttribute(value = "user", required = false) UserDTO user,
            @RequestParam(defaultValue = "0") int boardPage,
            @RequestParam(defaultValue = "10") int boardSize,
            @RequestParam(defaultValue = "0") int reservationPage,
            @RequestParam(defaultValue = "10") int reservationSize,
                         RedirectAttributes redirectAttributes,
            Model model
    ) {
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }

        // 게시글 데이터 페이징 처리
        Page<BoardWithUserDTO> myBoardPage = boardService.getBoardByUser(user.getUserKey(), boardPage, boardSize);
        model.addAttribute("myBoardPage", myBoardPage);
        Page<ReservationSuccess> myReservationsPage = reservationService.getReservationsByUser(user.getUserKey(), reservationPage, reservationSize);
        List<ReservationSuccess> reservations = myReservationsPage.getContent();
        reservations.forEach(reservation -> {
            System.out.println("Reservation: " + reservation);
        });
        // 예약 데이터 페이징 처리

        reservations.forEach(reservation -> {
            System.out.println("Reservation: " + reservation);
        });
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<String> reservationJsonList = myReservationsPage.getContent().stream()
                .map(reservation -> {
                    try {
                        return mapper.writeValueAsString(reservation);
                    } catch (JsonProcessingException e) {
                        return "{}"; // JSON 변환 실패 시 빈 객체
                    }
                }).toList();
        System.out.println("reservationJsonList = " + reservationJsonList);
        model.addAttribute("myReservationsPage", myReservationsPage);
        model.addAttribute("reservationJsonList", reservationJsonList);

        return "user/mypageTest";
    }



}
