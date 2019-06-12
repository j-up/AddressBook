package kr.co.address.controller.addressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.common.exception.ExHandler;
import kr.co.address.common.exception.FaultCode;
import kr.co.address.domain.CommonApiVO;
import kr.co.address.domain.addressBook.AddressBookRtnVO;
import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.service.addressBook.AddressBookService;

/**
 * @description User CRUD 관련 Controller
 */

@Controller
public class AddressBookController {
	@Autowired
	AddressBookService addressBookService;

	/**
	 * @description 연락처 삽입
	 * @param AddressBookRtnVO (ArrayList<AddressBookVO>)
	 * @return CommonApiVO
	 */

	@RequestMapping(value = "/addressBook/register", method = RequestMethod.POST)
	@ResponseBody
	public CommonApiVO setAddressBook(@RequestBody AddressBookRtnVO param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		
		CommonApiVO commonApiVo = addressBookService.doInsert(param.getAddressBookVoList());
		return commonApiVo;
		}
	
	
	/**
	 * @description 연락처 조회 
	 * @param AddressBookVO 
	 * @return AddressBookRtnVO
	 */
	
	@RequestMapping(value = "/addressBook/search", method = RequestMethod.PUT)
	@ResponseBody
	public AddressBookRtnVO getAddressBook(@RequestBody AddressBookVO param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		
		AddressBookRtnVO addressBookRtnVO = addressBookService.doList(param);
		return addressBookRtnVO;
		}
	
	
	}

