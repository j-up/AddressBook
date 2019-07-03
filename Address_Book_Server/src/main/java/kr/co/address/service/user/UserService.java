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
	
	public UserVO doList(UserVO param) {
		return userMapper.doList(param);
	}
	
	public int doInsert(UserVO param) {
		return userMapper.doInsert(param);
	}
	
	public int doIdCheck(UserVO param) {
		//param.setPassword(SHA256Util.encrypt(param.getPassword()));
		return userMapper.doIdCheck(param);
	}
}
