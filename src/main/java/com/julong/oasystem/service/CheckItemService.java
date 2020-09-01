package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：检查项目
 */
public interface CheckItemService {

    JSONObject insert(JSONObject jsonObject);

    JSONObject update(JSONObject jsonObject);

    JSONObject listByPage(JSONObject jsonObject);

    JSONObject delete(Long id);

//    JSONObject count(JSONObject jsonObject);

    JSONObject selectList(JSONObject jsonObject);
}
