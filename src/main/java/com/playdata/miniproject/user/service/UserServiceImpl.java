package com.playdata.miniproject.user.service;

import com.playdata.miniproject.user.dao.UserDAO;
import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UpdateDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    @Override
    public UserDTO getUserByUsername(LoginUserDTO loginUserDTO) {
        return userDAO.getUserById(loginUserDTO);
    }

    @Override
    public int addNewUser(SignupDTO signupDTO) {
        return userDAO.addNewUser(signupDTO);
    }

    @Override
    public UserDTO userIdCheck(String userId) {
        return userDAO.userIdCheck(userId);
    }

    @Override
    public UserDTO userNicknameCheck(String userNickname) {
        return userDAO.userNicknameCheck(userNickname);
    }

    @Override
    public UserDTO userEmailCheck(String userEmail) {
        return userDAO.userEmailCheck(userEmail);
    }

    @Override
    public UserDTO userPhoneCheck(String userPhone) {
        return userDAO.userPhoneCheck(userPhone);
    }

    @Override
    public int changeInfo(UpdateDTO updateDTO) {
        return userDAO.changeInfo(updateDTO);
    }

    @Override
    public UserDTO getUserByUserKey(int userKey) {
        return userDAO.getUserByUserKey(userKey);
    }


}
