package com.ssafy.board.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.CommentDto;

@Mapper
public interface CommentMapper {
	List<CommentDto> commentlist(int bno);
	int insert(CommentDto dto);
	int modify(CommentDto dto);
	int remove(int cno);
}
