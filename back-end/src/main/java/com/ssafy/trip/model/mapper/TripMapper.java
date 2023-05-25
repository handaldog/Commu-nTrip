package com.ssafy.trip.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.trip.model.dto.TripDto;

@Mapper
public interface TripMapper {

	public List<TripDto> list(TripDto tripdto);

	public TripDto detail(int content_id);

	public int preferworddetail(String title);

	public int updateHit(int content_id);

	public List<TripDto> topCardList();

}
