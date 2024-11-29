package com.playdata.miniproject.cafe.controller;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import com.playdata.miniproject.cafe.service.CafeService;
import com.playdata.miniproject.cafe.service.ReservationService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String cafe(@PathVariable("id")int cafeId, @SessionAttribute(value = "user", required = false) UserDTO user, Model model) {
        CafeDTO cafe = reservationService.selectById(cafeId);
        UserDTO customer = user;
        List<MenuDTO> menu = reservationService.menuListByCafe(cafeId);

        model.addAttribute("cafe", cafe);
        model.addAttribute("customer", customer);
        model.addAttribute("menu", menu);
        return "cafe/cafe_reservation";
    }
}