package kr.co.address.service.addressBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.mapper.addressBook.AddressBookMapper;

@Service
public class AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	public int doInsert(ArrayList<AddressBookVO> param) {
		return addressBookMapper.doInsert(param);
	}
}
