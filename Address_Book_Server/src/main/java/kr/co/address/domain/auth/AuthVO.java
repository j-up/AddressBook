package kr.co.address.domain.auth;

import java.io.Serializable;

public class AuthVO implements Serializable {
	private static final long serialVersionUID = 23412781L;

	private String basic_auth_id;
	private String basic_auth_pw;
	
	public String getBasic_auth_id() {
		return basic_auth_id;
	}
	public void setBasic_auth_id(String basic_auth_id) {
		this.basic_auth_id = basic_auth_id;
	}
	public String getBasic_auth_pw() {
		return basic_auth_pw;
	}
	public void setBasic_auth_pw(String basic_auth_pw) {
		this.basic_auth_pw = basic_auth_pw;
	}

}
