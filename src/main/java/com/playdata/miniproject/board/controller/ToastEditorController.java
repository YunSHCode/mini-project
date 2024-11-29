package com.playdata.miniproject.board.controller;

import com.playdata.miniproject.board.dto.BoardFileDTO;
import com.playdata.miniproject.board.service.BoardFileService;
import com.playdata.miniproject.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/tui_editor")
@RequiredArgsConstructor
public class ToastEditorController {

    // 파일 저장할 디렉토리
    @Value("${file.board.dir}")
    private String uploadDir;

    private final BoardFileService boardFileService;
    /**
     * 에디터 이미지 업로드
     * @param image 파일 객체
     * @return 업로드된 파일명
     */
    @PostMapping("/image-upload")
    public String uploadEditorImage(@RequestParam final MultipartFile image) throws IOException {
        if (image.isEmpty()) {
            return "";
        }
        BoardFileDTO boardFile = boardFileService.uploadBoardFile(image);
        return "/upload/board/" + boardFile.getStoreFilename();
    }
}
