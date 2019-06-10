package kr.co.address.mapper.addressBook;

import java.util.ArrayList;

import kr.co.address.domain.addressBook.AddressBookVO;
import kr.co.address.domain.user.UserVO;

/**
 * @description 
 */
public interface AddressBookMapper {
	public int doInsert(ArrayList<AddressBookVO> param);
}
