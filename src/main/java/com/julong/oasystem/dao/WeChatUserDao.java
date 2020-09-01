package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface WeChatUserDao {

	List<JSONObject> getWeChatUser(JSONObject jsonObject);
	
	int countWeChatUser(JSONObject jsonObject);
	
	int deleteWeChatUser(JSONObject jsonObject);
	
	int insertWeChatUser(JSONObject jsonObject);
}
