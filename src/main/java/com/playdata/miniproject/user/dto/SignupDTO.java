package com.playdata.miniproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("signupInfo")
public class SignupDTO {
    private String userProfilePictureOriginal;         // 프로필 사진 URL 원본 파일명
    private String userProfilePictureGenerated;         // 프로필 사진 URL 생성 파일명
    private String userId;               // 사용자 아이디
    private String userPassword;               // 비밀번호
    private String userNickname;               // 닉네임
    private String userRealname;               // 사용자실명
    private String userBirthday;               // 생년월일
    private String userEmail;                  // 이메일
    private String userPhoneNumber;            // 전화번호
    private String userVeganType;               // 비건 유형 (vegan, ovo, pesco)
    MultipartFile userPicture;}
