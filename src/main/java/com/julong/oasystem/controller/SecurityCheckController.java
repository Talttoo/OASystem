package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.SecurityCheckService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：安全检查相关
 */
@RestController
@RequestMapping("/securitycheck")
public class SecurityCheckController {

    @Autowired
    private SecurityCheckService securityCheckService;

    /**
     * 新增安全检查
     * @param requestJson
     * @return
     */
    @PostMapping("/create")
    public JSONObject insert(@RequestBody JSONObject requestJson) {

        System.out.println("requestJson:"+requestJson.toJSONString());

        return securityCheckService.insert(requestJson);

    }

    /**
     * 更新安全检查
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return securityCheckService.update(requestJson);
    }

    /**
     * 删除安全检查
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        System.out.println("itemcode:"+Long.valueOf(request.getParameter("id")));
        return securityCheckService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取安全检查列表数据
     * @param request
     * @return
     */
    @GetMapping("/getlist")
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return securityCheckService.listByPage(jsonObject);
    }

    /**
     * 根据用户id 获取安全检查列表数据
     * @param request
     * @return
     */
    @GetMapping("getListByUser")
    public JSONObject selectList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return securityCheckService.listByUser(jsonObject);
    }
}
