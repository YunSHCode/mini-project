package com.playdata.miniproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("loginInfo")
public class LoginUserDTO {
    private String userId;               // 아이디
    private String userPassword;               // 비밀번호
}
