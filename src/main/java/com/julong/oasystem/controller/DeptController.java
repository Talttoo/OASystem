package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.DeptAndUserVO;
import com.julong.oasystem.entity.User;
import com.julong.oasystem.service.DeptService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * @Author Taltoo
 * @Date 2020/5/12 0004 下午 15:02
 * @Description：科室
 */
@RestController
@RequestMapping("dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 新增部门
     * @param requestJson
     * @return
     */
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {
        return deptService.insert(requestJson);
    }

    /**
     * 同步部门数据
     * @param requestJson
     * @return
     */
    @PostMapping("sysnyc")
    public JSONObject sysnyc(@RequestBody JSONObject requestJson) {

        return deptService.sysnyc(requestJson);
    }

    /**
     * 更新部门数据
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return deptService.update(requestJson);
    }

    /**
     * 删除部门数据
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return deptService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取部门列表数据
     * @param request
     * @return
     */
    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        System.out.println("请求部门列表：");
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return deptService.list(jsonObject);
    }

    /**
     * 获取部门数据，用于下拉列表框
     * @param request
     * @return
     */
    @GetMapping("selectList")
    public JSONObject selectList(HttpServletRequest request) {
        System.out.println("请求科室列表++++++++");
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return deptService.selectList(jsonObject);
    }

    /**
     *
     * @描述: 查询所有部门下的所有用户 用户归类 树状数据
     *
     * @date:
     */
    @RequestMapping("/getDeptAndUserTreeData")
    @ResponseBody
    public JSONObject DeptAndUserTreeData()
    {
        System.out.println("获取所有部门及用户......");
        List<DeptAndUserVO> depts = deptService.selectDeptAndUser();

        List<User> users =new ArrayList<>();
        LinkedList<Object>  deptAndUserList = new LinkedList<>();
        Map<String, Object> deptAndUserMap = new HashMap();
        LinkedList<Object> deptList = new LinkedList<>();
        for (DeptAndUserVO dept : depts) {
            if(dept.getParentid().equals("0")) {
                deptAndUserMap.put("name", dept.getDeptName());
                deptAndUserMap.put("id", null);
            }else {
                Map<String, Object> deptMap = new HashMap();
                deptMap.put("name", dept.getDeptName());
                deptMap.put("id", null);
                users = dept.getUsers();

                LinkedList<Object> userlist = new LinkedList<>();
                for (User user : users) {
                    Map<String, Object> userMap = new HashMap();
                    userMap.put("name", user.getName());
                    userMap.put("id", user.getUserid());
//               userMap.put("icon","/img/timg.jpg");
                    userlist.add(userMap);
                }
                System.out.println("userlist:" + userlist.toString());
                deptMap.put("children", userlist);
                deptList.add(deptMap);

            }
        }
        //System.out.println("deptList:"+deptList.toString());
        deptAndUserMap.put("children", deptList);
        deptAndUserList.add(deptAndUserMap);
        //System.out.println("deptAndUserList:"+deptAndUserList.toString());
        return  JsonResultUtil.successJson(deptAndUserList);
    }
}
