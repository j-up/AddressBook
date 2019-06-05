package kr.co.address.controller.addressBook;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.address.common.utils.StringUtils;
import kr.co.address.service.addressBook.AddressBookService;

@Controller
public class AddressBookController {
	@Autowired
	AddressBookService addressBookService;
	
	
	/**
	 * @description 연락처 저장
	 * @param JSONObject
	 * @return 
	 */

	@RequestMapping(value = "/addressBook/register", method = RequestMethod.POST)
	@ResponseBody
	public void userLogin(@RequestBody String param) {
	
		JSONArray jsonArray = StringUtils.stringToJsonArray(param, "addressMST");
		
		int i = 1;
		
		/*
		JSONArray jsonArray = (JSONArray) clientSendJson.get("toArrayString");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject tmp = (JSONObject) jsonArray.get(i);
			// ip를 담을 임시 json객체
			JSONObject toIpObject = new JSONObject();
			// 1:1채팅이 아니라 단체 채팅일경우를 대비해 array로 받는다
			toIpObject.put("ipArray", tmp.get("ipArray"));
			toIpArrayObject.add(toIpObject);
			// 송신할때 ip리스트들을 사용하기 위한 arrayList
			toIpArrayList.add(tmp.get("ipArray").toString());
		} */
		
		}
	}

