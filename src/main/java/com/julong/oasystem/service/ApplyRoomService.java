package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/6/1 0004 下午 14:52
 * @Description：申请会议室
 */
public interface ApplyRoomService {

    JSONObject update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject approve(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject selectList(JSONObject jsonObject);
}
