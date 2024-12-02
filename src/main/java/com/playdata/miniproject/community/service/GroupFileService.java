package com.playdata.miniproject.community.service;

import com.playdata.miniproject.board.dto.BoardFileDTO;
import com.playdata.miniproject.community.dto.GroupFileDTO;
import com.playdata.miniproject.util.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupFileService {
    @Value("${file.community.dir}")
    private String uploadDir;

    @PostConstruct
    private void ensureUploadDirExists() {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (created) {
                System.out.println("Upload directory created at: " + uploadDir);
            } else {
                System.err.println("Failed to create upload directory at: " + uploadDir);
            }
        }
    }

    public GroupFileDTO uploadGroupFile(MultipartFile file) throws IOException {
        System.out.println("boardFileService file = " + file);
        FileUtils.FileInfo fileInfo = FileUtils.uploadFile(file, uploadDir);
        GroupFileDTO groupFileDTO = new GroupFileDTO(
                fileInfo.getOriginalFilename(),
                fileInfo.getStoreFilename()
        );

        return  groupFileDTO;
    }
}
