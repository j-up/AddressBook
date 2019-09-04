package kr.co.address.controller.addressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.common.exception.ExHandler;
import kr.co.address.common.exception.FaultCode;
import kr.co.address.domain.addressBook.AddressBookListModel;
import kr.co.address.domain.addressBook.AddressBookModel;
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
	 * @param AddressBookListModel (ArrayList<AddressBookModel>)
	 * @return CommonApiVO
	 */

	@RequestMapping(value = "/addressBook/register", method = RequestMethod.POST)
	@ResponseBody
	public int setAddressBook(@RequestBody AddressBookListModel param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		
		return addressBookService.doInsert(param.getAddressBookListModel()); 
		}
	
	
	/**
	 * @description 연락처 조회 
	 * @param AddressBookModel 
	 * @return AddressBookRtnVO
	 */
	
	@RequestMapping(value = "/addressBook/search", method = RequestMethod.POST)
	@ResponseBody
	public AddressBookListModel getAddressBook(@RequestBody AddressBookModel param) {
		ExHandler.exceptionNullMsg(param, FaultCode.INVLID_REQUEST);
		// AddressBookListVO
		return addressBookService.doList(param);
		}
	
	
	}

