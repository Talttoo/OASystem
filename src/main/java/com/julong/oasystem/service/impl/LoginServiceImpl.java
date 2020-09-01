package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.julong.oasystem.dao.LoginDao;
import com.julong.oasystem.dao.WeChatCacheDao;
import com.julong.oasystem.service.LoginService;
import com.julong.oasystem.service.PermissionService;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.Constants;
import com.julong.oasystem.wechat.WeChatCommon;
import com.julong.oasystem.wechat.WeChatUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author:
 * @description: 登录service实现类
 * @date:
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private WeChatCacheDao weChatCacheDao;

	@Autowired
	private WeChatConfigService webchatConfigService;

	/**
	 * 登录表单提交
	 */
	@Override
	public JSONObject authLogin(JSONObject jsonObject) {
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		JSONObject info = new JSONObject();
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			currentUser.login(token);
			info.put("result", "success");
		} catch (AuthenticationException e) {
			info.put("result", "fail");
		}
		System.out.println("info:"+info.toJSONString());
		return JsonResultUtil.successJson(info);
	}

	/**
	 * 根据用户名和密码查询对应的用户
	 */
	@Override
	public JSONObject getUser(String username, String password) {
		JSONObject jsonObject = loginDao.getUser(username, password);

		System.out.println("jsonObject:"+jsonObject.toJSONString());
		return  jsonObject;
		//return loginDao.getUser(username, password);
	}

	/**
	 * 查询当前登录用户的权限等信息
	 */
	@Override
	public JSONObject getInfo() {
		//从session获取用户信息
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getString("username");
		JSONObject info = new JSONObject();
		JSONObject userPermission = permissionService.getUserPermission(username);
		session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
		info.put("userPermission", userPermission);
		return JsonResultUtil.successJson(info);
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout() {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.logout();
		} catch (Exception e) {
		}
		return JsonResultUtil.successJson();
	}


	/**
	 * 获取微信token，先从本地数据库判断是否有未失效的，
	 * 有 则从本地读取出来，
	 * 没有 则从微信端获取来，在更新到数据库并缓存
	 * （最好是放缓存，此处还未加缓存）
	 * @return
	 */
	@Override
	public String getWeChatCache(JSONObject jsonObject1) {

		JsonResultUtil.fillOrgPk(jsonObject1);

		System.out.println("查找wechat_cache:"+jsonObject1.toJSONString());
		JSONObject jsonObject = weChatCacheDao.getWeChatCache(jsonObject1);

		//System.out.println("查找jsonObject:"+jsonObject.toJSONString());

		if(jsonObject==null) {   //失效或者不存在，调用微信接口获取并保存数据库
			JSONObject configObject = webchatConfigService.getByPkOrg(jsonObject1);
			System.out.println("查找configObject:"+configObject.toJSONString());
			String accessToken = WeChatUtil.getAccessToken(configObject.getString("cortid"), configObject.getString("secret"));
			String txlAccessToken = WeChatUtil.getAccessToken(configObject.getString("cortid"), configObject.getString("txlsecret"));
			String jspApiTicket = WeChatUtil.getJSApiTicket(accessToken);
			weChatCacheDao.deleteWeChatCache(jsonObject1);//清掉之前的数据
			JSONObject insertObject = new JSONObject();
			JsonResultUtil.fillOrgPk(insertObject);

			//微信的token和JSapiticket默认失效2小时
			insertObject.put("expireTime", this.getBeforeByHourTime(2));

			insertObject.put("key", WeChatCommon.ACCESSTOKEN);
			insertObject.put("value", accessToken);
			weChatCacheDao.insertWeChatCache(insertObject);

			insertObject.put("key", WeChatCommon.JSAPITICKET);
			insertObject.put("value", jspApiTicket);
			weChatCacheDao.insertWeChatCache(insertObject);

			insertObject.put("key", WeChatCommon.TXLJSAPITICKET);
			insertObject.put("value", txlAccessToken);
			weChatCacheDao.insertWeChatCache(insertObject);


			if(WeChatCommon.ACCESSTOKEN.equals(jsonObject1.getString("key"))){
				return accessToken;
			}else if(WeChatCommon.JSAPITICKET.equals(jsonObject1.getString("key"))){
				return jspApiTicket;
			}else {
				return txlAccessToken;
			}

		}else {                    //有则直接从数据库返回
			return jsonObject.getString("value");
		}
	}

	@Override
	public JSONObject getUserWeb2(String username) {
		return null;
	}


	/**
	 * 得到当前时间的后N小时
	 *
	 * @return
	 */
	public Date getBeforeByHourTime(int ihour){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + ihour);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");
		//returnstr = df.format(calendar.getTime());
		return calendar.getTime();
	}
}
