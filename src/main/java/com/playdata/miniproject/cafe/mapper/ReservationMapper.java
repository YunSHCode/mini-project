package com.playdata.miniproject.cafe.mapper;

import com.playdata.miniproject.cafe.dto.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReservationMapper {

    CafeDTO cafeById(int cafeId);

    List<MenuDTO> menuListByCafe(int cafeId);

    int insertReservation(ReservationRequest request);
    void insertReservationMenu(@Param("reservationId") int reservationId,
                               @Param("menuId") int menuId,
                               @Param("reservationMenuQuantity") int reservationMenuQuantity);

    ReservationSuccess selectReservationById(@Param("reservationId") int reservationId);

    List<ReservationSuccess> getReservationByUser(@Param("userKey") int userKey, @Param("offset") int offset, @Param("limit") int limit);

    int countReservationByUser(int userKey);
}
