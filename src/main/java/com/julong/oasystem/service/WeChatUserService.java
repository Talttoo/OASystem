package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：同步微信科室与成员Service
 */
public interface WeChatUserService {

	List<JSONObject> listWeChatOrg();
	
	JSONObject listWeChatUser(JSONObject jSONObject);
	
	
	JSONObject synchroTxl();
	
	JSONObject addUser(JSONObject jSONObject);
}
