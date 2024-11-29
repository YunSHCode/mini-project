package com.playdata.miniproject.user.mapper;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    UserDTO login(LoginUserDTO loginUserDTO);
    int signup(SignupDTO signupDTO);
    UserDTO idcheck(String userId);
    UserDTO nicknamecheck(String userNickname);
    UserDTO emailcheck(String userEmail);
    UserDTO phonecheck(String userPhoneNumber);


}
