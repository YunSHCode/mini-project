package com.playdata.miniproject.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class FileUtils {

    // 파일 저장 경로와 파일명을 합쳐서 전체 경로를 반환
    public static String getFullPath(String basePath, String filename) {
        return basePath + File.separator + filename;
    }

    // 파일 업로드 처리 및 원본 파일명과 저장된 파일명 반환
    public static List<FileInfo> uploadFiles(List<MultipartFile> multipartFiles, String uploadPath) throws IOException {
        List<FileInfo> fileInfos = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFile.isEmpty()) {
                String originalFilename = multipartFile.getOriginalFilename(); // 원본 파일명
                String storeFilename = createStoreFilename(originalFilename); // 저장된 파일명
                multipartFile.transferTo(new File(getFullPath(uploadPath, storeFilename))); // 파일 저장
                fileInfos.add(new FileInfo(originalFilename, storeFilename)); // 파일 정보 저장
            }
        }
        return fileInfos;
    }

    public static FileInfo uploadFile(MultipartFile multipartFile, String uploadPath) throws IOException {
        if (!multipartFile.isEmpty()) {
            String originalFilename = multipartFile.getOriginalFilename(); // 원본 파일명
            String storeFilename = createStoreFilename(originalFilename); // 저장된 파일명
            multipartFile.transferTo(new File(getFullPath(uploadPath, storeFilename))); // 파일 저장
            return new FileInfo(originalFilename, storeFilename); // 파일 정보 반환
        } else {
            throw new IllegalArgumentException("업로드된 파일이 비어 있습니다.");
        }
    }

    // 저장할 파일명 생성 (UUID 활용)
    public static String createStoreFilename(String originalFilename) {
        int position = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(position + 1);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid + "." + ext;
    }

    public static class FileInfo {
        private final String originalFilename;
        private final String storeFilename;

        public FileInfo(String originalFilename, String storeFilename) {
            this.originalFilename = originalFilename;
            this.storeFilename = storeFilename;
        }

        public String getOriginalFilename() {
            return originalFilename;
        }

        public String getStoreFilename() {
            return storeFilename;
        }
    }
}