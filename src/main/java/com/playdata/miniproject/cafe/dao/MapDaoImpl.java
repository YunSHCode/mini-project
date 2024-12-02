package com.playdata.miniproject.cafe.dao;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.mapper.MapMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MapDaoImpl implements MapDAO{
private final MapMapper mapper;

    @Override
    public List<CafeDTO> selectmap() {
        List<CafeDTO> cafelist = mapper.selectmap();
        System.out.println("---------------");
        System.out.println(cafelist);
        return cafelist;
    }
}
