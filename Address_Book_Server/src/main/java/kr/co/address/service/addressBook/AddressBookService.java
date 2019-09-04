package kr.co.address.service.addressBook;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.domain.addressBook.AddressBookListModel;
import kr.co.address.domain.addressBook.AddressBookModel;
import kr.co.address.mapper.addressBook.AddressBookMapper;

@Service
public class AddressBookService {

	@Autowired
	private AddressBookMapper addressBookMapper;
	
	public int doInsert(ArrayList<AddressBookModel> param) {
		for(AddressBookModel addressBookModel : param) {
			addressBookMapper.doInsert(addressBookModel);
		} 
		
		return 1;
	}
	
	public AddressBookListModel doList(AddressBookModel param) {
		AddressBookListModel addressBookListModel = new AddressBookListModel();
		addressBookListModel.setAddressBookListModel(addressBookMapper.doList(param));
		return addressBookListModel;
	}
}
