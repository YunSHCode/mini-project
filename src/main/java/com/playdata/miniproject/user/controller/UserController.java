package com.playdata.miniproject.user.controller;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UpdateDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {
    private final UserService service;


    //---------------------------------------------------------

    @PostMapping("/update-account")
    public String update(UpdateDTO updateDTO, Model model) {
        int user = service.changeInfo(updateDTO);

        if(user>=1){
            System.out.println("정보수정 성공");
            return "redirect:/user/mypage";
        }else{
            System.out.println("정보수정 실패");
            model.addAttribute("errorMsg", true);
            return "redirect:/user/mypage";
        }

    }

    //---------------------------------------------------------

    @GetMapping("/login/first")
    public String loginfirst(Model model) {
        return "/user/login";
    }

    @GetMapping("/signup/first")
    public String signupfirst(Model model) {
        return "/user/signup";
    }

    @GetMapping("/layout/default")
    public String layout(Model model) {
        return "/layout/default_layout";
    }

    @GetMapping("/mypage")
    public String mypage(@SessionAttribute(value = "user", required = false) UserDTO user, Model model) {
        if(user==null){
            return "redirect:/user/login/first";
        }
        UserDTO userDTO = service.getUserByUserKey(user.getUserKey());
        model.addAttribute("user", userDTO);
        return "user/account";
    }


    //---------------------------------------------------------

    @PostMapping("/login/verify")
    public String springlogin(LoginUserDTO loginUserDTO, Model model) {
        UserDTO user = service.getUserByUsername(loginUserDTO);

        if (user != null) {
            System.out.println("로그인 성공");
            model.addAttribute("user", user); // 필요시 세션에 저장
            return "redirect:/"; // 메인 페이지로 이동
        } else {
            System.out.println("로그인 실패");
            model.addAttribute("errorMessage", true);
            return "/user/login"; // 로그인 페이지로 다시 렌더링
        }
    }


    @GetMapping("/logout/verify")
    public String springlogout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    //---------------------------------------------------------


    @PostMapping("/signup/verify")
    public String signup(SignupDTO signupDTO) {
        service.addNewUser(signupDTO);
        System.out.println(signupDTO);
        return "user/success";

    }

}
