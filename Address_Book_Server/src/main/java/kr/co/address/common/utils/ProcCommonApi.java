package kr.co.address.common.utils;

import kr.co.address.domain.CommonApiVO;

/**
 * @description API 공통 처리 부분 util		
 */
public class ProcCommonApi {
	/**
	 * @description 공통 API 변수 값 셋팅 CommonApiVO가 Response인 경우.
	 * @param CommonApiVO
	 * @return CommonApiVO
	 */
	public static CommonApiVO setCommonApiVO(CommonApiVO commonApiVO) {

		commonApiVO.setResultCode("ADR-200");
		commonApiVO.setResultMessage("Success");
		commonApiVO.setResponseDateTime("");

		return commonApiVO;
 	}
}
