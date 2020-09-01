package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.UserDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.UserService;
import com.julong.oasystem.utils.CommonUtils;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author: lyt
 * @description: 用户/角色/权限
 * @date:
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Autowired
	private CommonUtils commonUtils;
	/**
	 * 用户列表
	 */
	@Override
	public JSONObject listUser(JSONObject jsonObject) {
		JsonResultUtil.fillPageParam(jsonObject);
//		int count = userDao.countUser(jsonObject);
		//JSONObject requestJson = JsonResultUtil.request2Json(request);
		int pageNum = jsonObject.getIntValue("pageNum");
		int pageRow = jsonObject.getIntValue("pageRow");


		//使用分页插件,核心代码就这一行，页数、每页行数
		Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
		List<JSONObject> list = userDao.listUser(jsonObject);

		System.out.println("userlist:++++++++++"+list.toString()+"+++++++++++++++++");

		return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
	}

	/**
	 * 添加用户
	 */
	@Override
	public JSONObject addUser(JSONObject jsonObject) {
		int exist = userDao.queryExistUsername(jsonObject);
		if (exist > 0) {
			return JsonResultUtil.errorJson(ErrorEnum.E_10009);
		}
		int res = userDao.addUser(jsonObject);
		if(res>0){
			return JsonResultUtil.successJson();
		}else {
			return JsonResultUtil.errorJson(401,"插入数据异常");
		}
	}

	/**
	 * 查询所有的角色
	 * 在添加/修改用户的时候要使用此方法
	 */
	@Override
	public JSONObject getAllRoles() {
		List<JSONObject> roles = userDao.getAllRoles();
		return JsonResultUtil.successPage(roles);
	}

	/**
	 * 修改用户
	 */
	@Override
	public JSONObject updateUser(JSONObject jsonObject) {
		int res = userDao.updateUser(jsonObject);
		if(res>0){
			return JsonResultUtil.successJson();
		}else {
			return JsonResultUtil.errorJson(401,"更新数据异常");
		}
	}

	/**
	 * 角色列表
	 */
	@Override
	public JSONObject listRole() {
		List<JSONObject> roles = userDao.listRole();
		return JsonResultUtil.successPage(roles);
	}

	/**
	 * 查询所有权限, 给角色分配权限时调用
	 */
	@Override
	public JSONObject listAllPermission() {
		List<JSONObject> permissions = userDao.listAllPermission();
		return JsonResultUtil.successPage(permissions);
	}

	/**
	 * 添加角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@Override
	public JSONObject addRole(JSONObject jsonObject) {
		jsonObject.put("roleId", commonUtils.getUUID());
		userDao.insertRole(jsonObject);
		userDao.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permissions"));
		return JsonResultUtil.successJson();
	}

	/**
	 * 修改角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject updateRole(JSONObject jsonObject) {
		String roleId = jsonObject.getString("roleId");
		List<Integer> newPerms = (List<Integer>) jsonObject.get("permissions");
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
		Set<Integer> oldPerms = (Set<Integer>) roleInfo.get("permissionIds");
		//修改角色名称
		dealRoleName(jsonObject, roleInfo);
		//添加新权限
		saveNewPermission(roleId, newPerms, oldPerms);
		//移除旧的不再拥有的权限
		removeOldPermission(roleId, newPerms, oldPerms);
		return JsonResultUtil.successJson();
	}

	/**
	 * 修改角色名称
	 */
	@Transactional(rollbackFor = Exception.class)
	public void dealRoleName(JSONObject paramJson, JSONObject roleInfo) {
		String roleName = paramJson.getString("roleName");
		if (!roleName.equals(roleInfo.getString("roleName"))) {
			userDao.updateRoleName(paramJson);
		}
	}

	/**
	 * 为角色添加新权限
	 */
	@Transactional(rollbackFor = Exception.class)
	public void saveNewPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> waitInsert = new ArrayList<>();
		for (Integer newPerm : newPerms) {
			if (!oldPerms.contains(newPerm)) {
				waitInsert.add(newPerm);
			}
		}
		if (waitInsert.size() > 0) {
			userDao.insertRolePermission(roleId, waitInsert);
		}
	}

	/**
	 * 删除角色 旧的 不再拥有的权限
	 */
	@Transactional(rollbackFor = Exception.class)
	public void removeOldPermission(String roleId, Collection<Integer> newPerms, Collection<Integer> oldPerms) {
		List<Integer> waitRemove = new ArrayList<>();
		for (Integer oldPerm : oldPerms) {
			if (!newPerms.contains(oldPerm)) {
				waitRemove.add(oldPerm);
			}
		}
		if (waitRemove.size() > 0) {
			userDao.removeOldPermission(roleId, waitRemove);
		}
	}

	/**
	 * 删除角色
	 */
	@Transactional(rollbackFor = Exception.class)
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject deleteRole(JSONObject jsonObject) {
		JSONObject roleInfo = userDao.getRoleAllInfo(jsonObject);
		List<JSONObject> users = (List<JSONObject>) roleInfo.get("users");
		if (users != null && users.size() > 0) {
			return JsonResultUtil.errorJson(ErrorEnum.E_10008);
		}
		userDao.removeRole(jsonObject);
		userDao.removeRoleAllPermission(jsonObject);
		return JsonResultUtil.successJson();
	}

	/**
	 * 查找管理员
	 * @param jsonObject
	 * @return
	 */
	@Override
	public JSONObject selectAdmin(JSONObject jsonObject){
		List<JSONObject> admin = userDao.selectAdmin(jsonObject);
		return JsonResultUtil.successPage(admin);
	}
}
