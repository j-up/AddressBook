package kr.co.address.domain.addressBook;

import java.io.Serializable;

public class AddressBookVO implements Serializable {
	private static final long serialVersionUID = 2421823L;
	
	private String id;
	private String name;
	private String phone;
	private String userGroup;
	private String bookmark;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getBookmark() {
		return bookmark;
	}
	public void setBookmark(String bookmark) {
		this.bookmark = bookmark;
	}
	public String toString() {
		return name +" " + phone + " " + userGroup + " " + bookmark;  
	}
}
