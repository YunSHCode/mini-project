package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardFileDTO;
import com.playdata.miniproject.util.FileUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardFileService {

    @Value("${file.board.dir}")
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

    public List<BoardFileDTO> uploadBoardFiles(List<MultipartFile> files) throws IOException {
        List<FileUtils.FileInfo> fileInfos = FileUtils.uploadFiles(files, uploadDir); // 원본과 저장 파일명 정보 반환
        List<BoardFileDTO> fileDTOList = new ArrayList<>();
        for (FileUtils.FileInfo fileInfo : fileInfos) {
            fileDTOList.add(new BoardFileDTO(
                    fileInfo.getOriginalFilename(), // 원본 파일명
                    fileInfo.getStoreFilename()     // 저장된 파일명
            ));
        }
        return fileDTOList;
    }

    public BoardFileDTO uploadBoardFile(MultipartFile file) throws IOException {
        System.out.println("boardFileService file = " + file);
        FileUtils.FileInfo fileInfo = FileUtils.uploadFile(file, uploadDir);
        BoardFileDTO boardFileDTO = new BoardFileDTO(
                fileInfo.getOriginalFilename(),
                fileInfo.getStoreFilename()
        );

        return  boardFileDTO;
    }
}
