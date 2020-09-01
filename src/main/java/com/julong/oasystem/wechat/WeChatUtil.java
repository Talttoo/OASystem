package com.julong.oasystem.wechat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/***
 * 
 * @author 获取微信授权信息
 *
 * 
 * 
 */

public class WeChatUtil {

	/***
	 * 模拟get请求
	 * @param url
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static String sendGet(String url, String charset, int timeout)
	{
		String result = "";
		try
		{
			URL u = new URL(url);
			System.out.println("url="+url);
			try
			{
				URLConnection conn = u.openConnection();
				conn.connect();
				conn.setConnectTimeout(timeout);
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
				String line = "";
				while ((line = in.readLine()) != null)
				{
					result = result + line;
				}
				in.close();
			} catch (IOException e) {
				return result;
			}
		}
		catch (MalformedURLException e)
		{
			return result;
		}
		return result;

	}
	
	/***
	 * 模拟post请求
	 * @param url
	 * @param charset
	 * @param timeout
	 * @return
	 */
	public static String sendPost(String url, String charset, int timeout,String jsonStr)
	{
		String result = "";
		try
		{
			URL u = new URL(url);
			try
			{
				HttpURLConnection connection = (HttpURLConnection) u.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setConnectTimeout(timeout);
	            connection.setRequestMethod("POST"); // 设置请求方式
	            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式
	            connection.setRequestProperty("Content-Type", "application/json");
	            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
	            out.append(jsonStr);
	            out.flush();
	            out.close();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
				String line = "";
				while ((line = in.readLine()) != null)
				{
					result = result + line;
				}
				in.close();
			} catch (IOException e) {
				return result;
			}
		}
		catch (MalformedURLException e)
		{
			return result;
		}
		return result;

	}

	/***
	 * 获取acess_token
	 * 来源www.vxzsk.com
	 * @return
	 */

	public static String getAccessToken(String appid,String appSecret) {

		String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+appid+"&corpsecret="+appSecret;
		
		String backData = WeChatUtil.sendGet(url, "utf-8", 10000);

		String accessToken = JSON.parseObject(backData).getString("access_token") ;

		return accessToken;

	}

	/***
	 * 
	 * 获取jsapiTicket
	 * 
	 * 来源 www.vxzsk.com
	 * 
	 * @return
	 * 
	 */

	public static String getJSApiTicket(String acessToken) {


		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+acessToken;

		String backData = WeChatUtil.sendGet(urlStr, "utf-8", 10000);

		String ticket = JSON.parseObject(backData).getString("ticket");

		return ticket;

	}
	
	
	
	/***
	 * 
	 * 微信根据code获取成员信息 见微信开发文档
	 * 
	 */

	public static JSONObject getUserByCode(String acessToken, String code) {


		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+acessToken+"&code="+code;

		String backData = WeChatUtil.sendGet(urlStr, "utf-8", 10000);

		return  JSON.parseObject(backData);
	}
	
	/***
	 * 
	 * 微信根据user_ticket获取成员详细信息 见微信开发文档
	 * 
	 */

	public static JSONObject getUserDetailByUserTicket(String userDetailTicket, String accessToken) {


		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token="+accessToken;
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("user_ticket", userDetailTicket);
		String backData = WeChatUtil.sendPost(urlStr, "utf-8", 10000,jsonObject.toJSONString());

		return  JSON.parseObject(backData);
	}
	
	
	/***
	 * 
	 * 微信根据user_ticket获取成员详细信息 见微信开发文档
	 * 
	 */

	public static JSONObject webchatSendMessage(JSONObject jsonObject, String accessToken) {



		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+accessToken;
		
		String backData = WeChatUtil.sendPost(urlStr, "utf-8", 10000,jsonObject.toJSONString());

		return  JSON.parseObject(backData);
	}
	
	/***
	 * 
	 * 微信根据所有用户 见微信开发文档
	 * 
	 */

	public static JSONObject getAllUser(String accessToken) {


		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token="+accessToken+"&department_id=1&fetch_child=1";
		
		String backData = WeChatUtil.sendGet(urlStr, "utf-8", 10000);

		return  JSON.parseObject(backData);
	}
	
	/***
	 * 
	 * 微信根据所有部门 见微信开发文档
	 * 
	 */

	public static JSONObject getAllDep(String accessToken) {

		
		String urlStr = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken+"&id=";
		
		String backData = WeChatUtil.sendGet(urlStr, "utf-8", 10000);

		return  JSON.parseObject(backData);
	}

