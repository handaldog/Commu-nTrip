package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.AlertDto;
import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.dto.CommentDto;
import com.ssafy.board.model.service.BoardService;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
public class BoardController {
	@Autowired
	public BoardService service;
	
	@GetMapping
	public List<BoardDto> list(@RequestParam Map<String, String> map) throws Exception {
		return service.listAll(map);
	}
	
	@GetMapping("/{bno}")
	public BoardDto view(@PathVariable int bno) throws Exception {
		return service.read(bno);
	}
	
	@PutMapping
	public int modify(@RequestBody BoardDto boardDto) throws Exception {
		return service.modify(boardDto);
	}
	
	@DeleteMapping("/{bno}")
	public int delete(@PathVariable int bno) throws Exception {
		return service.remove(bno);
	}
	
	@PostMapping
	public int write(@RequestBody BoardDto boardDto) throws Exception {
		return service.regist(boardDto);
	}
	
	@PostMapping("/comment")
	public int cwrite(@RequestBody CommentDto comm) throws Exception {
		return service.commentinsert(comm);
	}
	
	@GetMapping("/comment/{bno}")
	public List<CommentDto> clist(@PathVariable int bno) throws Exception{
		return service.commlist(bno);
	}
	
	@PutMapping("/comment") 
	public int cmodify(@RequestBody CommentDto comm) throws Exception {
		return service.commentmodify(comm);
	}
	
	@DeleteMapping("/comment/{cno}")
	public int cdelete(@PathVariable int cno) throws Exception {
		return service.commentremove(cno);
	}
	
	@PostMapping("/alert")
	public int insertAlert(@RequestBody AlertDto dto) throws Exception {
		return service.insertAlert(dto);
	}
	
	@DeleteMapping("/alert/{tno}")
	public int deleteAlert(@PathVariable int tno) throws Exception {
		return service.deleteAlert(tno);
	}
	
	@GetMapping("/alert/trade/{writer}")
	public List<AlertDto> alertTradeList(@PathVariable String writer) throws Exception {
		return service.alertTradeList(writer);
	}
	
	@GetMapping("/alert/toget/{writer}")
	public List<AlertDto> alertTogetList(@PathVariable String writer) throws Exception {
		return service.alertTogetList(writer);
	}
	
	@PutMapping("/count/{bno}")
	public int updateCount(@PathVariable int bno) throws Exception {
		return service.updateCount(bno);
	}
	
	@PutMapping("/state/{bno}")
	public int updateState(@PathVariable int bno) throws Exception {
		return service.updateState(bno);
	}
	
}
