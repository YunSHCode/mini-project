package com.playdata.miniproject.cafe.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//예약 성공 페이지에 가져올 응답 객체
@Data
public class ReservationSuccess {
    int reservationId;
    String userRealname;
    String cafeName;
    LocalDateTime reservationPickupTime;
    List<ReservationMenuSuccess> menuList;
}
