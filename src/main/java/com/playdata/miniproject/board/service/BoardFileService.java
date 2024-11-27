package com.playdata.miniproject.board.service;

import com.playdata.miniproject.board.dto.BoardFileDTO;
import com.playdata.miniproject.util.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BoardFileService {
    private final String uploadDir = Paths.get("D:", "mini-project", "images", "board").toString();

    public List<BoardFileDTO> uploadBoardFiles(List<MultipartFile> files) throws IOException {
        List<FileUtils.FileInfo> fileInfos = FileUtils.uploadFiles(files, uploadDir); // 원본과 저장 파일명 정보 반환
        List<BoardFileDTO> fileDTOList = new ArrayList<>();
        for (FileUtils.FileInfo fileInfo : fileInfos) {
            fileDTOList.add(new BoardFileDTO(
                    0,  // 파일 고유 ID
                    0,                         // 게시판 ID (필요시 설정)
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
                0,
                0,
                fileInfo.getOriginalFilename(),
                fileInfo.getStoreFilename()
        );

        return  boardFileDTO;
    }
}
