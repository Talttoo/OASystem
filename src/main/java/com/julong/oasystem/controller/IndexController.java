package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Taltoo
 * @Date 2020/8/26 0026 上午 10:38
 * @Description：
 */
@RestController
@RequestMapping("/home")
public class IndexController {

    @GetMapping("/getData")
    public JSONObject getHomeData(){
        return JsonResultUtil.successJson();
    }

}
