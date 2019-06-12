package kr.co.address.service.addressBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.CommonApiVO;
import kr.co.address.domain.addressBook.AddressBookRtnVO;
import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.mapper.addressBook.AddressBookMapper;

@Service
public class AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	public CommonApiVO doInsert(ArrayList<AddressBookVO> param) {
		for(AddressBookVO addressBookVo : param) {
			addressBookMapper.doInsert(addressBookVo);
		} 
		CommonApiVO commonApiVo = new CommonApiVO();
		commonApiVo.setSuccess();
		
		return commonApiVo;
	}
	
	public AddressBookRtnVO doList(AddressBookVO param) {
		AddressBookRtnVO addressBookRtnVo = new AddressBookRtnVO();
		addressBookRtnVo.setAddressBookVoList(addressBookMapper.doList(param));
		addressBookRtnVo.setSuccess();
		return addressBookRtnVo;
	}
}
