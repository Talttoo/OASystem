package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.julong.oasystem.entity.DepartmentVO;
import com.julong.oasystem.service.AddressBookService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/5/1 0004 下午 14:52
 * @Description：通讯录
 */
@RestController
@RequestMapping("/wechattxl")
public class AddressBookController {

	@Autowired
	private AddressBookService AddressBookService;

	/**
	 * 获取微信部门列表数据
	 * @param request
	 * @return
	 */
	@GetMapping("/listOrg")
    public JSONObject listWebChatOrg(HttpServletRequest request) {

		System.out.println("通讯录部门请求："+request.toString());

		JSONArray jsonArray =  JSONArray.parseArray(JSON.toJSONString(AddressBookService.listDept()));

		List<DepartmentVO> departmentVOS =JSONObject.parseArray(jsonArray.toJSONString(), DepartmentVO.class);

		System.out.println("通讯录部门请求原始list:"+ departmentVOS.toString());

		List<DepartmentVO>  listtemp = TreeUtils.buidTree(departmentVOS);

		System.out.println("TreeList:"+listtemp.toString());


		return JsonResultUtil.successJson(listtemp);
	}

	/**
	 * 获取微信成员列表数据
	 * @param request
	 * @return
	 */
	@GetMapping("/listUser")
    public JSONObject listWebChatUser(HttpServletRequest request) {

		JSONObject pageList=AddressBookService.listtUser(JsonResultUtil.request2Json(request));
		return pageList;
	}


	/**
	 * 添加通信录联系人
	 * @param requestJson
	 * @return
	 */
	@PostMapping("/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
       
        return AddressBookService.addUser(requestJson);
    }
}
