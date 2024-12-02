package com.playdata.miniproject.cafe.dto;

import lombok.Data;

@Data
public class ReservationMenuSuccess {
    int menuId;
    String menuName;
    int reservationMenuQuantity;
    String menuPictureGenerated;
    int menuPrice;
}
