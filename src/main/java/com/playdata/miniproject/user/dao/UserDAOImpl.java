package com.playdata.miniproject.user.dao;

import com.playdata.miniproject.user.dto.LoginUserDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO {
    private final UserMapper mapper;

    @Override
    public UserDTO getUserById(LoginUserDTO loginUserDTO) {
        return mapper.login(loginUserDTO);
    }

    @Override
    public int addNewUser(SignupDTO signupDTO) {
        return mapper.signup(signupDTO);
    }

    @Override
    public UserDTO userIdCheck(String userId) {
        return mapper.idcheck(userId);
    }

    @Override
    public UserDTO userNicknameCheck(String userNickname) {
        return mapper.nicknamecheck(userNickname);
    }

    @Override
    public UserDTO userEmailCheck(String userEmail) {
        return mapper.emailcheck(userEmail);
    }

    @Override
    public UserDTO userPhoneCheck(String userPhone) {
        return mapper.phonecheck(userPhone);
    }


}
