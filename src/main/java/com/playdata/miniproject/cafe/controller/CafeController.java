package com.playdata.miniproject.cafe.controller;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import com.playdata.miniproject.cafe.dto.ReservationSuccess;
import com.playdata.miniproject.cafe.service.CafeService;
import com.playdata.miniproject.cafe.service.ReservationService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class CafeController {
    private final CafeService cafeService;
    private final ReservationService reservationService;

    @GetMapping("/")
    public String feed() {
        return "cafe/cafe";
    }

    @GetMapping("/read")
    public String readcafe() {
        return "cafe/cafe_particular";
    }

    @GetMapping("/reservation/{id}")
    public String cafe(@PathVariable("id") int cafeId, @SessionAttribute(value = "user", required = false) UserDTO user, Model model, RedirectAttributes redirectAttributes) {
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }
        CafeDTO cafe = reservationService.selectById(cafeId);
        UserDTO customer = user;
        List<MenuDTO> menu = reservationService.menuListByCafe(cafeId);

        model.addAttribute("cafe", cafe);
        model.addAttribute("customer", customer);
        model.addAttribute("menu", menu);
        return "cafe/cafe_reservation";
    }

//    @GetMapping("/complete")
//    public String reservationComplete(@RequestParam("reservationId") int reservationId, Model model) {
//        ReservationInfo reservationInfo = reservationService.getReservationInfo(reservationId);
//        model.addAttribute("reservationInfo", reservationInfo);
//        return "reservation/complete";
//    }

    @GetMapping("/reservation/success")
    public String reservationSuccess(@RequestParam("reservationId") int reservationId, Model model) {
        // reservationId를 사용하여 예약 정보 조회
        ReservationSuccess reservation = reservationService.getReservationById(reservationId);

        // 총 금액 계산
        int totalAmount = reservation.getMenuList().stream()
                .mapToInt(menu -> menu.getMenuPrice() * menu.getReservationMenuQuantity())
                .sum();

        model.addAttribute("reservation", reservation);
        model.addAttribute("totalAmount", totalAmount);
        System.out.println("reservation.getMenuList() = " + reservation.getMenuList());
        return "cafe/reservationSuccess";
    }

}