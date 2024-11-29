package com.playdata.miniproject.feed.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("feedUsers")
public class UserDTO {
    int userKey;
    String userID;
    String userPassword;
    String userNickname;
    String userRealname;
    String userBirthday;
    String userEmail;
    String userPhoneNumber;
    String userVeganType;
    String userProfilePictureOriginal;
    String userProfilepictureGenerated;
    LocalDateTime userCreatedAt;
    LocalDateTime userUpdatedAt;

}
