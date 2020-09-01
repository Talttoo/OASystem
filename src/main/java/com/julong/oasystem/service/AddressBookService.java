package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/5/1 0004 下午 14:52
 * @Description：通讯录
 */
public interface AddressBookService {

	List<JSONObject> listDept();
	
	JSONObject listtUser(JSONObject jSONObject);
	
	
	JSONObject synchroTxl();
	
	JSONObject addUser(JSONObject jSONObject);
}
