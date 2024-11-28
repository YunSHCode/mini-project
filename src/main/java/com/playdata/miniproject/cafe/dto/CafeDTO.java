package com.playdata.miniproject.cafe.dto;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CafeDTO {
    int cafeId;
    String cafeName;
    String cafeAddr;
    double cafeLatitude;
    double cafeLongtitude;
    String cafeNo;
    LocalTime cafeOpenHour;
    LocalTime cafeCloseHour;
    String cafePictureOriginal;
    String cafePictureGenerated;


}
