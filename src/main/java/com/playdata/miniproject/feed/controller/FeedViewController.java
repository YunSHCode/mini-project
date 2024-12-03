package com.playdata.miniproject.feed.controller;

import com.playdata.miniproject.feed.dto.FeedDTO;
import com.playdata.miniproject.feed.dto.FeedListDTO;
import com.playdata.miniproject.feed.dto.FeedfileDTO;
import com.playdata.miniproject.feed.service.FileUploadService;
import com.playdata.miniproject.feed.service.FeedService;
import com.playdata.miniproject.user.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/feed")
@RequiredArgsConstructor
@SessionAttributes("user")
public class FeedViewController {

    private final FeedService feedService;
    private final FileUploadService fileUploadService; // 파일 업로드 서비스 추가

    @Value("${file.dir}feed/")
    private String uploadpath; // file.dir=C:/fullstack7/upload/

    // 피드 메인 페이지
    @GetMapping("/")
    public String feed(
            Model model) {
        List<FeedListDTO> feedDTOList = feedService.getAllFeeds();
        for (FeedListDTO feedListDTO : feedDTOList) {
            List<FeedfileDTO> feedfileDTOList = feedService.getFeedFiles(feedListDTO.getFeedId());
            feedListDTO.setFeedFiles(feedfileDTOList);
        }
        model.addAttribute("feedList", feedDTOList);
        FeedDTO feedDTO = new FeedDTO();
        model.addAttribute("feed", feedDTO);

        return "/feed/feed"; // 피드 목록 화면 반환
    }

    // 피드 이미지 불러오기
    @GetMapping("/img/{imgName}")
    public void viewImage(@PathVariable("imgName") String imgName, HttpServletResponse response) throws Exception {
        // 파일 경로 생성
        Path filePath = Paths.get(uploadpath).resolve(imgName).normalize();

        // 파일 존재 여부 확인
        if (!Files.exists(filePath) || Files.isDirectory(filePath)) {
            response.sendError(404);
        }

        File file = filePath.toFile();
        response.setContentType(Files.probeContentType(filePath));
        response.setContentLengthLong(file.length());

        try (InputStream inputStream = new FileInputStream(filePath.toString())) {
            IOUtils.copyLarge(inputStream, response.getOutputStream());
        }

        response.getOutputStream().flush();

    }

    // 피드 업로드 화면
    @GetMapping("/upload")
    public String upload(
            @SessionAttribute(value = "user", required = false) UserDTO user, RedirectAttributes redirectAttributes) {
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }
        return "/feed/upload_feed";
    }

    // 내 피드 화면
    @GetMapping("/myfeed")
    public String myfeed(
            @SessionAttribute(value = "user", required = false) UserDTO user,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }

        // 사용자의 피드 수 가져오기
        int myfeedCount = feedService.myFeedCount(user.getUserKey());
        System.out.println("*****************************");
        System.out.println("유저 아이디" + user.getUserKey());
        System.out.println("feed 횟수" + myfeedCount);
        List<FeedListDTO> feedDTOList = feedService.getFeedsByUser(user.getUserKey());

        // 모델에 데이터 추가

        model.addAttribute("myfeedCount", myfeedCount);
        model.addAttribute("feedList", feedDTOList);

        return "/feed/myfeed";
    }

    // 피드 수정 화면
    @GetMapping("/{feedId}/modify")
    public String editFeed(@PathVariable("feedId") Integer feedId, Model model) {

        FeedListDTO feedDTO = feedService.getFeedById(feedId);

        List<FeedfileDTO> feedfileDTOList = feedService.getFeedFiles(feedDTO.getFeedId());
        feedDTO.setFeedFiles(feedfileDTOList);

        model.addAttribute("feed", feedDTO);

        return "/feed/edit_feed"; // 피드 수정 화면 반환
    }

    @PostMapping("/modify/feed")
    public String modifyFeed(
            @RequestParam(name = "feedFile", required = false) List<MultipartFile> feedFiles,
            @RequestBody FeedListDTO feed) {

        return "asdasds";
    }

    // 피드 업로드 처리
    @PostMapping("/upload/feed")
    public String uploadFeed(@RequestParam(name = "feedFile", required = false) List<MultipartFile> feedFiles,
                             @RequestParam(name = "content") String feedContent,
                             @RequestParam(name = "diet", required = false) List<String> feedTags,
                             @ModelAttribute("user") UserDTO user) throws IOException {

        // 입력 값 검증
        if (feedContent == null || feedContent.trim().isEmpty()) {
            throw new IllegalArgumentException("피드 내용은 필수입니다.");
        }

        // 피드 파일이 비어 있는 경우 예외 처리
        if (feedFiles == null || feedFiles.isEmpty()) {
            throw new IllegalArgumentException("피드는 최소 1개의 파일을 포함해야 합니다.");
        }

        // 태그 목록이 비어 있으면 빈 리스트로 처리
        if (feedTags == null) {
            feedTags = new ArrayList<>();
        }

        // 디버깅 출력
        System.out.println("+++++++++++++++++++++++++ Controller Data start +++++++++++++++++++++++++++");
        System.out.println("피드 내용: " + feedContent);
        System.out.println("피드 파일 내용: " + feedFiles);
        System.out.println("태그 목록: " + String.join(", ", feedTags));
        System.out.println("사용자 키: " + user.getUserKey());
        System.out.println("+++++++++++++++++++++++++ Controller Data end +++++++++++++++++++++++++++");

        // 파일 업로드 서비스 호출하여 파일 처리
        List<FeedfileDTO> feedFileDTOList = fileUploadService.uploadFiles(feedFiles);

        // 서비스 로직 호출
        feedService.uploadFeed(user.getUserKey(), feedContent, String.join(",", feedTags), feedFileDTOList);

        return "redirect:/feed/"; // 피드 등록 후 메인 화면으로 리다이렉트
    }
}