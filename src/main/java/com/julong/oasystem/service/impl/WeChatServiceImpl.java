package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.julong.oasystem.service.LoginService;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.service.WeChatService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.wechat.WeChatCommon;
import com.julong.oasystem.wechat.WeChatSign;
import com.julong.oasystem.wechat.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 获取微信SDK签名类
 * @author
 *
 */

@Service
public class WeChatServiceImpl implements WeChatService {

	@Autowired
	private LoginService loginService;

	@Autowired
	private WeChatConfigService wechatConfigService;
	
	@Override
	public JSONObject getSignInfo(JSONObject jsonObject) {
		// TODO Auto-generated method stub
		
		//获取有效的JSAPITICK
		JSONObject queryObject = new JSONObject();
		queryObject.put("key", WeChatCommon.JSAPITICKET);
		String jsapiticke = loginService.getWeChatCache(queryObject);

		JsonResultUtil.fillOrgPk(queryObject);

		JSONObject configObject = wechatConfigService.getByPkOrg(queryObject);
	
		return WeChatSign.sign(jsapiticke, jsonObject.getString("url"),configObject.getString("cortid"));
	}

	@Override
	public void sendMessageWebChat(JSONObject content) {
		// TODO Auto-generated method stub
		JSONObject queryObject = new JSONObject();
		queryObject.put("key", WeChatCommon.ACCESSTOKEN);
		String accesstoken = loginService.getWeChatCache(queryObject);
	
		WeChatUtil.webchatSendMessage(content, accesstoken);
	}

}
