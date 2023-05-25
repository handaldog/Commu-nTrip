package com.ssafy.jjim.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.jjim.model.dto.JjimDto;

@Mapper
public interface JjimMapper {

	// 찜버튼 누르면 데이터 베이스에 저장.

	public int insert(JjimDto jjimdto);

	// 찜목록 아이디에 맞는 것들만 리스트로 뽑기
	public List<JjimDto> list(String id);

	// 찜 목록 삭제

	public int delete(int jno);

}
