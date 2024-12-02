package com.playdata.miniproject.cafe.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReservationRequest {
    int reservationId;
    int userKey;
    int cafeId;
    String pickupTime;
    LocalDateTime pickupDateTime;
    List<ReservationMenuRequest> menuData;
}
