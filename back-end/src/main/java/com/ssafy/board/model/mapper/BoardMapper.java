package com.ssafy.board.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.AlertDto;
import com.ssafy.board.model.dto.BoardDto;

@Mapper
public interface BoardMapper {
	public int insert(BoardDto board);

	public List<BoardDto> list(Map<String, Object> param);

	public int getTotalCount(Map<String, Object> param);

	public BoardDto selectOne(int bno);

	public int modify(BoardDto board);

	public int delete(int bno);
	
	public int insertAlert(AlertDto dto);
	
	public int deleteAlert(int tno);
	
	public List<AlertDto> AlertTradeList(String writer);
	
	public List<AlertDto> AlertTogetList(String writer);
	
	public int updateTradeState(int bno);
	
	public int updatePersonCount(int bno);

}
