package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/20 0004 下午 14:52
 * @Description：危急值提醒
 */
public interface CriticalRemindService {

    JSONObject getList(JSONObject jsonObject);

    JSONObject getListByRepNo(String repNo);

    JSONObject getListByDate(String date);

    List<JSONObject> getNewInsert(String times);

    JSONObject insertRemindMessage(JSONObject jsonParam,JSONObject requestJson);

    JSONObject insertTextcardMessage(JSONObject jsonParam,JSONObject requestJson);

    JSONObject getMessageList(JSONObject jsonObject);

    int updateSend (String repNo);

}
