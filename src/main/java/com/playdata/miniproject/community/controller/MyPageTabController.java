package com.playdata.miniproject.community.controller;

import com.playdata.miniproject.board.dto.BoardWithUserDTO;
import com.playdata.miniproject.board.service.BoardService;
import com.playdata.miniproject.cafe.dto.ReservationSuccess;
import com.playdata.miniproject.cafe.service.ReservationService;
import com.playdata.miniproject.community.dto.GroupListResponse;
import com.playdata.miniproject.community.service.GroupService;
import com.playdata.miniproject.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@RequestMapping("/user/mypage")
@Controller
@RequiredArgsConstructor
public class MyPageTabController {

    private final BoardService boardService;
    private final ReservationService reservationService;
    private final GroupService groupService;

    @GetMapping("/posts")
    public String getPosts(@SessionAttribute("user") UserDTO user,
                           @RequestParam(name = "page", defaultValue = "0") int boardPage,
                           @RequestParam(defaultValue = "10") int boardSize,
                           Model model) {
        System.out.println("컨트롤러로 게시글 조회 요청 들어옴");
        Page<BoardWithUserDTO> myBoardPage = boardService.getBoardByUser(user.getUserKey(), boardPage, boardSize);
        model.addAttribute("myBoardPage", myBoardPage);
        System.out.println("myBoardPage = " + myBoardPage.getContent());
        return "user/fragments/posts :: postTable";
    }

    @GetMapping("/reservations")
    public String getReservations(@SessionAttribute("user") UserDTO user,
                                  @RequestParam(defaultValue = "0") int reservationPage,
                                  @RequestParam(defaultValue = "10") int reservationSize,
                                  Model model) {
        Page<ReservationSuccess> myReservationsPage = reservationService.getReservationsByUser(user.getUserKey(), reservationPage, reservationSize);
        model.addAttribute("myReservationsPage", myReservationsPage);
        System.out.println("myReservationsPage.getContent() = " + myReservationsPage.getContent());
        return "user/fragments/reservations :: reservationTable";
    }

    @GetMapping("/my-groups")
    public String getGroups(@SessionAttribute("user") UserDTO user,
                            @RequestParam(defaultValue = "0") int groupPage,
                            @RequestParam(defaultValue = "10") int groupSize,
                            Model model) {
        Page<GroupListResponse> myCreatedGroups = groupService.findMyCreatedGroups(user.getUserKey(), groupPage, groupSize);
        model.addAttribute("myCreatedGroups", myCreatedGroups);
        return "user/fragments/mygroups :: myGroupsTable";
    }

    @GetMapping("/participated-groups")
    public String getParticipatedGroups(@SessionAttribute("user") UserDTO user,
                                        @RequestParam(defaultValue = "0") int groupPage,
                                        @RequestParam(defaultValue = "10") int groupSize,
                                        Model model){
        Page<GroupListResponse> myParticipatedGroups = groupService.findMyGroups(user.getUserKey(), "참가", groupPage, groupSize);
        model.addAttribute("myParticipatedGroups", myParticipatedGroups);
        return "user/fragments/myparticipatedgroups :: myParticipatedGroupsTable";
    }

    @GetMapping("/pending-groups")
    public String getPendingGroups(@SessionAttribute("user") UserDTO user,
                                   @RequestParam(defaultValue = "0") int groupPage,
                                   @RequestParam(defaultValue = "10") int groupSize,
                                   Model model) {
        Page<GroupListResponse> myPendingGroups = groupService.findMyGroups(user.getUserKey(), "신청", groupPage, groupSize);
        model.addAttribute("myPendingGroups", myPendingGroups);
        return "user/fragments/mypendinggroups :: myPendingGroupsTable";
    }

}
