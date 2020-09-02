package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MeetDao {

    int update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    List<JSONObject> newListMeeting(JSONObject jsonObject);

    JSONObject selectMeetingById(String id);

    int delete(Long id);

    int count(JSONObject jsonObject);

    List<JSONObject> selectList(JSONObject jsonObject);


    JSONObject selectByRoomName (JSONObject jsonObject);

    /**
     * 根据用户id获取个人会议
     * @param uId
     * @return
     */
    List<JSONObject> selectMyMeetList(String uId);

}
