package com.ssafy.trip.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.dto.DetailCommentDto;
import com.ssafy.trip.model.dto.TripDto;
import com.ssafy.trip.model.mapper.DetailCommentMapper;
import com.ssafy.trip.model.mapper.TripMapper;

@Service
public class TripService {

	@Autowired
	private TripMapper tm;
	
	@Autowired
	private DetailCommentMapper dm;

	public List<TripDto> list(TripDto tripdto) {
		return tm.list(tripdto);

	}
	
	public int updateHit(int content_id) {
		return tm.updateHit(content_id);
	}

	public TripDto detail(int content_id) {
		return tm.detail(content_id);

	}
	
	public int preferworddetail(String title) {
		return tm.preferworddetail(title);
	}
	
	public List<TripDto> topCardList() {
		return tm.topCardList();
	}
	
	public int commentinsert(DetailCommentDto dto) {
		return dm.insert(dto);
	}
	
	public int commentmodify(DetailCommentDto dto) {
		return dm.modify(dto);
	}
	
	public int commentremove(int dno) {
		return dm.remove(dno);
	}
	
	public List<DetailCommentDto> commentlist(int content_id){
		return dm.commentlist(content_id);
	}
}
