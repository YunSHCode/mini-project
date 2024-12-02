package com.playdata.miniproject.cafe.mapper;

import com.playdata.miniproject.cafe.dto.CafeDTO;
import com.playdata.miniproject.cafe.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface MapMapper {
    List<CafeDTO> selectmap();
    List<MenuDTO> selectMenuDU();

}
