package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.DeptDao;
import com.julong.oasystem.dao.LoginDao;
import com.julong.oasystem.dao.UserDao;
import com.julong.oasystem.dao.WeChatUserDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.LoginService;
import com.julong.oasystem.service.WeChatUserService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.wechat.WeChatCommon;
import com.julong.oasystem.wechat.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：同步微信科室与成员Service实现类
 */
@Service
public class WeChatUserServiceImpl implements WeChatUserService {

	
	 
    @Autowired
    private LoginService loginService;
    
    @Autowired
    private DeptDao deptDao;
    
    @Autowired
    private WeChatUserDao weChatUserDao;
    
    @Autowired
    private LoginDao loginDao;
    
    @Autowired
    private UserDao userDao;
    
    @Override
	public List<JSONObject> listWeChatOrg() {
		// TODO Auto-generated method stub
		JSONObject queryJsonObject = new JSONObject();
		//JsonResultUtil.fillOrgPk(queryJsonObject);
		List<JSONObject>  list = deptDao.selectList(queryJsonObject);
		//System.out.println("转换前list:"+list.toString());
		//List<JSONObject>  listtemp = this.orgList(list);

		return list;
	}
    
    @Override
	public JSONObject listWeChatUser(JSONObject jsonObject) {
    	JsonResultUtil.fillPageParam(jsonObject);
		JsonResultUtil.fillOrgPk(jsonObject);
//    	int count = weChatUserDao.countWeChatUser(jsonObject);
		int pageNum = jsonObject.getIntValue("pageNum");
		int pageRow = jsonObject.getIntValue("pageRow");
		Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
    	List<JSONObject> list = weChatUserDao.getWeChatUser(jsonObject);
    	JSONObject pageList= JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    	return pageList;
	}
    
    
    public List<JSONObject> orgList(List<JSONObject> orgList){
		List<JSONObject>  list = new ArrayList<JSONObject>();
		for (JSONObject obj : orgList) {
			if("0".equals(obj.getString("parentid"))){
				obj.put("children", orgChild(obj.getString("id"),orgList));
				list.add(obj);
			}
		}	
		return list;
	}

	
	public List<JSONObject> orgChild(String id, List<JSONObject> orgList){
		List<JSONObject> lists = new ArrayList<JSONObject>();
		for(JSONObject a:orgList){
			if(a.getString("parentid").equals(id)){
				a.put("children", orgChild(a.getString("id"),orgList));	
				lists.add(a);
			}
		}
		return lists;
		
	}

	@Transactional(rollbackFor = Exception.class)
	@Override
	public JSONObject synchroTxl() {
		
		
		JSONObject queryJsonObject = new JSONObject();
		queryJsonObject.put("key", WeChatCommon.TXLJSAPITICKET);
		String accessToken = loginService.getWeChatCache(queryJsonObject);
		// TODO Auto-generated method stub
		JsonResultUtil.fillOrgPk(queryJsonObject);
		//微信通讯录先清空，在增加
		deptDao.deleteWeChatDep(queryJsonObject);
		weChatUserDao.deleteWeChatUser(queryJsonObject);
		
		this.syschDep(accessToken);
		this.syschUser(accessToken);
		return JsonResultUtil.successJson();
	}


	private void syschDep(String accessToken) {

		JSONArray orgList= WeChatUtil.getAllDep(accessToken).getJSONArray("department");

		JSONObject orgJson = null;
		JSONObject orgJsontemp = null;
		System.out.println("orgList:"+orgList.toJSONString());

		for(int i= 0;i<orgList.size();i++) {
			orgJson = orgList.getJSONObject(i);
			orgJsontemp = new JSONObject();
			orgJsontemp.put("id", orgJson.getString("id"));
			orgJsontemp.put("deptname", orgJson.getString("name"));
			orgJsontemp.put("parentid", orgJson.getString("parentid"));
			JsonResultUtil.fillOrgPk(orgJsontemp);
			deptDao.insertWeChatDep(orgJsontemp);
		}

	}


//	private void syschDep(String accessToken) {
//		JSONArray orgList= WeChatUtil.getAllDep(accessToken).getJSONArray("department");
//
//		JSONObject orgJson = null;
//		JSONObject orgJsontemp = null;
//
//
//		for(int i= 0;i<orgList.size();i++) {
//			orgJson = orgList.getJSONObject(i);
//			orgJsontemp = new JSONObject();
//			orgJsontemp.put("id", orgJson.getString("id"));
//			orgJsontemp.put("name", orgJson.getString("name"));
//			orgJsontemp.put("parentid", orgJson.getString("parentid"));
//			JsonResultUtil.fillOrgPk(orgJsontemp);
//			deptDao.insertWeChatDep(orgJsontemp);
//		}
//
//	}
	
	private void syschUser(String accessToken) {
		
		
		JSONObject userJson = null;
		JSONObject userJsontemp = null;
		
		JSONArray userList= WeChatUtil.getAllUser(accessToken).getJSONArray("userlist");
		
		for(int i=0;i<userList.size();i++) {
			userJson = userList.getJSONObject(i);
			userJsontemp= new JSONObject();
			userJsontemp.put("name", userJson.getString("name"));
			userJsontemp.put("userid", userJson.getString("userid"));
			userJsontemp.put("gender", userJson.getString("gender"));
			userJsontemp.put("mobile", userJson.getString("mobile"));
			userJsontemp.put("email", userJson.getString("email"));
			userJsontemp.put("avatar", userJson.getString("avatar"));
			userJsontemp.put("main_department", userJson.getString("main_department"));
			userJsontemp.put("status", userJson.getString("status"));
			JsonResultUtil.fillOrgPk(userJsontemp);
			weChatUserDao.insertWeChatUser(userJsontemp);
			
		}
	}

	@Override
	public JSONObject addUser(JSONObject userObject) {
		// TODO Auto-generated method stub

		JSONObject jsonObject = loginDao.getUserByWebUserName(userObject);
		if(jsonObject == null) {//不存在，新增
			JsonResultUtil.fillOrgPk(userObject);
			userDao.addUser(userObject);
		}else {
			userDao.updateUserWebchat(userObject);
		}
		
		return JsonResultUtil.successJson();
	}

}
