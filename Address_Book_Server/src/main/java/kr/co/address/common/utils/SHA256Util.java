package kr.co.address.common.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SHA256Util {
	private static Logger logger = LoggerFactory.getLogger(SHA256Util.class);

	public static String encrypt(String base) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(base.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();

			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			
			return hexString.toString();
		} catch(Exception e) {
			logger.error("SHA-256 encrypt error : " + e.getMessage());
			return null;
		}
	}
}