	public static JSONObject sendRemindMessage(JSONObject jsonParam,JSONObject requestJson) {


		String token= getAccessToken(jsonParam.getString("appid"), jsonParam.getString("appSecret"));
		System.out.println("=="+token+"==");
		JSONObject msg=new JSONObject();

		msg.put("touser",requestJson.getString("userid"));
		msg.put("toparty", requestJson.getString("dept"));
		msg.put("totag", "");
		msg.put("msgtype", "text");
		msg.put("agentid", jsonParam.getString("agentid"));
		JSONObject content=new JSONObject();
		content.put("content", requestJson.getString("content"));
		msg.put("text", content);
		msg.put("safe", "0");
		msg.put("enable_id_trans", "0");
		msg.put("enable_duplicate_check", "0");
		msg.put("duplicate_check_interval", "1800");

		JSONObject result = webchatSendMessage(msg, token);

		return  result;
	}

	public static JSONObject sendRemindTextcard(JSONObject jsonParam,JSONObject requestJson) {

		String token= getAccessToken(jsonParam.getString("appid"), jsonParam.getString("appSecret"));
		System.out.println("=="+token+"==");
		JSONObject msg=new JSONObject();
		System.out.println("doctorid:"+requestJson.getString("doctorid"));
		msg.put("touser","LuoYuTao");
		msg.put("toparty", "");
		msg.put("totag", "");
		msg.put("msgtype", "textcard");
		msg.put("agentid", jsonParam.getString("agentid"));

		JSONObject content=new JSONObject();

		content.put("title", "危急值提醒");
		content.put("description", "<div class=\"gray\">"+ requestJson.getString("inTimes")+"</div> <div class=\"normal\">"+"姓名："+requestJson.getString("name")+"<br/>检验报告号："+requestJson.getString("repNo")+"<br/>科室："+requestJson.getString("deptName")+"<br/>检验结果为："+requestJson.getString("criticalNote")+""+"</div><div class=\"highlight\">请立即前往处理</div>");
		content.put("url", "http://localhost:9092/");
		content.put("btntxt", "更多");

		msg.put("textcard", content);
		msg.put("safe", "0");
		msg.put("enable_id_trans", "0");
		msg.put("enable_duplicate_check", "0");
		msg.put("duplicate_check_interval", "1800");

		JSONObject result = webchatSendMessage(msg, token);

		return  result;
	}



	public static void main(String[] args) {

		String token=getAccessToken("wx38736205f9dceb69",
				"VPIMP5d6xWnyONakqThoxLHcxHfjcHzxv3ggnO0VeYs");
		System.out.println("=="+token+"==");
		//String token="RbC47RCxJdl4mS8PYK3I2a7ygZSubTkG-mWFb42lO1O5Yaxd8zOU94baf4q4AushUoD2tDVy7jRCxZWGtkVHBTs7nwLmEl60w41hO-IQf196z0zJJy619pOJO95UiPLeVLhvQUBxIzzHs-sN3euVLURK33gKaGwVTey6jSM2UBlZFWCMeSdQRr_YGIMED8_wuGuZIqcdc2JextxPpHPnCQ";
		JSONObject msg=new JSONObject();
		msg.put("touser","jl_lyt");
		msg.put("toparty", "");
		msg.put("totag", "");
		msg.put("msgtype", "textcard");
		msg.put("agentid", "1000003");
		JSONObject content=new JSONObject();
//		content.put("content", "测试发送信息23333");
		content.put("title", "危急值提醒");
		content.put("description", "<div class=\"gray\">2020年7月8日</div> <div class=\"normal\">1号病人检验结果异常，结果为：xxxx</div><div class=\"highlight\">请前往处理</div>");
		content.put("url", "URL");
		content.put("btntxt", "更多");
		msg.put("textcard", content);
		msg.put("safe", "0");
		msg.put("enable_id_trans", "0");
		msg.put("enable_duplicate_check", "0");
		msg.put("duplicate_check_interval", "1800");
		JSONObject result= WeChatUtil.webchatSendMessage(msg, token);
//		JSONObject result= WeChatUtil.getAllDep(token);

		System.out.println("result:"+result.toJSONString());

		
//		String toke=getAccessToken("wx38736205f9dceb69", "X0Zt4cC2OEGkqLygGSDy_jtjM6mJE4d_A9kXIELwdQk");
//	 	System.out.println("=="+toke);

	}

}