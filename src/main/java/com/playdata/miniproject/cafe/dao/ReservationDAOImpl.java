package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.*;
import com.playdata.miniproject.cafe.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReservationDAOImpl implements ReservationDAO{
    private final ReservationMapper mapper;

    @Override
    public CafeDTO selectById(int cafeId) {
        return mapper.cafeById(cafeId);
    }

    @Override
    public List<MenuDTO> menuListByCafe(int cafeId) {
        return mapper.menuListByCafe(cafeId);
    }

    @Override
    public int insertReservation(ReservationRequest request) {
        return mapper.insertReservation(request);
    }

    @Override
    public void insertReservationMenu(int reservationId, int menuId, int reservationMenuQuantity) {
        mapper.insertReservationMenu(reservationId, menuId, reservationMenuQuantity);
    }

    @Override
    public ReservationSuccess selectReservationById(int reservationId) {
        return mapper.selectReservationById(reservationId);
    }

    @Override
    public List<ReservationSuccess> getReservationsByUser(int userKey, int offset, int size) {
        return mapper.getReservationByUser(userKey, offset, size);
    }

    @Override
    public int countReservationByUser(int userKey) {
        return mapper.countReservationByUser(userKey);
    }

    @Override
    public void cancelReservation(int reservationId) {
        mapper.cancelReservation(reservationId);
    }
}
