package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.DeptAndUserVO;

import java.util.List;

public interface DeptDao {

	int update(JSONObject jsonObject);

	int sysnyc(JSONObject jsonObject);

	int insert(JSONObject jsonObject);

	List<JSONObject> listByPage(JSONObject jsonObject);

	int delete(Long id);


	int count(JSONObject jsonObject);
	List<JSONObject> selectList(JSONObject jsonObject);

	List<DeptAndUserVO> selectDeptAndUser();


	List<JSONObject> getWeChatDep(JSONObject jsonObject);
	
	int deleteWeChatDep(JSONObject jsonObject);

	int insertWeChatDep(JSONObject jsonObject);


}
