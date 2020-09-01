package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：获取微信SDK签名类
 */
public interface WeChatService {

	JSONObject getSignInfo(JSONObject jsonObject);
	
	void sendMessageWebChat(JSONObject jsonObject);
}
