package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.CheckItemService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：检查项目
 */
@RestController
@RequestMapping("/checkitem")
public class CheckItemController {

    @Autowired
    private CheckItemService checkItemService;

    /**
     * 新增检查项目
     * @param requestJson
     * @return
     */
    @PostMapping("/create")
    public JSONObject insert(@RequestBody JSONObject requestJson) {

        System.out.println("requestJson:"+requestJson.toJSONString());

        return checkItemService.insert(requestJson);

    }

    /**
     * 更新检查项目
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return checkItemService.update(requestJson);
    }

    /**
     * 删除检查项目
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        System.out.println("itemcode:"+Long.valueOf(request.getParameter("itemCode")));
        return checkItemService.delete(Long.valueOf(request.getParameter("itemCode")));
    }

    /**
     * 获取检查项目列表数据
     * @param request
     * @return
     */
    @GetMapping("/getlist")
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return checkItemService.listByPage(jsonObject);
    }

    /**
     * 获取检查项目列表数据,用于下拉列表框
     * @param request
     * @return
     */
    @GetMapping("selectList")
    public JSONObject selectList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return checkItemService.selectList(jsonObject);
    }

}
