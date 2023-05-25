package com.ssafy.trip.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.DetailCommentDto;

@Mapper
public interface DetailCommentMapper {
	List<DetailCommentDto> commentlist(int content_id);
	int insert(DetailCommentDto dto);
	int modify(DetailCommentDto dto);
	int remove(int dno);
}
