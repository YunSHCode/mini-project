package com.playdata.miniproject.cafe.dto;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CafeDTO {
    private int cafe_id;
    private  String cafe_name;
    private  String cafe_addr;
    private  double cafe_latitude;
    private  double cafe_longtitude;
    private short cafe_no;
    private LocalTime cafe_open_hour;
    private LocalTime cafe_close_hour;
    private long cafe_picture_original;
    private String cafe_picture_generated;
    private int menu_id;
    private String menu_name;
    private int menu_price;
    private String menu_picture_original;
    private String menu_picture_generated;


}
