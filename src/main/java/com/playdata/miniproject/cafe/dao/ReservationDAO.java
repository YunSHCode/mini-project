package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.*;

import java.util.List;

public interface ReservationDAO {
    CafeDTO selectById(int cafeId);

    List<MenuDTO> menuListByCafe(int cafeId);

    int insertReservation(ReservationRequest request);

    void insertReservationMenu(int reservationId, int menuId, int reservationMenuQuantity);

    ReservationSuccess selectReservationById(int reservationId);

    List<ReservationSuccess> getReservationsByUser(int userKey, int offset, int size);

    int countReservationByUser(int userKey);

    void cancelReservation(int reservationId);
}
