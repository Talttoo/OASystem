package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

/**
 * @author: lyt
 * @description: 登录相关dao
 * @date:
 */
public interface LoginDao {
	/**
	 * 根据用户名和密码查询对应的用户
	 */
	JSONObject getUser(@Param("username") String username, @Param("password") String password);

	JSONObject getUserByWebUserName(JSONObject jsonObject);

}
