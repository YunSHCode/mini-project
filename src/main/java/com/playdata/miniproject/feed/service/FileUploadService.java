package com.playdata.miniproject.feed.service;

import com.playdata.miniproject.feed.dto.FeedfileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileUploadService {
    @Value("${file.dir}")
    private String uploadpath; //file.dir=C:/fullstack7/upload/

    // 파일명을 전달 받아서 업로드 폴더 경로와 파일명을 연결해서 실제 어느 위치에 어떤 파일로 처리될지 full path를 만들어서 리턴하는 메소드
    public String getUploadpath(String filename) {
        System.out.println(uploadpath + filename);
        return uploadpath + filename;
    }

    // 실제 파일 업로드를 실행하는 메소드
    // 업로드되는 파일의 정보를 List로 만들어서 반환
    public List<FeedfileDTO> uploadFiles(List<MultipartFile> MultipartFiles) throws IOException {

        List<FeedfileDTO> fileDTOList = new ArrayList<>();
        // 매개변수로 전달된 업로드할 파일정보가 저장된 List에 있는 모든 MultipartFile을 꺼내서 업로드
        for (MultipartFile multipartFile : MultipartFiles) {
            if (!multipartFile.isEmpty()) { // 파일의 내용이 empty 상태인지 평가
                // 원본 파일명을 추출
                String originalFilename = multipartFile.getOriginalFilename();
                // 저장 파일명 추출
                String storeFilename = createStoreFilename(originalFilename);

                System.out.println(storeFilename);
                System.out.println(originalFilename);

                // transferTo메소드는 지정된 경로의 파일명으로 파일 객체 생성
                multipartFile.transferTo(new File(getUploadpath(storeFilename)));

                // FeedfileDTO 생성자에 필요한 값을 모두 채워서 DTO 객체 생성
                FeedfileDTO feedfileDTO = new FeedfileDTO();
                feedfileDTO.setFeedFileNameOrg(originalFilename);
                feedfileDTO.setFeedFileName(storeFilename);
                feedfileDTO.setFeedUploadDt(LocalDateTime.now());

                // 파일의 확장자명을 얻어서 feedFileType으로 설정
                String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();

                int fileType = 0; // 기본 값 0 (기타)

                // 파일 확장자에 따라 타입 설정
                if (fileExtension.equals("jpg") || fileExtension.equals("jpeg") || fileExtension.equals("png") || fileExtension.equals("gif")) {
                    fileType = 1; // 이미지 파일
                } else if (fileExtension.equals("pdf")) {
                    fileType = 2; // PDF 파일
                } else if (fileExtension.equals("txt")) {
                    fileType = 3; // 텍스트 파일
                }

                feedfileDTO.setFeedFileType(fileType); // 확장자에 맞는 타입 설정
                feedfileDTO.setFeedId(1);  // 예시로 피드 ID를 설정, 실제로는 DB에 저장된 피드 ID로 설정

                // 파일 DTO 리스트에 추가
                fileDTOList.add(feedfileDTO);

            }

        }
        return fileDTOList;
    }

    // 저장되는 파일명을 리턴하는 메소드 - UUID를 이용해서 파일명을 생성
    private String createStoreFilename(String originalFilename) {
        // 확장자 추출
        int position = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(position + 1);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
}
