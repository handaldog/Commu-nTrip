package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dto.AlertDto;
import com.ssafy.board.model.dto.BoardDto;
import com.ssafy.board.model.dto.CommentDto;
import com.ssafy.board.model.mapper.BoardMapper;
import com.ssafy.board.model.mapper.CommentMapper;
import com.ssafy.util.PageNavigation;
import com.ssafy.util.SizeConstant;

@Service
public class BoardService {
	@Autowired
	public BoardMapper dao;
	
	@Autowired
	public CommentMapper cdao;
	
	public List<BoardDto> listAll(Map<String, String> map) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		String key = map.get("key");
//		if("userid".equals(key))
//			key = "user_id";
		param.put("boardType", map.get("boardType"));
		param.put("key", key.isEmpty() ? "" : key);
		param.put("word", map.get("word").isEmpty() ? "" : map.get("word"));
		return dao.list(param);
	}

	public BoardDto read(int bno) throws Exception {
		return dao.selectOne(bno);
	}

	public int regist(BoardDto dto) throws Exception {
		return dao.insert(dto);
	}
	
	public int modify(BoardDto dto) throws Exception {
		return dao.modify(dto);
	}
	
	public int remove(int bno) throws Exception {
		return dao.delete(bno);
	}
	
	public int commentinsert(CommentDto dto) throws Exception {
		return cdao.insert(dto);
	}
	
	public List<CommentDto> commlist(int bno) throws Exception{
		return cdao.commentlist(bno);
	}
	
	public int commentmodify(CommentDto dto) throws Exception {
		return cdao.modify(dto);
	}
	
	public int commentremove(int cno) throws Exception {
		return cdao.remove(cno);
	}
	
	public int insertAlert(AlertDto dto) throws Exception {
		return dao.insertAlert(dto);
	}
	
	public int deleteAlert(int tno) throws Exception {
		return dao.deleteAlert(tno);
	}
	
	public List<AlertDto> alertTradeList(String writer) throws Exception {
		return dao.AlertTradeList(writer);
	}
	
	public List<AlertDto> alertTogetList(String writer) throws Exception {
		return dao.AlertTogetList(writer);
	}
	
	public int updateCount(int bno) throws Exception {
		return dao.updatePersonCount(bno);
	}
	
	public int updateState(int bno) throws Exception {
		return dao.updateTradeState(bno);
	}
}
