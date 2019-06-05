package kr.co.address.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.UserVO;
import kr.co.address.mapper.user.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public UserVO doList(UserVO param) {
		UserVO userVO = new UserVO();
		userVO = userMapper.doList(param);
		return userVO;
	}
}
