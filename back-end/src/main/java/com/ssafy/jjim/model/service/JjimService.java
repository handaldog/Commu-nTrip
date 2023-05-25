package com.ssafy.jjim.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.jjim.model.dto.JjimDto;
import com.ssafy.jjim.model.mapper.JjimMapper;

@Service
public class JjimService {

	@Autowired
	private JjimMapper jm;
	// 찜버튼 누르면 데이터 베이스에 저장.

	public void insert(JjimDto jjimdto) {
		jm.insert(jjimdto);
	}

	// 찜목록 아이디에 맞는 것들만 리스트로 뽑기
	public List<JjimDto> list(String id) {
		System.out.println(jm.list(id).toString());
		
		return jm.list(id);
	}

	// 찜 목록 삭제

	public int delete(int jno) {
		return jm.delete(jno);
	}

}
