package kr.co.address.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.domain.UserVO;
import kr.co.address.service.user.UserService;

/** 
 * @description User CRUD 관련 Controller 		
 */
@Controller
public class UserController {
	
	@Autowired
	UserService userService; 
	
	/**
	* @description		정기권 조회    
	* @param 			CustdefVO
	* @return			ResponseGetCustdefVO 
	*/
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public UserVO userLogin(@RequestBody UserVO param) {
		return userService.doList(param);
	}
	
	
	
	/**
	* @description		정기권 등록    
	* @param 			CustdefVO
	* @return			ResponseSetCustdefVO
	* @develop    		minjae_kang, 2019. 5. 22. 
	*/
	/*
	@RequestMapping(value="/if1200/v1/setCustdefList", method=RequestMethod.POST)
	@ResponseBody
	public ResponseSetCustdefVO setCustdefList(@RequestBody CustdefVO param) throws Exception{
		
		param.setDat_falg(ProcCommonApi.INSERT_CUSTDEF);
		
		return custdefService.doProcessMst(param);
	} */
	
	/**
	* @description	 	정기권 수정    
	* @param 			CustdefVO
	* @return			CommonApiVO
	* @develop    		minjae_kang, 2019. 5. 22. 
	*/
	/*
	@RequestMapping(value="/if1300/v1/modifyCustdefList", method=RequestMethod.POST)
	@ResponseBody
	public CommonApiVO modifyCustdefList(@RequestBody CustdefVO param) throws Exception {
		
		param.setDat_falg(ProcCommonApi.UPDATE_CUSTDEF);
		
		return custdefService.doProcessMst(param);
	} /*
	
	/**
	* @doc 				POST IF_PARK_1400 /kr.co.amano.controller.custdef/if1100/v1/deleteCustdefList
	* @description		정기권 삭제
	* @param 			CustdefVO
	* @return			CommonApiVO
	* @develop    		minjae_kang, 2019. 5. 22. 
	*/ /*
	@RequestMapping(value="/if1400/v1/deleteCustdefList", method=RequestMethod.POST)
	@ResponseBody
	public CommonApiVO deleteCustdefList(@RequestBody CustdefVO param) throws Exception {
		
		param.setDel_falg(ProcCommonApi.DELETE_CUSTDEF);
		
		return custdefService.doProcessMst(param);
	} */
}
