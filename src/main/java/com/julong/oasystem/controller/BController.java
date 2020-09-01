package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.CriticalRemindService;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.wechat.WeChatUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
public class BController {


    @Autowired
    RestTemplate restTemplate;


    @Autowired
    CriticalRemindService criticalRemindService;

    @Autowired
    WeChatConfigService weChatConfigService;


    @Value("${server.port}")
    String port;

    @GetMapping("/xiaofei")
    @HystrixCommand(fallbackMethod = "orderToUserInfoFallback")
    public String findUserById() {
//        return restTemplate.getForObject("http://servicehi/getsring", String.class);

        System.out.println(port);
        System.out.println(LocalDateTime.now().toString());
        return LocalDateTime.now().toString() + "  " + restTemplate.getForEntity("http://servicehi/getsring", String.class).getBody();
    }


    public String orderToUserInfoFallback() {

        return "服务降级.";
    }


    @Autowired
    DiscoveryClient discoveryclient;


    @GetMapping("/discovery")
    public List<String> findUserById2() {
        List<String> services = discoveryclient.getServices();

        return services;
    }

    @PostMapping("/testsendmessage")
    public JSONObject sendRemindMessage(@RequestBody JSONObject requestJson){
        System.out.println("收到请求++++++++");
        JSONObject jsonParam = weChatConfigService.getMessageParam(new JSONObject());
        System.out.println("jsonParam++++++++"+jsonParam.toJSONString());
        if(jsonParam.containsKey("appid")) {

             JSONObject result = WeChatUtil.sendRemindMessage(jsonParam, requestJson);
            System.out.println("result:"+result.toJSONString());

            int  errcode =  result.getIntValue("errcode");

            if(errcode==0){
                //记录发送消息
                JSONObject res = criticalRemindService.insertRemindMessage(jsonParam, requestJson);
                return JsonResultUtil.successJson();
            }else {
                return JsonResultUtil.errorJson(400,"发送提醒消息失败！");
            }

        }



        return JsonResultUtil.errorJson(400,"发送提醒消息失败！");

    }




}


 /*
    export const GETHOS =params=>req('get','/dkyy/area/getzmenzhendata',params)
    export const MZSR =params=>req('post','/dkyy/area/getmenzhenshouru',params)
    export const ZYSR =params=>req('post','/dkyy/area/getzhushouru',params)

   */