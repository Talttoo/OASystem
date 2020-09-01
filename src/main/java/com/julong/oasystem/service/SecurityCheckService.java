package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：安全检查service
 */
public interface SecurityCheckService {


    JSONObject insert(JSONObject jsonObject);

    JSONObject update(JSONObject jsonObject);

    JSONObject listByPage(JSONObject jsonObject);

    JSONObject delete(Long id);

//    JSONObject count(JSONObject jsonObject);

    JSONObject selectList(JSONObject jsonObject);

    JSONObject listByUser(JSONObject jsonObject);
}
