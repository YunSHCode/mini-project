package com.playdata.miniproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Repository;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("updateInfo")
public class UpdateDTO {
    private int userKey;
    private String userNickname;
    private String userPassword;
    private String userVeganType;
}
