package kr.co.address.domain.addressBook;

import java.io.Serializable;
import java.util.ArrayList;


public class AddressBookListModel implements Serializable{

	private static final long serialVersionUID = 88593112L;
	private ArrayList<AddressBookModel> addressBookListModel;

	public ArrayList<AddressBookModel> getAddressBookListModel() {
		return addressBookListModel;
	}

	public void setAddressBookListModel(ArrayList<AddressBookModel> addressBookListModel) {
		this.addressBookListModel = addressBookListModel;
	}
}
