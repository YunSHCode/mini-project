package com.playdata.miniproject.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("groupfile")
public class GroupFileDTO {
    private String originalFilename;//원본파일명
    private String storeFilename;//실제저장될파일명
}
