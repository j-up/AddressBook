package kr.co.address.common.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import kr.co.address.common.exception.HMException;

public class StringUtils extends org.apache.commons.lang3.StringUtils {
	
	public static String decode(String org, String eq, String ret, String els) {
		if ( StringUtils.equals(org, eq) ) {
			return ret;
		}
		return els;
	}
	
	/** 공백문자. */
	public final static char WHITE_SPACE = ' ';
	
	/** The m_white space. */
	static String m_whiteSpace = " \t\n\r";
	
	/** The m_cit char. */
	static char m_citChar = '"';
	
	/**
	 * 실제 바이트 수를 얻는다.
	 * 
	 * @param input
	 *            the input
	 * 
	 * @return int
	 */
	public static int getLength(String input) {
		byte[] bytes = input.getBytes();
		return bytes.length;
	}
	
	public static String convertCardNo(String carNo) {
		// String org_card_no = carNo.substring(0, carNo.indexOf("="));
		return carNo.substring(0, 8) + "****" + carNo.substring(12, carNo.length());
		
	}
	
	/**
	 * <pre>
	 * Oracle 문자열 함수 LPAD와 같은 기능
	 * </pre>
	 * 
	 * .
	 * 
	 * @param content
	 *            String 내용
	 * @param maxWidth
	 *            the max width
	 * @param prefix
	 *            지정된 길이에 String 내용 앞에 채울 문자
	 * 
	 * @return 지정된 문자열 길이에 prefix를 채운 String
	 */
	public static String lpad(String content, int maxWidth, String prefix) {
		if ( content == null )
			return "";
		int lengthOfContent = content.length();
		int space = maxWidth - lengthOfContent;
		if ( maxWidth <= lengthOfContent || maxWidth < (lengthOfContent + prefix.length()) )
			return content;
		char[] charPrefix = prefix.toCharArray();
		StringBuffer strBuf = new StringBuffer();
		int index = 0;
		while (space > strBuf.length()) {
			if ( index >= prefix.length() )
				index = 0;
			strBuf.append(charPrefix[index]);
			index++;
		}
		strBuf.append(content);
		return strBuf.toString();
	}
	
	public static String removeKor(String str) {
		String rtn = str.replaceAll("[^0-9?!\\.!\\_^a-zA-Z]", "");
		return rtn;
	}
	
	/**
	 * <pre>
	 * 입력 문자열에 원하는 길이만큼 지정된 문자를 뒤에 채워준다.
	 * </pre>
	 * 
	 * @param content
	 *            String 내용
	 * @param maxWidth
	 *            the max width
	 * @param suffix
	 *            content 뒤에 지정된 길이만큼 채울 문자
	 * 
	 * @return 지정된 길이만큼 content에 suffix를 채운 문자열
	 */
	public static String rpad(String content, int maxWidth, String suffix) {
		if ( content == null )
			return "";
		int lengthOfContent = content.length();
		if ( maxWidth <= lengthOfContent || maxWidth < (lengthOfContent + suffix.length()) )
			return content;
		char[] charSuffix = suffix.toCharArray();
		StringBuffer strBuf = new StringBuffer(content);
		int index = 0;
		while (maxWidth > strBuf.length()) {
			if ( index >= suffix.length() )
				index = 0;
			strBuf.append(charSuffix[index]);
			index++;
		}
		return strBuf.toString();
	}
	
