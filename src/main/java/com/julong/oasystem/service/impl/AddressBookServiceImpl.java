package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.LoginDao;
import com.julong.oasystem.dao.UserDao;
import com.julong.oasystem.dao.DeptDao;
import com.julong.oasystem.dao.AddressBookDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.AddressBookService;
import com.julong.oasystem.service.LoginService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/5/1 0004 下午 14:52
 * @Description：通讯录
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {



    @Autowired
    private LoginService loginService;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private AddressBookDao addressBookDao;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UserDao userDao;

    @Override
	public List<JSONObject> listDept() {
		// TODO Auto-generated method stub
		JSONObject queryJsonObject = new JSONObject();
		//JsonResultUtil.fillOrgPk(queryJsonObject);
    	List<JSONObject>  list = deptDao.selectList(queryJsonObject);
        System.out.println("转换前list:"+list.toString());

		return list;
	}

    @Override
	public JSONObject listtUser(JSONObject jsonObject) {
        System.out.println("请求通讯录数据："+jsonObject.toJSONString());
    	JsonResultUtil.fillPageParam(jsonObject);
		//JsonResultUtil.fillOrgPk(jsonObject);
		int pageNum = jsonObject.getIntValue("pageNum");
		int pageRow = jsonObject.getIntValue("pageRow");
		Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
    	List<JSONObject> list = addressBookDao.getWebChatUser(jsonObject);
		//    	int count = addressBookDao.countWebChatUser(jsonObject);
    	JSONObject pageList= JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());

        //System.out.println("list:"+list.toString()+"  pageList: "+pageList.toJSONString());
    	return pageList;
	}

	@Override
	public JSONObject synchroTxl() {
		return null;
	}


	public List<JSONObject> orgList(List<JSONObject> orgList){
		List<JSONObject>  list = new ArrayList<JSONObject>();
		for (JSONObject obj : orgList) {
			if("0".equals(obj.getString("parentid"))){
				obj.put("children", orgChild(obj.getString("id"),orgList));
				list.add(obj);
			}
		}
        //System.out.println("list:"+list.toString());
		return list;
	}


	public List<JSONObject> orgChild(String  id, List<JSONObject> orgList){
		List<JSONObject> lists = new ArrayList<JSONObject>();
		for(JSONObject a:orgList){
			if(a.getString("parentid").equals(id)){
				a.put("children", orgChild(a.getString("id"),orgList));
				lists.add(a);
			}
		}
		return lists;

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
