package com.playdata.miniproject.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ReservationMenuRequest {
    int menuId;
    int quantity;
}
