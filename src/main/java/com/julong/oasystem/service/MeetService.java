package com.julong.oasystem.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议相关service
 */
public interface MeetService {

    JSONObject update(JSONObject jsonObject);

    int insertSelective(JSONObject jsonObject, String[] userIds);

    int updateByPrimaryKeySelective(JSONObject jsonObject,String[] userIds);

    JSONObject insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject newListMeeting(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject selectList(JSONObject jsonObject);

    JSONObject selectByRoomName(JSONObject jsonObject);
}
