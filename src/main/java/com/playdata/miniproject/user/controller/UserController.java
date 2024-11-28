package com.playdata.miniproject.user.controller;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {
    private final UserService service;

    @GetMapping("/login/first")
    public String loginfirst(Model model) {
        return "/user/login";
    }

    @GetMapping("/signup/first")
    public String signupfirst(Model model) {
        return "/user/signup1";
    }

    //---------------------------------------------------------

    @PostMapping("/login/verify")
    //스프링에서 제공되는 기능을 이용해서 로그인 처리하기
    public String springlogin(LoginUserDTO loginUserDTO, Model model) {
        UserDTO user = service.getUserByUsername(loginUserDTO);
        String view="";
        System.out.println(user);
        if(user!=null) {
            System.out.println("로그인 성공");
            model.addAttribute("user", user);
            view="user/mypage";
        }else{
            System.out.println("로그인 실패");
            view="redirect:/user/login/first";
        }
        return view;
    }

    //---------------------------------------------------------

    @PostMapping("/signup/verify")
    public String signup(SignupDTO signupDTO) {
        service.addNewUser(signupDTO);
        System.out.println(signupDTO);
        return "user/success";

    }

}
