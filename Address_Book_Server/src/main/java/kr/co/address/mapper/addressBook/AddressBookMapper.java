package kr.co.address.mapper.addressBook;

import java.util.ArrayList;

import kr.co.address.domain.addressBook.AddressBookListVO;
import kr.co.address.domain.addressBook.AddressBookVO;

/**
 * @description 
 */
public interface AddressBookMapper {
	public int doInsert(AddressBookVO param);
	public ArrayList<AddressBookVO> doList(AddressBookVO param);
}
