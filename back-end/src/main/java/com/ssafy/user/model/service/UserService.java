package com.ssafy.user.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.user.model.dto.PreferDto;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper um;

	public UserDto searchOne(String id, String pw) {
		return um.searchOne(id, pw);
	}

	public int insert(UserDto userdto) {
		return um.insert(userdto);
	}

	public int delete(String id) {
		return um.delete(id);
	}

	public int modify(UserDto dto) {
		return um.modify(dto);

	}
	
	public int checkId(String id) {
		return um.checkId(id);
	}
	
	public int registType(UserDto dto) {
		return um.registType(dto);
	}
	
	public UserDto userInfo(String userid) throws Exception {
		return um.userInfo(userid);
	}
	
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		um.saveRefreshToken(map);
	}
	
	public Object getRefreshToken(String userid) throws Exception {
		return um.getRefreshToken(userid);
	}
	
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		um.deleteRefreshToken(map);
	}
	
	public List<PreferDto> selectType(String usertype) {
		return um.selectType(usertype);
	}

}
