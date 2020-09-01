package com.julong.oasystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.CriticalValueService;
import com.julong.oasystem.utils.JsonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Taltoo
 * @Date 2020/6/21 0004 下午 15:02
 * @Description：危急值极限值定义
 */
@Slf4j
@RestController
@RequestMapping("/critical-value")
public class CriticalValueController {

    @Autowired
    CriticalValueService criticalValueService;


    /**
     * 新增危急值极限值定义
     * @param jsonObject
     * @return
     */
    @PostMapping("/add")
    public JSONObject addCritical(@RequestBody JSONObject jsonObject) {
        return criticalValueService.addCritical(jsonObject);

    }

    /**
     * 更新危急值极限值定义
     * @param jsonObject
     * @return
     */
    @PostMapping("/update")
    public JSONObject updateCritical(@RequestBody JSONObject jsonObject) {
        return criticalValueService.updateCritical(jsonObject);
    }


    /**
     * 删除危急值极限值定义
     * @param jsonObject
     * @return
     */
    @PostMapping("/delete")
    public JSONObject deleteCritical(@RequestBody JSONObject jsonObject) {
        System.out.println("delete:"+jsonObject.toJSONString());
        return criticalValueService.deleteCritical(jsonObject);
    }


    /**
     * 获取危急值极限定义，用于下拉列表框
     * @return
     */
    @GetMapping("/listitem")
    public JSONObject listItem() {
        return criticalValueService.listItem();
    }

    /**
     * 获取危急值极限值定义列表数据
     * @param request
     * @return
     */
    @GetMapping("/listcritical")
    public JSONObject listCriticalValue(HttpServletRequest request) {
        return criticalValueService.listCriticalValue(JsonResultUtil.request2Json(request));
    }


}
