package kr.co.address.controller.addressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.common.exception.ExHandler;
import kr.co.address.common.exception.FaultCode;
import kr.co.address.domain.addressBook.AddressBookListVO;
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
	 * @param AddressBookListVO (ArrayList<AddressBookVO>)
	 * @return CommonApiVO
	 */

	@RequestMapping(value = "/addressBook/register", method = RequestMethod.POST)
	@ResponseBody
	public int setAddressBook(@RequestBody AddressBookListVO param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		
		return addressBookService.doInsert(param.getAddressBookListVo()); 
		}
	
	
	/**
	 * @description 연락처 조회 
	 * @param AddressBookVO 
	 * @return AddressBookRtnVO
	 */
	
	@RequestMapping(value = "/addressBook/search", method = RequestMethod.POST)
	@ResponseBody
	public AddressBookListVO getAddressBook(@RequestBody AddressBookVO param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		// AddressBookListVO
		return addressBookService.doList(param);
		}
	
	
	}

