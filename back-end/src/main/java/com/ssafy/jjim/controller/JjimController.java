package com.ssafy.jjim.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.jjim.model.dto.JjimDto;
import com.ssafy.jjim.model.service.JjimService;
import com.ssafy.user.model.dto.UserDto;

@RestController
@RequestMapping("/jjim")
@CrossOrigin("*")
public class JjimController {

	@Autowired
	private JjimService js;

	// 찜목록 리스트 출력, 아이디에 따른.
	@GetMapping("/list")
	public List<JjimDto> list(String id) {

		return js.list(id);
	}

	// 찜하기
	@PostMapping("/write")
	public void write(@RequestBody JjimDto jjimdto, HttpSession session) {
		jjimdto.setId(jjimdto.getId());
		js.insert(jjimdto);
	}

	// 찜 삭제하기
	@DeleteMapping("/{jno}")
	public int delete(@PathVariable int jno) {
		return js.delete(jno);
	}
}
