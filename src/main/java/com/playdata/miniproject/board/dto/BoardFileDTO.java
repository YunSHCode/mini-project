package com.playdata.miniproject.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("boardfile")
public class BoardFileDTO {
	private int boardFileId;//식별할 수 있는 번호
	private int boardId;//첨부된 파일이 어떤 게시글의 파일인지 구분하기 위한 게시글번호(tbboard의 board_no)
	private String originalFilename;//원본파일명
	private String storeFilename;//실제저장될파일명
}
