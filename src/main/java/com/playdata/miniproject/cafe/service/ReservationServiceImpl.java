package com.playdata.miniproject.cafe.service;

import com.playdata.miniproject.cafe.dao.ReservationDAO;
import com.playdata.miniproject.cafe.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService{

    private final ReservationDAO reservationDAO;
    @Override
    public CafeDTO selectById(int cafeId) {
        return reservationDAO.selectById(cafeId);
    }

    @Override
    public List<MenuDTO> menuListByCafe(int cafeId) {
        return reservationDAO.menuListByCafe(cafeId);
    }

    @Override
    @Transactional
    public int insertReservation(ReservationRequest request) {
        // 현재 시각 가져오기
        LocalDateTime currentTime = LocalDateTime.now();

        // 사용자가 선택한 수령 시간 (분 단위)
        int pickupTime = Integer.parseInt(request.getPickupTime());
        // 현재 시각에 선택한 분 추가하여 수령 시간 계산
        LocalDateTime pickupDateTime = currentTime.plusMinutes(pickupTime);
        request.setPickupDateTime(pickupDateTime);
        // 1. 예약 정보 저장
        int reservationId = reservationDAO.insertReservation(request);
        System.out.println("reserve Service ~~~ reservationId = " + reservationId);
        // 2. 각 메뉴에 대한 ReservationMenu 데이터 저장
        for (ReservationMenuRequest menu : request.getMenuData()) {
            reservationDAO.insertReservationMenu(reservationId, menu.getMenuId(), menu.getQuantity());
        }

        return reservationId;
    }

    @Override
    @Transactional
    public ReservationSuccess getReservationById(int reservationId) {
        // 1. 예약 정보 조회
        ReservationSuccess reservation = reservationDAO.selectReservationById(reservationId);

        return reservation;
    }

    @Override
    public Page<ReservationSuccess> getReservationsByUser(int userKey, int page, int size) {
        int offset = page * size;
        List<ReservationSuccess> reservations = reservationDAO.getReservationsByUser(userKey, offset, size);
        int total = reservationDAO.countReservationByUser(userKey);
        return new PageImpl<>(reservations, PageRequest.of(page,size), total);
    }
}
