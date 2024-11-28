package com.playdata.miniproject.cafe.controller;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cafe/map")
public class MapController {

    private final CafeService cafeService;

    public MapController(CafeService cafeService) {
        this.cafeService = cafeService;
    }
//
//    }

    /*@GetMapping("/")
    public List<CafeDTO> selectmap() {
        List<CafeDTO> cafelist = cafeService.selectmap();
        return cafelist;
    }
    */
    @GetMapping(value = "/" )
    public List<CafeDTO> selectmap() {
        List<CafeDTO> cafelist = cafeService.selectmap();
        System.out.println(cafelist);
        return cafelist;
    }


}
