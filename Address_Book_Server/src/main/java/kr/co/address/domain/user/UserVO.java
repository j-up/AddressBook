package kr.co.address.domain.user;

import java.io.Serializable;


public class UserVO implements Serializable {

	private static final long serialVersionUID = 14235328L;

	private String email;
	private String password;
	private String create_date;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}