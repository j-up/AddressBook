package kr.co.address.domain.addressBook;

import java.io.Serializable;
import java.util.ArrayList;


public class AddressBookListVO implements Serializable{

	private static final long serialVersionUID = 88593112L;
	private ArrayList<AddressBookVO> addressBookListVo;

	public ArrayList<AddressBookVO> getAddressBookListVo() {
		return addressBookListVo;
	}

	public void setAddressBookListVo(ArrayList<AddressBookVO> addressBookListVo) {
		this.addressBookListVo = addressBookListVo;
	}
}
