package kr.co.address.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.common.utils.SHA256Util;
import kr.co.address.domain.user.UserVO;
import kr.co.address.mapper.user.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public int doLogin(UserVO param) {
		param.setPassword(SHA256Util.encrypt(param.getPassword()));
		return userMapper.doLogin(param);
	}
	
	public int doInsert(UserVO param) {
		param.setPassword(SHA256Util.encrypt(param.getPassword()));
		return userMapper.doInsert(param);
	}
	
	public int doIdCheck(UserVO param) {
		param.setPassword(SHA256Util.encrypt(param.getPassword()));
		return userMapper.doIdCheck(param);
	}
}
