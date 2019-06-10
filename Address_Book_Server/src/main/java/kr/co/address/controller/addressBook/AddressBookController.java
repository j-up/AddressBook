package kr.co.address.controller.addressBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.domain.addressBook.AddressBookRtnVO;
import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.service.addressBook.AddressBookService;

@Controller
public class AddressBookController {
	@Autowired
	AddressBookService addressBookService;
	
	
	/**
	 * @description 연락처 저장
	 * @param String (Json 직렬화)
	 * @return 
	 */

	@RequestMapping(value = "/addressBook/register", method = RequestMethod.POST)
	@ResponseBody
	public void addressBookPost(@RequestBody AddressBookRtnVO param) {
		return addressBookService.doInsert(param.getAddressBookVoList());
		//JSONArray jsonArray = StringUtils.stringToJsonArray(param, "addressMST");
		
		
		
		}
	}

