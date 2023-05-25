package com.ssafy.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.dto.PreferDto;
import com.ssafy.user.model.dto.UserDto;
import com.ssafy.user.model.service.JwtServiceImpl;
import com.ssafy.user.model.service.UserService;



@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	// get(select), post(insert), put(update), delete
	
	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private UserService us;

	// 회원가입 할 때
	@PostMapping("/join")
	public int join(@RequestBody UserDto userdto) {
		return us.insert(userdto);
	}

	// 로그인 할 때
	@PostMapping("/login")
	public ResponseEntity<Map<String,Object>> login(@RequestBody UserDto user, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			UserDto loginUser = us.searchOne(user.getId(), user.getPw());
			if (loginUser != null) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getId());// key, data
				us.saveRefreshToken(user.getId(), refreshToken);
				logger.debug("유저 아이디 : {}", user.getId());
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@GetMapping("/info/{id}") //회원 인증
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("id") String id,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userid);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto user = us.userInfo(id);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	// 로그아웃 할 때
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			us.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, user);
		if (jwtService.checkToken(token)) {
			logger.debug("userid : {}", us.getRefreshToken(user.getId()));
			if (token.equals(us.getRefreshToken(user.getId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@DeleteMapping("/delete/{userid}")
	public int delete(@PathVariable("userid") String userid ) {
		return us.delete(userid);
	}//회원 탈퇴 구현 

	@PutMapping
	public int modify(@RequestBody UserDto userdto) {
		return us.modify(userdto);
	}
	
	@PostMapping("/check")
	public int checkId(@RequestBody UserDto userdto) {
		return us.checkId(userdto.getId());
	}
	
	@PutMapping("/type")
	public int registType(@RequestBody UserDto userdto) {
		return us.registType(userdto);
	}
	
	@GetMapping("/type/{usertype}")
	public List<PreferDto> selectType(@PathVariable("usertype") String usertype) {
		return us.selectType(usertype);
	}

}
