package kr.co.address.mapper.auth;

import java.util.ArrayList;

import kr.co.address.domain.auth.AuthVO;

public interface AuthMapper {
	ArrayList<AuthVO>doList(AuthVO param);
}
