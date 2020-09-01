package com.julong.oasystem.service;


import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Taltoo
 * @Date 2020/7/1 0004 下午 14:52
 * @Description：远程调用连接微信接口
 */
@Service
@FeignClient("wechatservice")
@RequestMapping
public interface AccessWeChatService {

    @RequestMapping(value = "/wechat/getString", method = RequestMethod.GET)
    JSONObject getString();


    @RequestMapping(value = "/wechat/sendmessages", method = RequestMethod.POST)
    JSONObject sendmessages(@RequestBody JSONObject requestJson);


    @PostMapping("/wechat/sendRemindTextcard")
    public JSONObject sendRemindTextcard(@RequestBody JSONObject requestJson);


    @GetMapping("/wechat/getAccessToken")
    public String getAccessToken(@RequestParam("appid")String appid, @RequestParam("appSecret")String appSecret);


    @GetMapping("/wechat/getJSApiTicket")
    public String getJSApiTicket(@RequestParam("accessToken")String accessToken);


    @GetMapping("/wechat/alldept")
    public JSONObject getAllDep(@RequestParam("accessToken")String accessToken);


    @GetMapping("/wechat/alluser")
    public JSONObject getAllUser(@RequestParam("accessToken")String accessToken);


    @GetMapping("/wechat/getuserbycode")
    public JSONObject getAllUser(@RequestParam("accessToken")String accessToken,@RequestParam("code")String code);

}
