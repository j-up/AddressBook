package kr.co.address.mapper.user;

import kr.co.address.domain.user.UserVO;

/**
 * @description 
 */
public interface UserMapper {
	public int doLogin(UserVO param);
	public int doIdCheck(UserVO param);
	public int doInsert(UserVO param);
}
