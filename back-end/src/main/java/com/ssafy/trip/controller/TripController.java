package com.ssafy.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.dto.DetailCommentDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.service.TripService;

@RestController
@RequestMapping("/trip")
@CrossOrigin("*")
public class TripController {

	@Autowired
	private TripService ts;

	// detail
	@GetMapping("/detail")
	public TripDto detail(int content_id) {
		ts.updateHit(content_id);
		return ts.detail(content_id);
	}
	
	@GetMapping("/preferdetail/{title}")
	public int preferworddetail(@PathVariable("title") String title) {
		return ts.preferworddetail(title);
	}

	// trip 목록 list

	@GetMapping("/list")
	public List<TripDto> list(TripDto tripdto) {
		return ts.list(tripdto);
	}
	
	@GetMapping("/toplist")
	public List<TripDto> topCardList() {
		return ts.topCardList();
	}
	
	@GetMapping("/comment/{content_id}")
	public List<DetailCommentDto> clist(@PathVariable int content_id) {
		return ts.commentlist(content_id);
	}
	
	@PostMapping("/comment")
	public int cwrite(@RequestBody DetailCommentDto dto) {
		return ts.commentinsert(dto);
	}
	
	@PutMapping("/comment")
	public int cmodify(@RequestBody DetailCommentDto dto) {
		return ts.commentmodify(dto);
	}
	
	@DeleteMapping("/comment/{dno}")
	public int cdelete(@PathVariable int dno) {
		return ts.commentremove(dno);
	}
}
