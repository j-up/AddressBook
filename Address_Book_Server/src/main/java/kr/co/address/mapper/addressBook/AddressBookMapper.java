package kr.co.address.mapper.addressBook;

import kr.co.address.domain.AddressBookVO;
import kr.co.address.domain.UserVO;

/**
 * @description 
 */
public interface AddressBookMapper {
	public AddressBookVO doList(AddressBookVO param);
	public int doInsert(AddressBookVO param);
	
}
