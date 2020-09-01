package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressBookDao {
	List<JSONObject> getWebChatUser(JSONObject jsonObject);
	
	int countWebChatUser(JSONObject jsonObject);
	
	int deleteWebChatUser(JSONObject jsonObject);
	
	int insertWebChatUser(JSONObject jsonObject);
}
