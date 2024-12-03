package com.playdata.miniproject.user.controller;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.dto.UserFileDTO;
import com.playdata.miniproject.user.service.UserFileService;
import com.playdata.miniproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
@SessionAttributes("user")
public class UserController {
    private final UserService service;
private final UserFileService fileService;

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
    public String mypage(Model model) {
        return "user/account";
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
         //   session.setAttribute("user", user);
            model.addAttribute("user", user);
            view="redirect:/";
        }else{
            System.out.println("로그인 실패");
            view="redirect:/user/login/first";
        }
        return view;
    }

    //---------------------------------------------------------

//    @GetMapping("/logout/verify")
//    public String logout(HttpSession session) {
//        System.out.println(session.getId());
//        if(session!=null) {
//            session.invalidate();
//        }
//        return "redirect:/";
//    }

    //---------------------------------------------------------

    @GetMapping("/logout/verify")
    public String springlogout(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }

    //---------------------------------------------------------


    @PostMapping("/signup/verify")
    public String signup(@ModelAttribute SignupDTO signupDTO,
                         @RequestParam(value = "userPicture", required = false) MultipartFile userPicture) throws IOException {
        System.out.println("----------------");
        System.out.println(signupDTO);
        System.out.println(userPicture);

        UserFileDTO signupFile = fileService.uploadUserFile(userPicture);
        signupDTO.setUserProfilePictureOriginal(signupFile.getUserProfilePictureOriginal());
        signupDTO.setUserProfilePictureGenerated(signupFile.getUserProfilePictureGenerated());


        service.addNewUser(signupDTO);
        return "user/success";

        }

    }



