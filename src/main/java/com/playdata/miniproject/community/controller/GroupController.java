package com.playdata.miniproject.community.controller;

import com.playdata.miniproject.community.dto.*;
import com.playdata.miniproject.community.service.GroupFileService;
import com.playdata.miniproject.community.service.GroupService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupFileService groupFileService;
    private final GroupService groupService;

    @GetMapping("/list")
    public String groupList(@RequestParam(defaultValue = "0") int groupPage,
                            @RequestParam(defaultValue = "10") int groupSize,
                            Model model) {

        Page<GroupListResponse> groupList = groupService.getCommunityList(groupPage, groupSize);

        model.addAttribute("communitylist", groupList.getContent()); // 목록 데이터
        model.addAttribute("currentPage", groupPage); // 현재 페이지
        model.addAttribute("totalPages", groupList.getTotalPages()); // 총 페이지 수
        model.addAttribute("totalItems", groupList.getTotalElements()); // 총 항목 수

        return "community/grouplist";
    }

    @GetMapping("/editform")
    public String editForm() {
        return "community/groupform";
    }

    @PostMapping("/insert")
    public String createCommunity(
            @ModelAttribute GroupRequest groupRequest,
            @RequestParam(value = "communityPicture", required = false) MultipartFile communityPicture,
            @SessionAttribute(value = "user", required = false) UserDTO user,
            RedirectAttributes redirectAttributes) throws IOException {

        if (user == null) {
            redirectAttributes.addFlashAttribute("loginMessage", "로그인 후 이용 가능합니다.");
            return "redirect:/user/login/first";
        }

        // 사진 파일 저장 처리
        GroupFileDTO groupFile = groupFileService.uploadGroupFile(communityPicture);

        groupRequest.setCommunityPictureOriginal(groupFile.getOriginalFilename());
        groupRequest.setCommunityPictureGenerated(groupFile.getStoreFilename());
        groupRequest.setUserKey(user.getUserKey());
        groupService.insertCommunity(groupRequest);

        // 성공 시 리스트 페이지로 리다이렉트
        return "redirect:/group/list";
    }

    @GetMapping("/detail/{id}")
    public String readGroup(@PathVariable("id") int groupId, Model model) {
        GroupDetailResponse group = groupService.getCommunityDetail(groupId);
        model.addAttribute("group", group);
        return "community/groupdetail";
    }

    @GetMapping("/members/{groupId}")
    public ResponseEntity<List<MemberResponse>> getGroupMembers(@PathVariable("groupId") int groupId) {
        List<MemberResponse> members = groupService.getGroupMembers(groupId);

        if (members.isEmpty()) {
            return ResponseEntity.noContent().build(); // 멤버가 없으면 204 응답
        }

        return ResponseEntity.ok(members); // 멤버 목록을 JSON으로 반환
    }

    @PostMapping("/join/{communityId}")
    public ResponseEntity<String> requestToJoin(
            @PathVariable int communityId,
            @SessionAttribute(value = "user", required = false) UserDTO user) {

        // 세션에 로그인 정보가 없으면 요청 거절
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        // 이미 신청 또는 참여 중인지 확인
        boolean alreadyRequested = groupService.isMemberAlreadyRequested(user.getUserKey(), communityId);
        if (alreadyRequested) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 신청 중이거나 참여 중입니다.");
        }

        // 신청 상태로 멤버 추가
        groupService.requestToJoin(user.getUserKey(), communityId);
        return ResponseEntity.ok("참여 요청이 성공적으로 등록되었습니다.");
    }
}
