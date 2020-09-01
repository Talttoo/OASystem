package com.julong.oasystem.service;


import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：微信配置参数service
 */
public interface WeChatConfigService {

    JSONObject update(JSONObject jsonObject);

    JSONObject insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject listAll(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject getByPkOrg(JSONObject jsonObject);

    JSONObject getMessageParam(JSONObject jsonObject);
}