package kr.co.address.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.common.exception.FaultCode;
import kr.co.address.common.exception.HMException;
import kr.co.address.common.utils.StringUtils;
import kr.co.address.domain.user.UserVO;
import kr.co.address.service.user.UserService;

/**
 * @description User CRUD 관련 Controller
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * @description 로그인
	 * @param UserVO
	 * @return UserVO
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public UserVO loginPost(@RequestBody UserVO param) {
		if ( StringUtils.isEmpty(param.getEmail()) || StringUtils.isEmpty(param.getPassword()) ) {
			new HMException(FaultCode.INVLID_REQUEST);
		}
		param = userService.doList(param);
		return param;
	}
	
	/**
	 * @description 로그인
	 * @param UserVO
	 * @return UserVO
	 */
	@RequestMapping(value = "/id/check", method = RequestMethod.POST)
	@ResponseBody
	public int idCheckPost(@RequestBody UserVO param) {
		if ( StringUtils.isEmpty(param.getEmail()) ) {
			new HMException(FaultCode.INVLID_REQUEST);
		}
		return userService.doIdCheck(param);
	}
	
	/**
	 * @description 회원가입 
	 * @param UserVO
	 * @return int
	 */
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	@ResponseBody
	public int signupPost(@RequestBody UserVO param) {
		if ( StringUtils.isEmpty(param.getEmail()) || StringUtils.isEmpty(param.getPassword()) ) {
			new HMException(FaultCode.INVLID_REQUEST);
		}
		
		return userService.doInsert(param);
	}
}
