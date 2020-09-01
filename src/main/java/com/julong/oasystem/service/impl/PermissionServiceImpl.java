package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.julong.oasystem.dao.PermissionDao;
import com.julong.oasystem.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author: lyt
 * @date:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao;

	/**
	 * 查询某用户的 角色  菜单列表   权限列表
	 */
	@Override
	public JSONObject getUserPermission(String username) {
		JSONObject userPermission = getUserPermissionFromDB(username);
		return userPermission;
	}

	/**
	 * 从数据库查询用户权限信息
	 */
	private JSONObject getUserPermissionFromDB(String username) {
		JSONObject userPermission = permissionDao.getUserPermission(username);
		//管理员角色ID为1
		String  adminRoleId = "1";
		//如果是管理员
		String roleIdKey = "roleId";
		if (adminRoleId.equals( userPermission.getString(roleIdKey))) {
			//查询所有菜单  所有权限
			Set<String> menuList = permissionDao.getAllMenu();
			Set<String> permissionList = permissionDao.getAllPermission();
			userPermission.put("menuList", menuList);
			userPermission.put("permissionList", permissionList);
		}
		System.out.println("userPermission:"+userPermission.toString());
		return userPermission;
	}
}
