package com.julong.oasystem.utils;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.config.exception.CommonJsonException;
import com.julong.oasystem.entity.ArticleVO;
import com.julong.oasystem.entity.CriticalMessageVO;
import com.julong.oasystem.entity.ScheduleVO;
import com.julong.oasystem.utils.constants.Constants;
import com.julong.oasystem.utils.constants.ErrorEnum;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;


import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author: lyt
 * @description: 本后台接口系统常用的json工具类
 * @date:
 */
public class JsonResultUtil {

	/**
	 * 根据修改搜影响的行数返回结果
	 */
	public static JSONObject result(int i)
	{
		return i > 0 ? successJson() : errorJson(ErrorEnum.E_90003);
	}

	/**
	 * 返回一个info为空对象的成功消息的json
	 */
	public static JSONObject successJson() {
		return successJson(new JSONObject());
	}

	/**
	 * 返回一个返回码为100的json
	 */
	public static JSONObject successJson(Object info) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", Constants.SUCCESS_CODE);
		resultJson.put("msg", Constants.SUCCESS_MSG);
		resultJson.put("info", info);

		System.out.println("resultJson:"+resultJson.toJSONString());

		return resultJson;
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject errorJson(ErrorEnum errorEnum) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", errorEnum.getErrorCode());
		resultJson.put("msg", errorEnum.getErrorMsg());
		resultJson.put("info", new JSONObject());
		return resultJson;
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject errorJson(int code,String error) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", code);
		resultJson.put("msg", error);
		resultJson.put("info", new JSONObject());
		return resultJson;
	}

	/**
	 * 返回错误信息JSON
	 */
	public static JSONObject resultJson(int code,String error) {
		JSONObject resultJson = new JSONObject();
		resultJson.put("code", code);
		resultJson.put("msg", error);
//		resultJson.put("info", new JSONObject());
		return resultJson;
	}
	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(final JSONObject requestJson, List<JSONObject> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		//System.out.println("result:"+result.toJSONString());
		return result;
	}

	public static JSONObject successPageObject(final JSONObject requestJson, Object list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		//System.out.println("result:"+result.toJSONString());
		return result;
	}

	public static JSONObject successPageSchedule(final JSONObject requestJson, List<ScheduleVO> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		//System.out.println("result:"+result.toJSONString());
		return result;
	}
	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPageMessage(final JSONObject requestJson, List<CriticalMessageVO> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		System.out.println("result:"+result.toJSONString());
		return result;
	}


	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param requestJson 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPageArticle(final JSONObject requestJson, List<ArticleVO> list, int totalCount) {
		int pageRow = requestJson.getIntValue("pageRow");
		int totalPage = getPageCounts(pageRow, totalCount);
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		System.out.println("result:"+result.toJSONString());
		return result;
	}


	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param totalPage 请求参数json,此json在之前调用fillPageParam 方法时,已经将pageRow放入
	 * @param list        查询分页对象list
	 * @param totalCount  查询出记录的总条数
	 */
	public static JSONObject successPage(long totalCount, int totalPage, List<JSONObject> list) {

		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		info.put("totalCount", totalCount);
		info.put("totalPage", totalPage);
		result.put("info", info);
		System.out.println("result:"+result.toJSONString());
		return result;
	}

	/**
	 * 查询分页结果后的封装工具方法
	 *
	 * @param list 查询分页对象list
	 */
	public static JSONObject successPage(List<JSONObject> list) {
		JSONObject result = successJson();
		JSONObject info = new JSONObject();
		info.put("list", list);
		result.put("info", info);
		return result;
	}

	/**
	 * 获取总页数
	 *
	 * @param pageRow   每页行数
	 * @param itemCount 结果的总条数
	 */
	private static int getPageCounts(int pageRow, int itemCount) {
		if (itemCount == 0) {
			return 1;
		}
		return itemCount % pageRow > 0 ?
				itemCount / pageRow + 1 :
				itemCount / pageRow;
	}

	/**
	 * 将request参数值转为json
	 */
	public static JSONObject request2Json(HttpServletRequest request) {
		JSONObject requestJson = new JSONObject();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			String[] pv = request.getParameterValues(paramName);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < pv.length; i++) {
				if (pv[i].length() > 0) {
					if (i > 0) {
						sb.append(",");
					}
					sb.append(pv[i]);
				}
			}
			requestJson.put(paramName, sb.toString());
		}
		return requestJson;
	}

	/**
	 * 将request转JSON
	 * 并且验证非空字段
	 */
	public static JSONObject convert2JsonAndCheckRequiredColumns(HttpServletRequest request, String requiredColumns) {
		JSONObject jsonObject = request2Json(request);
		hasAllRequired(jsonObject, requiredColumns);
		return jsonObject;
	}

	/**
	 * 验证是否含有全部必填字段
	 *
	 * @param requiredColumns 必填的参数字段名称 逗号隔开 比如"userId,name,telephone"
	 */
	public static void hasAllRequired(final JSONObject jsonObject, String requiredColumns) {
		if (!StringTools.isNullOrEmpty(requiredColumns)) {
			//验证字段非空
			String[] columns = requiredColumns.split(",");
			String missCol = "";
			for (String column : columns) {
				Object val = jsonObject.get(column.trim());
				if (StringTools.isNullOrEmpty(val)) {
					missCol += column + "  ";
				}
			}
			if (!StringTools.isNullOrEmpty(missCol)) {
				jsonObject.clear();
				jsonObject.put("code", ErrorEnum.E_90003.getErrorCode());
				jsonObject.put("msg", "缺少必填参数:" + missCol.trim());
				jsonObject.put("info", new JSONObject());
				throw new CommonJsonException(jsonObject);
			}
		}
	}

	/**
	 * 在分页查询之前,为查询条件里加上分页参数
	 *
	 * @param paramObject    查询条件json
	 * @param defaultPageRow 默认的每页条数,即前端不传pageRow参数时的每页条数
	 */
	private static void fillPageParam(final JSONObject paramObject, int defaultPageRow) {
		int pageNum = paramObject.getIntValue("pageNum");
		pageNum = pageNum == 0 ? 1 : pageNum;
		int pageRow = paramObject.getIntValue("pageRow");
		pageRow = pageRow == 0 ? defaultPageRow : pageRow;
		paramObject.put("offSet", (pageNum - 1) * pageRow);
		paramObject.put("pageRow", pageRow);
		paramObject.put("pageNum", pageNum);
		//删除此参数,防止前端传了这个参数,pageHelper分页插件检测到之后,拦截导致SQL错误
		paramObject.remove("pageSize");
	}

	/**
	 * 分页查询之前的处理参数
	 * 没有传pageRow参数时,默认每页10条.
	 */
	public static void fillPageParam(final JSONObject paramObject) {
		fillPageParam(paramObject, 10);
	}

	/**
	 *
	 * @param paramObject
	 */
	public static void fillOrgPk(final JSONObject paramObject){
		//JSONObject object = (JSONObject) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		// admin可以查全部
//       if(!"admin".equals(SecurityUtils.getSubject().getPrincipal().toString())){
//		paramObject.put("orgPk",object.getString("orgPk"));
//        }
		paramObject.put("orgPk","1000011");

	}

	public static String getCurrentTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date());
	}

	public static String formatDate(String dateStr){
		SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
		dateFormat.applyPattern("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return  date.toString();

	}

	public static String  getUserid(){
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		//String username = userInfo.getString("username");
		String userId = userInfo.getString("userId");
		return userId;
	}
	public static String  getUserName(){
		Session session = SecurityUtils.getSubject().getSession();
		JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
		String username = userInfo.getString("username");
		//String userId = userInfo.getString("userId");
		return username;
	}
}
