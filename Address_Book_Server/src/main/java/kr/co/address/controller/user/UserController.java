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

	@RequestMapping(value = "/login", method = RequestMethod.PUT)
	@ResponseBody
	public UserVO userPut(@RequestBody UserVO param) {
		if ( StringUtils.isEmpty(param.getId()) || StringUtils.isEmpty(param.getPassword()) ) {
			new HMException(FaultCode.INVLID_REQUEST);
		}
		param = userService.doList(param);
		param.setResultCode("ADR-200");
		param.setResultMessage("Success");
		return param;
	}
}
