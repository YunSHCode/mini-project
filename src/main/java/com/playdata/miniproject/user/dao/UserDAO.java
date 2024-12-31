package com.playdata.miniproject.user.dao;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UpdateDTO;
import com.playdata.miniproject.user.dto.UserDTO;

public interface UserDAO {
    UserDTO getUserById(LoginUserDTO loginUserDTO);
    int addNewUser(SignupDTO signupDTO);
    UserDTO userIdCheck(String userId);
    UserDTO userNicknameCheck(String userNickname);
    UserDTO userEmailCheck(String userEmail);
    UserDTO userPhoneCheck(String userPhone);
    int changeInfo(UpdateDTO updateDTO);

    UserDTO getUserByUserKey(int userKey);
}
