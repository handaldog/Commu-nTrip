package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.user.model.dto.PreferDto;
import com.ssafy.user.model.dto.UserDto;

@Mapper
public interface UserMapper {

	public UserDto searchOne(@Param("id") String id, @Param("pw") String pw);

	public int insert(UserDto dto);

	public int delete(String id);

	public int modify(UserDto dto);
	
	public int checkId(String id);
	
	public int registType(UserDto dto);
	
	public List<PreferDto> selectType(String usertype);
	
	public UserDto userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
