package kr.co.address.service.addressBook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.AddressBookVO;
import kr.co.address.mapper.addressBook.AddressBookMapper;

@Service
public class AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	public int doInsert(AddressBookVO param) {
		return addressBookMapper.doInsert(param);
	}
}
