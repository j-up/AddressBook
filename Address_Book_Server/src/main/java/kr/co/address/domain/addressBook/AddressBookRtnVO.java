package kr.co.address.domain.addressBook;

import java.util.ArrayList;

import kr.co.address.domain.CommonApiVO;

public class AddressBookRtnVO extends CommonApiVO{
	private ArrayList<AddressBookVO> addressBookVoList;

	public ArrayList<AddressBookVO> getAddressBookVoList() {
		return addressBookVoList;
	}

	public void setAddressBookVoList(ArrayList<AddressBookVO> addressBookVoList) {
		this.addressBookVoList = addressBookVoList;
	}
}
