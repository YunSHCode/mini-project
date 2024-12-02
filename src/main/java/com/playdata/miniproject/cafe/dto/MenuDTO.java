package com.playdata.miniproject.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MenuDTO {
    int menuId;
    String menuName;
    int menuPrice;
    String menuPictureGenerated;
    int cafeId;
}
