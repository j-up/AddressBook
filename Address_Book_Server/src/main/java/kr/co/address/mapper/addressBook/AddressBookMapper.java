package kr.co.address.mapper.addressBook;

import java.util.ArrayList;

import kr.co.address.domain.addressBook.AddressBookListModel;
import kr.co.address.domain.addressBook.AddressBookModel;

/**
 * @description 
 */
public interface AddressBookMapper {
	public int doInsert(AddressBookModel param);
	public ArrayList<AddressBookModel> doList(AddressBookModel param);
}
