package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import com.playdata.miniproject.cafe.dto.ReservationRequest;
import com.playdata.miniproject.cafe.dto.ReservationSuccess;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReservationService {
    CafeDTO selectById(int cafeId);
    List<MenuDTO> menuListByCafe(int cafeId);

    int insertReservation(ReservationRequest request);

    ReservationSuccess getReservationById(int reservationId);

    Page<ReservationSuccess> getReservationsByUser(int userKey, int reservationPage, int reservationSize);

    void cancelReservation(int reservationId);
}
