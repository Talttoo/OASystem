package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.DepartmentVO;
import com.julong.oasystem.service.WeChatUserService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：同步微信科室与成员
 */
@RestController
@RequestMapping("/webchattxl")
public class WeChatUserController {

	@Autowired
	private WeChatUserService weChatUserService;

	/**
	 * 获取通讯录部门数据
	 * @param request
	 * @return
	 */
	@GetMapping("/listOrg")
    public JSONObject listWebChatOrg(HttpServletRequest request) {

		JSONArray jsonArray =  JSONArray.parseArray(JSON.toJSONString(weChatUserService.listWeChatOrg()));

		List<DepartmentVO> departmentVOS =JSONObject.parseArray(jsonArray.toJSONString(), DepartmentVO.class);

		System.out.println("通讯录部门请求原始list:"+ departmentVOS.toString());

		List<DepartmentVO>  listtemp = TreeUtils.buidTree(departmentVOS);

		System.out.println("通讯录部门最终list:"+listtemp.toString());
		return JsonResultUtil.successJson(listtemp);
	}

	/**
	 * 获取微信通讯录成员
	 * @param request
	 * @return
	 */
	@GetMapping("/listUser")
    public JSONObject listWebChatUser(HttpServletRequest request) {
		JSONObject pageList=weChatUserService.listWeChatUser(JsonResultUtil.request2Json(request));
		return pageList;
	}

	/**
	 * 同步微信通讯录
	 * @param request
	 * @return
	 */
	@GetMapping("/sysnTxl")
    public JSONObject sysnTxl(HttpServletRequest request) {
		weChatUserService.synchroTxl();
		return JsonResultUtil.successJson();
	}

	/**
	 * 新增通讯录成员
	 * @param requestJson
	 * @return
	 */
	@PostMapping("/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
       
        return weChatUserService.addUser(requestJson);
    }
}
