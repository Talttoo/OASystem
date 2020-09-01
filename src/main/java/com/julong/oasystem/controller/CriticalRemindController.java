package com.julong.oasystem.controller;


import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.AccessWeChatService;
import com.julong.oasystem.service.CriticalRemindService;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author Taltoo
 * @Date 2020/6/20 0004 下午 14:52
 * @Description：危急值提醒
 */
@Slf4j
@RestController
@RequestMapping("/critical-remind")
public class CriticalRemindController {

    @Autowired
    CriticalRemindService criticalRemindService;

    @Autowired
    WeChatConfigService weChatConfigService;

    @Autowired
    AccessWeChatService accessWeChatService;

    /**
     * 测试远程调用
     * @param request
     * @return
     */
    @GetMapping("/getString")
    public JSONObject getString(HttpServletRequest request) {

        return  accessWeChatService.getString();
    }


    /**
     * 获取危急值提醒列表数据
     * @param request
     * @return
     */
    @GetMapping("/getreminds")
    public JSONObject getReminds(HttpServletRequest request) {
        System.out.println("收到请求.....");

        //log.info(date);
        JSONObject result = criticalRemindService.getList(JsonResultUtil.request2Json(request));

        System.out.println("result:"+result.toJSONString());



        return result;
    }


    /**
     *  根据RepNo获取危急值提醒列表数据
     * @param id
     * @return
     */
    @GetMapping("/remindsbyid")
    public JSONObject getRemindByRepNo(@RequestParam("id") String id) {
        System.out.println("收到请求.....repNo:"+id);

        //log.info(date);
        JSONObject result = criticalRemindService.getListByRepNo(id);

        System.out.println("getremindsbyid--result:"+result.toJSONString());

        return result;
    }


    /**
     * 根据日期获取危急值提醒信息
     * @param date
     * @return
     */
    @PostMapping("/getremindsbydate")
    public JSONObject getRemindsByDate(@RequestParam("date") String date) {
        System.out.println("收到请求.....");

        //log.info(date);
        JSONObject result = criticalRemindService.getListByDate(date);

        System.out.println("result:"+result.toJSONString());

        return result;
    }

    /**
     * 获取危急值提醒参数
     * @param date
     * @return
     */
    @PostMapping("/getparam")
    public JSONObject getRemindsParam(@RequestParam("date") String date) {

        return JsonResultUtil.successJson();
    }


    /**
     * 手动发送危急值提醒给医生
     * @param requestJson
     * @return
     */
    @PostMapping("/sendmessage")
    public JSONObject sendRemindMessage(@RequestBody JSONObject requestJson){

        JSONObject jsonParam = weChatConfigService.getMessageParam(new JSONObject());
        //
        if(jsonParam.containsKey("appid")) {
//            JSONObject result = WeChatUtil.sendRemindMessage(jsonParam, requestJson);

            requestJson.put("appid",jsonParam.getString("appid"));
            requestJson.put("appSecret",jsonParam.getString("appSecret"));
            requestJson.put("agentid",jsonParam.getString("agentid"));

            System.out.println("requestJson:"+requestJson.toJSONString());

            JSONObject result = accessWeChatService.sendmessages(requestJson);

            System.out.println("result:"+result.toJSONString());

            int  errcode =  result.getIntValue("errcode");

            if(errcode==0){
                //记录发送消息
                JSONObject res = criticalRemindService.insertRemindMessage(jsonParam, requestJson);
                return JsonResultUtil.successJson();
            }else {
                return JsonResultUtil.errorJson(400,"发送提醒消息失败！");
            }

        }else {
            log.error("获取微信参数失败，无法发送提醒消息");
            return JsonResultUtil.errorJson(400,"发送提醒消息失败！");
        }




    }


    /**
     * 获取已发提醒消息列表数据
     * @param request
     * @return
     */
    @GetMapping("/getmessages")
    public JSONObject getMessages(HttpServletRequest request) {
        System.out.println("收到message请求.....");

        //log.info(date);
        JSONObject result = criticalRemindService.getMessageList(JsonResultUtil.request2Json(request));

        System.out.println("controller-message:"+result.toJSONString());

        return result;
    }


}
