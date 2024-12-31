package com.playdata.miniproject.cafe.controller;

import com.playdata.miniproject.cafe.dto.ReservationRequest;
import com.playdata.miniproject.cafe.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cafe")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    @PostMapping("/reservation.do")
    public ResponseEntity<Map<String, String>> makeReservation(@RequestBody ReservationRequest request) {
        // request 객체를 사용하여 예약 처리 로직 구현
        int reservationId = reservationService.insertReservation(request);
        reservationId = request.getReservationId();
        System.out.println("request = " + request.toString());

        // 예약 성공 후 이동할 URL 생성
        String redirectUrl = "/cafe/reservation/success?reservationId=" + reservationId;

        // 응답 데이터 구성
        Map<String, String> response = new HashMap<>();
        response.put("message", "예약이 성공적으로 완료되었습니다.");
        response.put("redirectUrl", redirectUrl);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancelReservation/{reservationId}")
    public ResponseEntity<Void> cancelReservation(@PathVariable() int reservationId) {
        reservationService.cancelReservation(reservationId); // 예약 취소 비즈니스 로직
        return ResponseEntity.ok().build();
    }

}
