package com.playdata.miniproject.community.controller;

import com.playdata.miniproject.community.dao.CommunityDAO;
import com.playdata.miniproject.community.dto.CommunityDTO;
import com.playdata.miniproject.community.service.CommunitySevice;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
public class CommunityController {

    private final CommunitySevice communitySevice;

    @GetMapping
    public String community(Model model) {
        List<CommunityDTO> communities = communitySevice.getCommunityList();
        model.addAttribute("communitylist", communities);
        System.out.println(communities);
        System.out.println("메소드목록로들어갔습니다");
        System.out.println("커뮤니티 목록 불러오기 성공: " + communities);
        return "community/community";
    }

    @GetMapping("/read")
    public String readCommunity(Model model) {
        CommunityDTO communityDTO = communitySevice.getCommunityById(1);
        model.addAttribute("community", communityDTO);
        System.out.println(communityDTO);
        System.out.println("메소드상세페이지로 들어갔습니다");
        System.out.println("커무니티 상세 불러오기: " + communityDTO);
        return "community/read";
    }


  //  @GetMapping("/lastt")
   // public String last(Model model) {
   //    CommunityDAO communityService = null;
     //   int communityId = communityService.registerCommunity(newCommunity);
     //   model.addAttribute("communityId", communityId);
     //   model.addAttribute("message", "새로운 커뮤니티가 생성되었습니다!");
       // System.out.println(communityId);
       // System.out.println("메소드가 호출 되었스비낟");
       // System.out.println("모임등록 성공");

       // return "/community/last";
    }



//    @Autowired
//    private CommunitySevice communitySevice;
//    @GetMapping("/")
//    public String community() {
//        System.out.println("컨트롤러의 메소드호출");
//        //서비스의 메소드를 호출
//        communitySevice.registerCommunity(null);
//        return "community/community";
//    }


//@GetMapping("/{id}")
//    public String readCommunity(@PathVariable int id, Model model) {
//        CommunityDTO community = communityService.getCommunityById(id);
//        model.addAttribute("community", community);
//        return "community/read";
//    }