	/**
	 * <pre>
	 * String을 원하는 길이(byte 단위)로 줄여 마지막 접미사를 붙여 반환한다.
	 * 
	 * 접미사의 길이도 길이에 포함되며, 원하는 크기가 접미사의 길이보다 작으면 IllegalArgumentException을 던진다.
	 * 
	 * 주어진 인코딩 기준으로 byte[]를 얻은 후 이를  byte 단위로 나누며, 나누는 과정에 깨지는 문자는 버린다.
	 * </pre>
	 * 
	 * @param content
	 *            String 내용
	 * @param maxWidth
	 *            원하는 글자 길이 (byte 수 기준)\
	 * @param suffix
	 *            잘린 글자 뒤에 붙일 문자
	 * 
	 * @return length보다 길경우 suffix를 붙인 String
	 * 
	 * @throws UnsupportedEncodingException
	 *             the unsupported encoding exception
	 * 
	 * @see java.lang.IllegalArgumentException
	 */
	public static String abbreviate(String content, int maxWidth, String suffix) throws UnsupportedEncodingException {
		if ( content == null )
			return "";
		if ( maxWidth < suffix.length() )
			throw new IllegalArgumentException();
		int ptr = maxWidth - suffix.length();
		String str = null;
		try {
			byte[] bytes = content.getBytes("UTF-8");
			str = new String(bytes, 0, (bytes.length < ptr) ? bytes.length : ptr, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
		// 인코딩 차이로 깨져 원문과 달라진 글자를 잘라낸다.
		ptr = ((ptr = str.length() - 4) < 0) ? 0 : ptr; // 끝에서 4글자 전부터 비교 시작
		while (ptr < str.length() && str.charAt(ptr) == content.charAt(ptr))
			ptr++;
		return str.substring(0, ptr) + suffix;
	}
	
	/**
	 * <pre>
	 * 빈문자열 검사.
	 * </pre>
	 *
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object input) {
		return (input == null);
	}
	
	/**
	 * <pre>
	 * 빈문자열 검사.
	 * </pre>
	 *
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String input) {
		return (input == null || input.trim().equals(""));
	}
	
	public static String isEmpty(String input, String defaultVal) {
		if ( input == null || input.trim().equals("") ) {
			input = defaultVal;
		}
		return input;
	}
	
	public static String isEmptyDefault(String input, String defaultVal) {
		if ( input == null || input.trim().equals("") ) {
			input = defaultVal;
		}
		return input;
	}
	
	/**
	 * <pre>
	 * 빈 문자열 검사.
	 * </pre>
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isEmpty(String... input) {
		
		for (String str : input) {
			if ( isEmpty(str) ) {
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * <pre>
	 * 입력된 문자열을 화폐 단위로 표시한다.
	 * </pre>
	 *
	 * @param currency
	 * @return
	 * @throws Exception
	 */
	public static String toCurrency(String currency) throws Exception {
		try {
			
			if ( isBlank(currency) || !isNumeric(currency) || (currency.length() < 1) ) {
				return currency;
			}
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			return format.format(new Long(currency));
		} catch (IllegalArgumentException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 입력된 숫자를 화폐 단위표시.
	 * </pre>
	 *
	 * @param currency
	 * @return
	 * @throws Exception
	 */
	public static String toCurrency(long currency) throws Exception {
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			return format.format(Long.valueOf(currency));
		} catch (IllegalArgumentException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 화폐 단위 포맷을 숫자로 파싱한다.
	 * </pre>
	 *
	 * @param myString
	 * @return
	 * @throws Exception
	 */
	public static String parseCurrency(String myString) throws Exception {
		try {
			NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
			return format.parse(myString).toString();
		} catch (ParseException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 *  Array 또는 List 등의 객체를 Delimiter로 구분된 문자로 반환한다.
	 * </pre>
	 *
	 * @param obj
	 * @param delimiter
	 * @return
	 */
	public static String listToString(Object obj, String delimiter) {
		if ( obj == null ) {
			return "";
		}
		// -----------------------------------------------------
		// obj의 클래스에 따라 객체를 문자열로 만들어 리턴한다.
		// -----------------------------------------------------
		if ( obj.getClass().isArray() ) {
			StringBuffer buffer = new StringBuffer(512);
			int length = Array.getLength(obj);
			for (int i = 0; i < length; i++) {
				if ( i != 0 ) {
					buffer.append(delimiter);
				}
				buffer.append(Array.get(obj, i));
			}
			return buffer.toString();
		} else if ( obj instanceof Collection ) {
			return listToString(((Collection<?>) obj).iterator(), delimiter);
		} else if ( obj instanceof Enumeration ) {
			StringBuffer buffer = new StringBuffer(512);
			boolean started = false;
			Enumeration<?> it = (Enumeration<?>) obj;
			while (it.hasMoreElements()) {
				if ( started ) {
					buffer.append(delimiter);
				} else {
					started = true;
				}
				buffer.append(it.nextElement());
			}
			return buffer.toString();
		} else if ( obj instanceof Iterator ) {
			StringBuffer buffer = new StringBuffer(512);
			boolean started = false;
			Iterator<?> it = (Iterator<?>) obj;
			while (it.hasNext()) {
				if ( started ) {
					buffer.append(delimiter);
				} else {
					started = true;
				}
				buffer.append(it.next());
			}
			return buffer.toString();
		} else if ( obj instanceof Map ) {
			// -----------------------------------------------------
			// Map 의 경우 여러 객체들이 들어올 수 있으므로 다시 자신을 호출하여 처리한다.
			// -----------------------------------------------------
			return listToString(((Map<?, ?>) obj).values(), delimiter);
		} else {
			return obj.toString();
		}
	}
	
	/**
	 * <pre>
	 * 널값일 경우 대체 객체를 반환한다.
	 * </pre>
	 *
	 * @param source
	 * @param alernative
	 * @return
	 */
	public static Object nvl(Object source, Object alernative) {
		if ( source == null ) {
			return alernative;
		}
		return source;
	}
	
	/**
	 * <pre>
	 * 소수점이 있는 문자열에 , 처리.
	 * </pre>
	 *
	 * @param targetVal
	 * @param type
	 * @return
	 */
	public static String formatNumber(String targetVal, String type) {
		int intVal = 0;
		double dblVal = 0;
		String rtnVal = "0";
		if ( targetVal == null || targetVal.trim().length() == 0 ) {
			return "";
		}
		if ( targetVal != null ) {
			// -----------------------------------------------------
			// type 에 따라 숫자의 소수점 처리를 한다.
			// -----------------------------------------------------
			if ( type.equals("INT") ) { // 순수정수형
				intVal = new Integer(targetVal).intValue();
				DecimalFormat dfInt = new DecimalFormat("#,##0");
				rtnVal = dfInt.format(intVal);
			}
			if ( type.equals("FINT") ) { // 더블형에서 정수형
				intVal = Math.round(Float.parseFloat(targetVal));
				DecimalFormat dfInt = new DecimalFormat("#,##0");
				rtnVal = dfInt.format(intVal);
			} else if ( type.equals("DBL") ) { // 순수 더블형
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("#,##0.00");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("IDBL") ) { // 정수가 OVERFLOW(LONG TYPE 정수)
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("#,##0");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("DDBL") ) { // 더블형이 소수점 4자리인경우
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("#,##0.0000");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("DDBL1") ) { // 더블형이 소수점 1자리인경우
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("#,##0.0");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("DDBL3") ) { // 더블형이 소수점 3자리인경우
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("##,###,###,##0.000");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("DDBL6") ) { // 더블형이 소수점 6자리인경우
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("##,###,###,##0.000000");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("DDBL7") ) { // 더블형이 소수점 7자리인경우
				dblVal = new Double(targetVal).doubleValue();
				DecimalFormat dfDbl = new DecimalFormat("##,###,###,##0.0000000");
				rtnVal = dfDbl.format(dblVal);
			} else if ( type.equals("INT4") ) { // 정수지만 앞에 영이 붙는것 예) 0025
				int diff = 4 - targetVal.trim().length();
				for (int i = 0; i < diff; i++) {
					targetVal = "0" + targetVal;
				}
			}
		}
		return rtnVal;
	}
	
	/**
	 * <pre>
	 * Absolute Path에서 FileName 만 잘라서 반환한다. <br>
	 * Windows 와 Unix 계열에서의 디렉토리 구분자가 다르므로, 동일한 데이터에 대해 다른 결과를 반환하므로, 개발/운용시 주의
	 * 하기바람. <br>
	 * 구분자는 System.getProperty("file.separator") 연산의 결과를 사용한다.
	 * </pre>
	 *
	 * @param fullFileName
	 * @return
	 */
	public static String getFileName(String fullFileName) {
		try {
			return fullFileName.substring(fullFileName.lastIndexOf(System.getProperty("file.separator")) + 1);
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <pre>
	 * 전달 받은 스트링 객체를 주어진 문자 세트로 디코딩 한다.
	 * </pre>
	 * 
	 * @param value
	 *            디코딩될 값이 있는 스트링 객체.
	 * @param charset
	 *            디코딩 될 문자 세트.
	 * 
	 * @return 디코딩된 스트링 객체.
	 */
	public static String decodeCharset(String value, String charset) {
		try {
			Charset set = Charset.forName(charset);
			return set.decode(ByteBuffer.wrap(value.getBytes())).toString();
		} catch (UnsupportedCharsetException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <pre>
	 * 전달 받은 스트링 객체를 주어진 문자 세트로 인코딩 한다.
	 * </pre>
	 * 
	 * @param value
	 *            인코딩될 값이 있는 스트링 객체.
	 * @param charset
	 *            인코딩 될 문자 세트.
	 * 
	 * @return 인코딩된 스트링 객체.
	 */
	public static String encodeCharset(String value, String charset) {
		try {
			Charset set = Charset.forName(charset);
			ByteBuffer bb = set.encode(value);
			return new String(bb.array(), charset);
		} catch (UnsupportedCharsetException ex) {
			ex.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * <pre>
	 * 한글 인코딩(KSC5601)을 "8859_1" 인코딩으로 전환한다.
	 * </pre>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String kscToAsc(String str) throws Exception {
		try {
			return str != null ? new String(str.getBytes("KSC5601"), "8859_1") : str;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 8859_1을 KSC5601로 인코딩한다.
	 * </pre>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String ascToKsc(String str) throws Exception {
		try {
			return str != null ? new String(str.getBytes("8859_1"), "KSC5601") : str;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 한글 인코딩(KSC5601)을 "UTF-8" 인코딩으로 전환한다.
	 * </pre>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String kscToUtf8(String str) throws Exception {
		try {
			return str != null ? new String(str.getBytes("KSC5601"), "UTF-8") : str;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * "UTF-8"을 KSC5601로 인코딩한다.
	 * </pre>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 *             the exception
	 */
	public static String utf8ToKsc(String str) throws Exception {
		try {
			return str != null ? new String(str.getBytes("UTF-8"), "KSC5601") : str;
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		}
	}
	
	/**
	 * <pre>
	 * 이메일 형식 체크 잘못된 형식일 경우 false값을 리턴 한다.
	 * </pre>
	 * 
	 * @param aEmailAddress
	 *            the a email address
	 * 
	 * @return true, if checks if is email address
	 */
	public static boolean isEmailAddress(String aEmailAddress) {
		if ( aEmailAddress == null )
			return false;
		boolean result = true;
		try {
			if ( !hasNameAndDomain(aEmailAddress) ) {
				result = false;
			}
		} catch (PatternSyntaxException ex) {
			ex.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * <pre>
	 * Checks for name and domain.
	 * </pre>
	 * 
	 * @param aEmailAddress
	 *            the a email address
	 * 
	 * @return true, if successful
	 */
	private static boolean hasNameAndDomain(String aEmailAddress) {
		String[] tokens = aEmailAddress.split("@");
		return tokens.length == 2 && !isEmpty(tokens[0]) && !isEmpty(tokens[1]);
	}
	
	/**
	 * <pre>
	 * 법인번호 유효성을 체크한다.
	 * </pre>
	 * 
	 * @param corpNo
	 *            the corp no
	 * 
	 * @return true, if checks if is corp no
	 */
	public static boolean isCorpNo(String corpNo) {
		if ( corpNo == null || corpNo.length() == 0 )
			return false;
		corpNo = replace(corpNo, "-", "");
		String noArr[] = new String[13];
		int sum1 = 0;
		int sum2 = 0;
		int total_sum = 0;
		for (int i = 0; i < corpNo.length(); i++) {
			noArr[i] = corpNo.substring(i, i + 1);
		}
		for (int i = 0; i < noArr.length - 1; i++) {
			if ( i % 2 == 0 ) { // * 1
				sum1 = sum1 + (Integer.parseInt(noArr[i]) * 1);
			} else { // * 2
				sum2 = sum2 + (Integer.parseInt(noArr[i]) * 2);
			}
		}
		total_sum = sum1 + sum2;
		int mok = total_sum % 10;
		if ( mok != 0 ) {
			mok = 10 - mok;
		}
		if ( mok == Integer.parseInt(noArr[12]) ) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * <pre>
	 * 사업자 번호 유효성을 체크한다.
	 * </pre>
	 * 
	 * @param bizNo
	 *            the biz no
	 * 
	 * @return true, if checks if is biz no
	 */
	public static boolean isBizNo(String bizNo) {
		if ( bizNo == null || bizNo.length() == 0 )
			return false;
		bizNo = replace(bizNo, "-", "");
		Integer sum = 0;
		String[] getlist = new String[10];
		Integer[] chkvalue = new Integer[] { 1, 3, 7, 1, 3, 7, 1, 3, 5 };
		for (int i = 0; i < 10; i++) {
			getlist[i] = bizNo.substring(i, i + 1);
		}
		for (int i = 0; i < 9; i++) {
			sum += Integer.parseInt(getlist[i]) * chkvalue[i];
		}
		sum = sum + ((Integer.parseInt(getlist[8]) * 5) / 10);
		int sidliy = sum % 10;
		int sidchk = 0;
		if ( sidliy != 0 ) {
			sidchk = 10 - sidliy;
		} else {
			sidchk = 0;
		}
		if ( sidchk != Integer.parseInt(getlist[9]) ) {
			return false;
		}
		return true;
	}
	
	public static boolean isMobileNo(String bizNo) {
		return bizNo.replaceAll("-", "").matches("(01[016789])(\\d{3,4})(\\d{4})");
	}
	
	/**
	 * <pre>
	 * 주민번호가 맞는지 체크.
	 * </pre>
	 * 
	 * @param jumin1
	 *            the jumin1
	 * @param jumin2
	 *            the jumin2
	 * 
	 * @return true, if checks if is ssn
	 */
	public static boolean isSSN(String jumin1, String jumin2) {
		// -----------------------------------------------------
		// 데이터 존재 여부 판별 한다. 주민번호 생성 규칙에 맞춰 검사한후 검사 값을 리턴한다.
		// -----------------------------------------------------
		if ( isEmpty(jumin1) || isEmpty(jumin2) ) {
			return false;
		}
		if ( !isNumeric(jumin1) ) {
			return false;
		}
		if ( !isNumeric(jumin2) ) {
			return false;
		}
		jumin1 = jumin1.trim();
		jumin2 = jumin2.trim();
		if ( !(jumin1.length() == 6 && jumin2.length() == 7) ) {
			return false;
		}
		// -----------------------------------------------------
		// 주민번호 생성 규칙에 맞춰 검사한후 검사 값을 리턴한다.
		// -----------------------------------------------------
		int hap = 0;
		for (int i = 0; i < 6; i++) {
			hap += (jumin1.charAt(i) - '0') * (i + 2);
		}
		int n0 = jumin2.charAt(0) - '0';
		int n1 = jumin2.charAt(1) - '0';
		int n2 = jumin2.charAt(2) - '0';
		int n3 = jumin2.charAt(3) - '0';
		int n4 = jumin2.charAt(4) - '0';
		int n5 = jumin2.charAt(5) - '0';
		int n6 = jumin2.charAt(6) - '0';
		hap += n0 * 8 + n1 * 9 + n2 * 2 + n3 * 3 + n4 * 4 + n5 * 5;
		hap %= 11;
		hap = 11 - hap;
		hap %= 10;
		if ( hap != n6 ) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 주민번호 체크.
	 * </pre>
	 * 
	 * @param jumin
	 *            the jumin
	 * 
	 * @return true, if is sSN
	 */
	public static boolean isSSN(String jumin) {
		boolean returnFlag = false;
		String str = trimToEmpty(jumin);
		if ( isEmpty(str) ) {
			return false;
		}
		if ( str.length() != 13 ) {
			return false;
		}
		returnFlag = isSSN(str.substring(0, 6), str.substring(6));
		return returnFlag;
	}
	
	/**
	 * <pre>
	 * To number.
	 * </pre>
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the string
	 */
	public static String toNumber(String str) {
		StringBuffer toNum = new StringBuffer();
		Pattern pattern = Pattern.compile("[0-9]");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			toNum.append(matcher.group());
		}
		return toNum.toString();
	}
	
	/**
	 * <pre>
	 * 숫자의 소수점과 자리 구분 타입을 파라미터로 받아 포맷에 맞게 변경하여 리턴한다.
	 * </pre>
	 *
	 * @param data
	 * @param fmt
	 * @return
	 * @throws Exception
	 */
	public static String toStringDecimal(String data, String fmt) throws Exception {
		double dt = 0L;
		try {
			dt = (new Double(data)).doubleValue();
		} catch (NumberFormatException e) {
			return data;
		}
		java.text.DecimalFormat df = new java.text.DecimalFormat(fmt);
		return df.format(dt);
	}
	
	/**
	 * <pre>
	 * Make message.
	 * </pre>
	 *
	 * @param form
	 *            the form ex) "The disk \"{1}\" contains {0} file(s)."
	 * @param args
	 *            the args ex) Object[] testArgs = {new Long(3), "disk"};
	 * @return
	 */
	public static String makeMessage(String form, Object[] args) {
		String message = null;
		MessageFormat format = new MessageFormat(form);
		try {
			message = format.format(args);
		} catch (IllegalArgumentException e) {
			message = form;
		}
		return message;
	}
	
	/**
	 * <pre>
	 * Format number.
	 * </pre>
	 * 
	 * @param number
	 *            the number
	 * 
	 * @return the string
	 */
	public static String formatNumber(String number) {
		if ( number == null )
			return null;
		if ( number.length() <= 3 )
			return number;
		char[] chars = number.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < chars.length; i++) {
			sb.append(chars[i]);
			if ( (chars.length - (i + 1)) % 3 == 0 && (i + 1) != chars.length ) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * <pre>
	 * 주어진 문자열로 code와 value를 얻어온다.
	 * </pre>
	 * 
	 * @param value
	 *            code와 value로 구성된 문자열 ex)${1}이름, ${2}주소
	 * 
	 * @return the list< string[]>
	 */
	public static List<String[]> parseCodeValue(String value) {
		List<String[]> list = new LinkedList<String[]>();
		String[] splitData = value.split(",");
		for (String data : splitData) {
			list.add(parseUnitCodeValue(data));
		}
		return list;
	}
	
	/**
	 * <pre>
	 * Parses the unit code value.
	 * </pre>
	 * 
	 * @param data
	 *            the data
	 * 
	 * @return the string[]
	 */
	private static String[] parseUnitCodeValue(String data) {
		String[] codeValue = new String[2];
		int start = data.indexOf("${");
		if ( start > -1 ) {
			int end = data.indexOf("}");
			codeValue[0] = data.substring(start + 2, end);
			codeValue[1] = data.substring(end + 1);
		}
		return codeValue;
	}
	
	/**
	 * <pre>
	 * In_array.
	 * </pre>
	 * 
	 * @param haystack
	 *            the haystack
	 * @param needle
	 *            the needle
	 * 
	 * @return true, if successful
	 */
	public boolean in_array(List<?> haystack, String needle) {
		for (int i = 0; i < haystack.size(); i++) {
			if ( haystack.get(i).toString().equals(needle) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * <pre>
	 * 문자열에서 Property형태의 값을 추출한다. Property 형태란 'key=value'형식으로 되어있는 것을 의미한다. 단,
	 * 여기에서는 하나의 문자열을 사용할 수 있게 하기 위해 각 Property의 구분자로 '::'를 사용한다.
	 * Example
	 * </pre>
	 * 
	 * @param source
	 *            프로퍼티를 검색할 원본 문자열
	 * @param key
	 *            검색할 키 문자열
	 * @param defaultValue
	 *            해당 Key에 해당하는 값이 없을때 반환할 기본값
	 * 
	 * @return 검색된 Property의 Value
	 * 
	 *         <code>
	 * String source = "key1=value1::key2=value2::key3=value3";<br>
	 * String key = "key2";<br>
	 * String value = TextUtil.getParam(source,key,"Default Value");<br>
	 * </code> 위의 예제의 결과 값은 "value2" 이다.
	 */
	public static String getParam(String source, String key, String defaultValue) {
		if ( source == null || key == null ) {
			return defaultValue;
		}
		int i = source.indexOf(key + "=");
		if ( i < 0 ) {
			return defaultValue;
		}
		int j = i + key.length() + 1;
		int k = source.indexOf("::", j);
		if ( k < 0 ) {
			k = source.length();
		}
		try {
			return source.substring(j, k);
		} catch (IndexOutOfBoundsException ex) {
			ex.printStackTrace();
			return defaultValue;
		}
	}
	
	/**
	 * <pre>
	 * 문자열을 좌측 정렬한다. 이때 문자열뒤에 줄임표는 넣지 않는다.<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignLeft(source, 10);<br>
	 * </code> <code>result</code>는 <code>"ABCDEFG   "</code> 을 가지게 된다.
	 */
	public static String alignLeft(String source, int length) {
		
		return alignLeft(source, length, false);
	}
	
	/**
	 * <pre>
	 * 문자열을 좌측부터 원하는만큼 자른다.(원한다면 끝에 ...을 붙인다.)<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * @param isEllipsis
	 *            마지막에 줄임표("...")의 여부
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignLeft(source, 5, true);<br>
	 * </code> <code>result</code>는 <code>"AB..."</code> 을 가지게 된다.
	 */
	public static String alignLeft(String source, int length, boolean isEllipsis) {
		
		if ( source.length() <= length ) {
			
			StringBuffer temp = new StringBuffer(source);
			for (int i = 0; i < (length - source.length()); i++) {
				temp.append(WHITE_SPACE);
			}
			return temp.toString();
		} else {
			if ( isEllipsis ) {
				
				StringBuffer temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		}
	}
	
	/**
	 * <pre>
	 * 문자열을 우측 정렬한다. 이때 문자열뒤에 줄임표는 넣지 않는다.<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignRight(source, 10);<br>
	 * </code> <code>result</code>는 <code>"   ABCDEFG"</code> 을 가지게 된다.
	 */
	public static String alignRight(String source, int length) {
		
		return alignRight(source, length, false);
	}
	
	/**
	 * <pre>
	 * 문자열을 우측 정렬한다.(원한다면 끝에 ...을 붙인다.)<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * @param isEllipsis
	 *            마지막에 줄임표("...")의 여부
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignRight(source, 5, true);<br>
	 * </code> <code>result</code>는 <code>"AB..."</code> 을 가지게 된다.
	 */
	public static String alignRight(String source, int length, boolean isEllipsis) {
		
		if ( source.length() <= length ) {
			
			StringBuffer temp = new StringBuffer(length);
			for (int i = 0; i < (length - source.length()); i++) {
				temp.append(WHITE_SPACE);
			}
			temp.append(source);
			return temp.toString();
		} else {
			if ( isEllipsis ) {
				
				StringBuffer temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		}
	}
	
	/**
	 * <pre>
	 * 문자열을 중앙 정렬한다. 이때 문자열뒤에 줄임표는 넣지 않는다. 만약 공백이 홀수로 남는다면 오른쪽에 들어 간다.<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignCenter(source, 10);<br>
	 * </code> <code>result</code>는 <code>" ABCDEFG "</code> 을 가지게 된다.
	 */
	public static String alignCenter(String source, int length) {
		
		return alignCenter(source, length, false);
	}
	
	/**
	 * <pre>
	 * 문자열을 중앙 정렬한다. 만약 공백이 홀수로 남는다면 오른쪽에 들어 간다.<br>
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * @param isEllipsis
	 *            마지막에 줄임표("...")의 여부
	 * 
	 * @return 정렬이 이루어진 문자열
	 * 
	 *         <code>
	 * String source = "ABCDEFG";<br>
	 * String result = TextUtil.alignCenter(source, 5,true);<br>
	 * </code> <code>result</code>는 <code>"AB..."</code> 을 가지게 된다.
	 */
	public static String alignCenter(String source, int length, boolean isEllipsis) {
		if ( source.length() <= length ) {
			// -----------------------------------------------------
			// source 의 길이를 확인하여 length 보다 짧을 경우
			// 앞뒤로 length 길이에 맞춰 ' '을 붙여 중앙으로 만들어 리턴한다.
			// -----------------------------------------------------
			StringBuffer temp = new StringBuffer(length);
			int leftMargin = (int) (length - source.length()) / 2;
			int rightMargin;
			if ( (leftMargin * 2) == (length - source.length()) ) {
				rightMargin = leftMargin;
			} else {
				rightMargin = leftMargin + 1;
			}
			for (int i = 0; i < leftMargin; i++) {
				temp.append(WHITE_SPACE);
			}
			temp.append(source);
			for (int i = 0; i < rightMargin; i++) {
				temp.append(WHITE_SPACE);
			}
			return temp.toString();
		} else {
			// -----------------------------------------------------
			// source 의 길이를 확인하여 length 보다 길 경우 '...' 을 붙여 리턴한다.
			// -----------------------------------------------------
			if ( isEllipsis ) {
				StringBuffer temp = new StringBuffer(length);
				temp.append(source.substring(0, length - 3));
				temp.append("...");
				return temp.toString();
			} else {
				return source.substring(0, length);
			}
		}
		
	}
	
	/**
	 * <pre>
	 * 문자열의 제일 처음글자를 대문자화 한다.<br>
	 * </pre>
	 * 
	 * @param s
	 *            원본 문자였
	 * 
	 * @return 대문자화 된 문자열
	 * 
	 *         <code>
	 * String source = "abcdefg";<br>
	 * String result = TextUtil.capitalize(source);<br>
	 * </code> <code>result</code>는 <code>"Abcdefg"</code> 을 가지게 된다.
	 */
	public static String capitalize(String s) {
		return !s.equals("") && s != null ? s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() : s;
	}
	
	/**
	 * <pre>
	 * 원본 문자열에서 target 문자열을 찾아 해당 boolean으로 치환한다.<br>
	 * </pre>
	 * 
	 * @param s
	 *            원본 문자열
	 * @param s1
	 *            치환될 문자열
	 * @param flag
	 *            치환되어 들어갈 boolean
	 * 
	 * @return 치환된 문자열
	 * 
	 *         <code>
	 * String source = "Onwer is [B] statues.";<br>
	 * String result = TextUtil.replace(source, "[B]",true);<br>
	 * </code> <code>result</code>는 <code>"Onwer is true statues."</code> 을 가지게
	 *         된다.
	 */
	public static String replace(String s, String s1, boolean flag) {
		return replace(s, s1, String.valueOf(flag));
	}
	
	/**
	 * <pre>
	 * 원본 문자열에서 target 문자열을 찾아 해당 숫자로 치환한다.<br>
	 * </pre>
	 * 
	 * @param s
	 *            원본 문자열
	 * @param s1
	 *            치환될 문자열
	 * @param i
	 *            치환되어 들어갈 숫자
	 * 
	 * @return 치환된 문자열
	 * 
	 *         <code>
	 * String source = "Onwer is [I] statues.";<br>
	 * String result = TextUtil.replace(source, "[I]",15);<br>
	 * </code> <code>result</code>는 <code>"Onwer is 15 statues."</code> 을 가지게
	 *         된다.
	 */
	public static String replace(String s, String s1, int i) {
		return replace(s, s1, String.valueOf(i));
	}
	
	/**
	 * <pre>
	 * 원본 문자열에서 target 문자열을 찾아 치환한다.<br>
	 * </pre>
	 * 
	 * @param s
	 *            원본 문자열
	 * @param s1
	 *            치환될 문자열
	 * @param s2
	 *            치환되어 들어갈 문자열
	 * 
	 * @return 치환된 문자열
	 * 
	 *         <code>
	 * String source = "Onwer is [I] statues.";<br>
	 * String result = TextUtil.replace(source, "[I]","fool");<br>
	 * </code> <code>result</code>는 <code>"Onwer is fool statues."</code> 을 가지게
	 *         된다.
	 */
	public static String replace(String s, String s1, String s2) {
		StringBuffer stringbuffer = new StringBuffer(s.length());
		int j = 0;
		for (int i = s.indexOf(s1, j); i != -1; i = s.indexOf(s1, j)) {
			stringbuffer.append(s.substring(j, i));
			stringbuffer.append(s2);
			j = i + s1.length();
		}
		
		if ( j < s.length() ) {
			stringbuffer.append(s.substring(j));
		}
		return stringbuffer.toString();
	}
	
	/**
	 * <pre>
	 * 배열을 받아 연결될 문자열로 연결한다. 이때 각 엘레멘트 사이에 구분문자열을 추가한다.<br>
	 * </pre>
	 * 
	 * @param aobj
	 *            문자열로 만들 배열
	 * @param s
	 *            각 엘레멘트의 구분 문자열
	 * 
	 * @return 연결된 문자열
	 * 
	 *         <code>
	 * String[] source = new String[] {"AAA","BBB","CCC"};<br>
	 * String result = TextUtil.join(source,"+");<br>
	 * </code> <code>result</code>는 <code>"AAABBBCCC"</code>를 가지게 된다.
	 */
	public static String join(Object aobj[], String s) {
		StringBuffer stringbuffer = new StringBuffer();
		int i = aobj.length;
		if ( i > 0 ) {
			stringbuffer.append(aobj[0].toString());
		}
		for (int j = 1; j < i; j++) {
			stringbuffer.append(s);
			stringbuffer.append(aobj[j].toString());
		}
		
		return stringbuffer.toString();
	}
	
	/**
	 * <pre>
	 * 문자열을 지정된 Token Seperator로 Tokenize한다.(문자열 배열을 리턴)<br>
	 * </pre>
	 * 
	 * @param s
	 *            원본 문지열
	 * @param s1
	 *            Token Seperators
	 * 
	 * @return 토큰들의 배열
	 * 
	 *         <code>
	 * String source = "Text token\tis A Good\nAnd bad.";<br>
	 * String[] result = TextUtil.split(source, " \t\n");<br>
	 * </code> <code>result</code>는
	 *         <code>"Text","token","is","A","Good","And","bad."</code> 를 가지게
	 *         된다.
	 */
	public static String[] split(String s, String s1) {
		StringTokenizer stringtokenizer = new StringTokenizer(s, s1);
		int i = stringtokenizer.countTokens();
		String as[] = new String[i];
		for (int j = 0; j < i; j++) {
			as[j] = stringtokenizer.nextToken();
		}
		
		return as;
	}
	
	/**
	 * <pre>
	 * 원본 문자열을 일반적인 공백문자(' ','\n','\t','\r')로 토큰화 한다.
	 * </pre>
	 * 
	 * @param s
	 *            원본문자열
	 * 
	 * @return 토큰의 배열
	 * 
	 *         <code>
	 * String source = "Text token\tis A Good\nAnd\rbad.";<br>
	 * String[] result = TextUtil.splitwords(source);<br>
	 * </code> <code>result</code>는
	 *         <code>"Text","token","is","A","Good","And","bad."</code> 를 가지게
	 *         된다.
	 */
	public static String[] splitwords(String s) {
		return splitwords(s, m_whiteSpace);
	}
	
	/**
	 * <pre>
	 * 원본 문자열을 일반적인 공백문자(' ','\n','\t','\r')로 토큰화 한다.<br> 겹따옴표('"') 안의 내용은 하나의 토큰으로 판단하여 문자열을 토큰화 한다.
	 * </pre>
	 * 
	 * @param s
	 *            원본 문자열
	 * @param s1
	 *            Token Seperators
	 * 
	 * @return Description of the Returned Value
	 * 
	 *         <code>
	 * String source = "Text token\tis A \"Good day\"\nAnd\r\"bad day.\"";<br>
	 * String[] result = TextUtil.splitwords(source);<br>
	 * </code> <code>result</code>는
	 *         <code>"Text","token","is","A","Good day","And","bad day."</code>
	 *         를 가지게 된다.
	 */
	@SuppressWarnings("unchecked")
	public static String[] splitwords(String s, String s1) {
		boolean flag = false;
		StringBuffer stringbuffer = null;
		Vector<StringBuffer> vector = new Vector<StringBuffer>();
		// -----------------------------------------------------
		// s1에 맞춰 문자열을 분리한다.
		// -----------------------------------------------------
		for (int i = 0; i < s.length();) {
			char c = s.charAt(i);
			if ( !flag && s1.indexOf(c) != -1 ) {
				if ( stringbuffer != null ) {
					vector.addElement(stringbuffer);
					stringbuffer = null;
				}
				for (; i < s.length() && s1.indexOf(s.charAt(i)) != -1; i++) {
					;
				}
			} else {
				if ( c == m_citChar ) {
					if ( flag ) {
						flag = false;
					} else {
						flag = true;
					}
				} else {
					if ( stringbuffer == null ) {
						stringbuffer = new StringBuffer();
					}
					stringbuffer.append(c);
				}
				i++;
			}
		}
		
		// -----------------------------------------------------
		// 분리된 문자열을 vector 에 담아 for 문을 돌려 배열 형태로 바꿔준다.
		// -----------------------------------------------------
		if ( stringbuffer != null ) {
			vector.addElement(stringbuffer);
		}
		String as[] = new String[vector.size()];
		for (int j = 0; j < vector.size(); j++) {
			as[j] = new String((StringBuffer) vector.elementAt(j));
		}
		
		return as;
	}
	
	/**
	 * <pre>
	 * 배열을 Vector로 만든다.
	 * </pre>
	 * 
	 * @param array
	 *            원본 배열
	 * 
	 * @return 배열과 같은 내용을 가지는 Vector
	 */
	
	public static Vector<Object> toVector(Object[] array) {
		if ( array == null ) {
			return null;
		}
		
		Vector<Object> vec = new Vector<Object>(array.length);
		
		for (int i = 0; i < array.length; i++) {
			vec.add(i, array[i]);
		}
		return vec;
	}
	
	/**
	 * <pre>
	 * 문자열의 배열을 소팅한다.
	 * </pre>
	 * 
	 * @param source
	 *            the source
	 * 
	 * @return 배열과 같은 내용을 가지는 Vector
	 */
	public static String[] sortStringArray(String[] source) {
		
		java.util.Arrays.sort(source);
		
		return source;
	}
	
	/**
	 * <pre>
	 * 문자열의 Enemration을 소팅된 배열로 반환한다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 열거형
	 * 
	 * @return 열거형의 값을 가진 배열
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] sortStringArray(Enumeration source) {
		Vector buf = new Vector();
		while (source.hasMoreElements()) {
			buf.add(source.nextElement());
		}
		String[] buf2 = new String[buf.size()];
		
		for (int i = 0; i < buf.size(); i++) {
			
			Object obj = buf.get(i);
			if ( obj instanceof String ) {
				buf2[i] = (String) obj;
			} else {
				throw new IllegalArgumentException("Not String Array");
			}
		}
		java.util.Arrays.sort(buf2);
		return buf2;
	}
	
	/**
	 * <pre>
	 * 문자열이 입력한 길이보다 남는 공백에 좌측정렬후 원하는 문자를 삽입힌다.
	 * </pre>
	 * 
	 * @param source
	 *            원본 문자열
	 * @param length
	 *            정렬이 이루어질 길이
	 * @param ch
	 *            공백에 삽입할 원하는 문자
	 * 
	 * @return 결과 문자열
	 * 
	 *         <code>
	 * String source = "ABC"<br>
	 * String result = TextUtil.insertLeftChar(source, 5, '*');<br>
	 * </code> <code>result</code>는 <code>"ABC**"</code> 을 가지게 된다.
	 */
	public static String insertLeftChar(String source, int length, char ch) {
		
		StringBuffer temp = new StringBuffer(length);
		
		if ( source.length() <= length ) {
			
			for (int i = 0; i < (length - source.length()); i++) {
				temp.append(ch);
			}
			temp.append(source);
		}
		return temp.toString();
	}
	
	/**
	 * <pre>
	 * 법인 등록 번호로 변환
	 * </pre>
	 *
	 * @param corpno
	 * @return
	 */
	public static String toCorpno(String corpno) {
		String result = "";
		if ( !isNumeric(corpno) || (corpno.length() > 13) || (corpno.length() < 13) ) {
			return corpno;
		}
		result = corpno.substring(0, 6) + "-" + corpno.substring(6, 13);
		return result;
	}
	
	/**
	 * <pre>
	 * 사업자 등록 번호로 변환
	 * </pre>
	 *
	 * @param corpno
	 * @return
	 */
	public static String toBizpno(String corpno) {
		String result = "";
		if ( !isNumeric(corpno) || (corpno.length() > 10) || (corpno.length() < 10) ) {
			return corpno;
		}
		result = corpno.substring(0, 3) + "-" + corpno.substring(3, 5) + "-" + corpno.substring(5, 10);
		return result;
	}
	
	/**
	 * <pre>
	 * 주민등록 번호로 변환.
	 * </pre>
	 *
	 * @param citizen
	 * @return
	 */
	public static String toCitizen(String citizen) {
		String result = "";
		if ( !isNumeric(citizen) || (citizen.length() > 13) || (citizen.length() < 13) ) {
			return citizen;
		}
		result = citizen.substring(0, 7) + "-" + citizen.substring(7, 13);
		return result;
	}
	
	/**
	 * <pre>
	 * 우편 번호 형식에 맞춰 변화한다.
	 * </pre>
	 * 
	 * @param zipCode
	 *            the zip code
	 * 
	 * @return the string
	 */
	public static String toZipcode(String zipCode) {
		String result = "";
		if ( !isNumeric(zipCode) || (zipCode.length() > 6) || (zipCode.length() < 6) ) {
			return zipCode;
		}
		result = zipCode.substring(0, 3) + "-" + zipCode.substring(3, 6);
		return result;
	}
	
	/**
	 * <pre>
	 * 전화번호 형식에 맞춰 변경한다.
	 * </pre>
	 * 
	 * @param telnumber
	 *            the telnumber
	 * 
	 * @return the string
	 */
	public static String toTel(String telnumber) {
		String result = "";
		if ( !isNumeric(telnumber) || (telnumber.length() > 11) ) {
			return telnumber;
		}
		// -----------------------------------------------------
		// 전화 번호의 길이에 따라 분류 방법을 맞춰 리턴합니다.
		// -----------------------------------------------------
		
		switch (telnumber.length()) {
		case 11:
			if ( telnumber.substring(0, 3).equals("050") ) {
				result = telnumber.substring(0, 4) + "-" + telnumber.substring(4, 7) + "-" + telnumber.substring(7, 11);
			} else if ( telnumber.substring(0, 4).equals("0130") ) {
				result = telnumber.substring(0, 4) + "-" + telnumber.substring(4, 7) + "-" + telnumber.substring(7, 11);
			} else {
				result = telnumber.substring(0, 3) + "-" + telnumber.substring(3, 7) + "-" + telnumber.substring(7, 11);
			}
			break;
		case 10:
			if ( telnumber.substring(0, 2).equals("02") ) {
				result = telnumber.substring(0, 2) + "-" + telnumber.substring(2, 6) + "-" + telnumber.substring(6, 10);
			} else {
				result = telnumber.substring(0, 3) + "-" + telnumber.substring(3, 6) + "-" + telnumber.substring(6, 10);
			}
			break;
		case 9:
			result = telnumber.substring(0, 2) + "-" + telnumber.substring(2, 5) + "-" + telnumber.substring(5, 9);
			break;
		case 8:
			result = telnumber.substring(0, 4) + "-" + telnumber.substring(4, 8);
			break;
		default:
			result = telnumber;
		}
		return result;
	}
	
	/**
	 * int를 String으로 형변환한다.
	 *
	 * @param i
	 *            숫자
	 * @return 숫자형 문자열
	 */
	public static final String toString(int i) {
		return (new Integer(i)).toString();
	}
	
	/**
	 * 특수 문자열 받아서 HTML 코드로 변환하는 메소드 <xmp> < : &lt; > : &gt; & : &amp; ' : &#39;
	 * " : &quot; white space : &nbsp; \r\n : <BR>
	 * </xmp>
	 *
	 * @param text
	 *            특수 문자열
	 * @return String 치환된 문자열
	 */
	public static final String formatHTMLCode(String text) {
		if ( text == null || text.equals("") )
			return "";
		
		StringBuffer sb = new StringBuffer(text);
		char ch;
		
		for (int i = 0; i < sb.length(); i++) {
			ch = sb.charAt(i);
			if ( ch == '<' ) {
				sb.replace(i, i + 1, "&lt;");
				i += 3;
			} else if ( ch == '>' ) {
				sb.replace(i, i + 1, "&gt;");
				i += 3;
			} else if ( ch == '&' ) {
				sb.replace(i, i + 1, "&amp;");
				i += 4;
			} else if ( ch == '\'' ) {
				sb.replace(i, i + 1, "&#39;");
				i += 4;
			} else if ( ch == '"' ) {
				sb.replace(i, i + 1, "&quot;");
				i += 5;
			} else if ( ch == ' ' ) {
				sb.replace(i, i + 1, "&nbsp;");
				i += 5;
			} else if ( ch == '\r' && sb.charAt(i + 1) == '\n' ) {
				sb.replace(i, i + 2, "<BR>");
				i += 3;
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * <pre>
	 * 입력받은 underscore(_) 로 연결된 단어를 camelcase로 변환한다.
	 * 첫글자는 소문자로 시작한다.
	 * ex) LORD_OF_THE_RINGS ==> lordOfTheRings
	 * </pre>
	 *
	 * @param text
	 * @return
	 */
	public static String toCamelCase(String text) {
		if ( isEmpty(text) )
			return "";
		
		return toCamelCase(text, true);
	}
	
	/**
	 * 
	 * <pre>
	 * 입력받은 underscore(_) 로 연결된 단어를 camelcase로 변환한다.
	 * 첫글자는 소문자로 시작한다.
	 * ex) LORD_OF_THE_RINGS ==> lordOfTheRings
	 * </pre>
	 *
	 * @param text
	 * @param startCharLowerCase
	 *            첫글자가 소문자로 시작하는지 여부. false 인 경우 대문자로 시작
	 * @return
	 */
	
	public static String toCamelCase(String text, boolean startCharLowerCase) {
		if ( text == null )
			return null;
		if ( text.length() == 1 )
			return text;
		String result = org.apache.commons.lang3.text.WordUtils.capitalizeFully(text, new char[] { '_' }).replaceAll("_", "");
		if ( startCharLowerCase ) {
			result = result.substring(0, 1).toLowerCase() + result.substring(1);
		}
		return result;
	}
	
	/**
	 * <pre>
	 * 넘겨받은 문자열을 모두 합쳐서 반환한다.
	 * </pre>
	 * 
	 * @param strings
	 * @return
	 */
	public static String merge(String... strings) {
		StringBuilder merge = new StringBuilder();
		for (String str : strings) {
			merge.append(str);
		}
		return merge.toString();
	}
	
	public static String makeAccessKey() {
		// UUID.randomUUID().toString();
		return UUID.randomUUID().toString();
	}
	
	// 한글+ 숫자 => 숫자만 치환합니다.
	public static String makeNumber(String num) {
		String numeral = "", temp = "";
		if ( num == null ) {
			numeral = null;
		} else {
			for (int i = 0; i < num.length(); i++) {
				temp = num.substring(i, i + 1);
				if ( temp.charAt(0) == 45 ) {
					numeral += temp;
				}
			}
		}
		return numeral;
	}
	
	public static boolean hasArray(String arrayStr, String checkVal) {
		ArrayList<String> arrays = new ArrayList<String>(Arrays.asList(arrayStr.split(",")));
		
		return arrays.contains(checkVal);
	}
	
	
	
	public static boolean isStringInteger(String s) {
		try {
			Integer.parseInt(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static boolean isValidCarNo(String carNo) {
		boolean rtn = true;

		if (carNo.toLowerCase().contains("x")) {
			rtn = false;
		}

		if (carNo.equalsIgnoreCase("No_Detection")) {
			rtn = false;
		}

		if(isEmpty(carNo)) {
			rtn = false;
		}
		return rtn;
	}
	
	public static String getPartnerParkingId(String iLotArea, String dtInDate , String iID) {
		return iLotArea+"_"+dtInDate.substring(0,8)+"_"+StringUtils.lpad(iID, 9, "0");
	}
}
