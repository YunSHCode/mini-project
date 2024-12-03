package com.playdata.miniproject.user.service;

import com.playdata.miniproject.community.dto.GroupFileDTO;
import com.playdata.miniproject.user.dto.SignupDTO;
import com.playdata.miniproject.user.dto.UserDTO;
import com.playdata.miniproject.user.dto.UserFileDTO;
import com.playdata.miniproject.util.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFileService {

    @Value("${file.dir}user/")
    private String uploadUser;
    public UserFileDTO uploadUserFile(MultipartFile file) throws IOException {
        System.out.println("file = " + file);
        FileUtils.FileInfo fileInfo = FileUtils.uploadFile(file, uploadUser);

        UserFileDTO signupDTO = new UserFileDTO(
                fileInfo.getOriginalFilename(),
                fileInfo.getStoreFilename()
        );
        return signupDTO;
    }

    @PostConstruct
    private void ensureUploadDirUsers() {
        File dir = new File(uploadUser);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("uploadUser = " + uploadUser);
            } else {
                System.out.println("failed = " + created);
            }
        }
    }

}
