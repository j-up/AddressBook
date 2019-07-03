package kr.co.address.service.addressBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.addressBook.AddressBookListVO;
import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.mapper.addressBook.AddressBookMapper;

@Service
public class AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	public int doInsert(ArrayList<AddressBookVO> param) {
		for(AddressBookVO addressBookVo : param) {
			addressBookMapper.doInsert(addressBookVo);
		} 
		
		return 1;
	}
	
	public AddressBookListVO doList(AddressBookVO param) {
		AddressBookListVO addressBookListVo = new AddressBookListVO();
		addressBookListVo.setAddressBookListVo(addressBookMapper.doList(param));
		return addressBookListVo;
	}
}
