package com.playdata.miniproject.user.controller;

import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AjaxController {
    public final UserService service;

    @GetMapping("/signup/idcheck")
    public String userIdCheck(String id) {
        String msg = "";
        UserDTO user = service.userIdCheck(id);
        if(user != null) {
            msg = "이미 사용 중인 아이디입니다.";
        }else{
            msg = "사용 가능한 아이디입니다.";
        }
        return msg;
    }

    @GetMapping("/signup/nicknamecheck")
    public String userNicknameCheck(String nickname) {
        System.out.println(nickname);
        String msg = "";
        UserDTO user = service.userNicknameCheck(nickname);
        System.out.println(user);
        if(user != null) {
            msg = "이미 사용 중인 닉네임입니다.";
        }else{
            msg = "사용 가능한 닉네임입니다.";
        }
        return msg;
    }

    @GetMapping("/signup/emailcheck")
    public String userEmailCheck(String email) {
        String msg = "";
        UserDTO user = service.userEmailCheck(email);
        if(user != null) {
            msg = "이미 사용 중인 이메일입니다.";
        }else{
            msg = "사용 가능한 이메일입니다.";
        }
        return msg;
    }

    @GetMapping("/signup/phonecheck")
    public String userPhoneCheck(String phone) {
        String msg = "";
        UserDTO user = service.userPhoneCheck(phone);
        if(user != null) {
            msg = "이미 사용 중인 휴대폰 번호입니다.";
        }else{
            msg = "사용 가능한 휴대폰 번호입니다.";
        }
        return msg;
    }

}
