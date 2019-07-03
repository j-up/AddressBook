package kr.co.address.service.auth;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.address.common.exception.ExHandler;
import kr.co.address.common.exception.FaultCode;
import kr.co.address.domain.auth.AuthVO;
import kr.co.address.mapper.auth.AuthMapper;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class AuthService {
	@Autowired
	CacheManager  cacheManager;

	@Autowired
	AuthMapper authMapper;
	
	Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	
	/**
	* @description		접속 권한 관련 캐시 작업    
	*/
	@PostConstruct
	public void initCache() {
		
		logger.info("START AuthCache CACHE Load Start");
		
		//authKey
		
		ArrayList<AuthVO> authList = doList(new AuthVO());
		Cache cache = cacheManager.getCache("authCache");
		cache.removeAll();
		String basic_auth_id = "";
		String basic_auth_pw = "";
		
		for (AuthVO item : authList) {

			basic_auth_id = item.getBasic_auth_id();
			basic_auth_pw = item.getBasic_auth_pw();
		}
		
		cache.put(new Element("basic_auth_id", basic_auth_id));
		cache.put(new Element("basic_auth_pw", basic_auth_pw));
		
		logger.info("Total Cache {} loaded",cache.getSize());
		
	}
	
	/**
	* @description		
	* @param 			AuthVO
	* @return			ArrayList<AuthVO> 
	*/
	public ArrayList<AuthVO> doList(AuthVO param) {

		ArrayList<AuthVO> rtnList = authMapper.doList(param);

		return rtnList;
	}
	
	/**
	* @description		header로 넘어온 auth값 체크   
	* @param 			String authKey
	* @return			String 
	*/
	public String checkAuthKey(AuthVO param) {
		
		Cache cache = cacheManager.getCache("authCache");
		String result = "";
		ExHandler.exceptionMsg(param.getBasic_auth_id(),FaultCode.AUTH_ERROR,"basic_auth_id");
		ExHandler.exceptionMsg(param.getBasic_auth_pw(),FaultCode.AUTH_ERROR,"basic_auth_pw");
	
		if(param.getBasic_auth_id().equals(cache.get("basic_auth_id").getObjectValue())) {
			
			if(param.getBasic_auth_pw().equals(cache.get("basic_auth_pw").getObjectValue())) {
				result = (String) cache.get("basic_auth_pw").getObjectValue();
			}
		}
		
		return result;
		
	}
}
