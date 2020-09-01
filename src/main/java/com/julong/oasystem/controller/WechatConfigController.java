package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：微信配置参数
 */
@RestController
@RequestMapping("wechatConfig")
public class WechatConfigController {

    @Autowired
    private WeChatConfigService weChatConfigService;

    /**
     * 新增微信配置
     * @param requestJson
     * @return
     */
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {
        return weChatConfigService.insert(requestJson);
    }

    /**
     * 更新微信配置
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return weChatConfigService.update(requestJson);
    }

    /**
     * 删除微信配置
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return weChatConfigService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取微信参数列表
     * @param request
     * @return
     */
    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return weChatConfigService.list(jsonObject);
    }

    /**
     * 获取所有微信参数列表
     * @param request
     * @return
     */
    @GetMapping("/listAll")
    public JSONObject listAll(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return weChatConfigService.listAll(jsonObject);
    }
}