package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MeetingRoomDao {

    int update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    int delete(Long id);

    int count(JSONObject jsonObject);

    List<JSONObject> selectList(JSONObject jsonObject);

    /**
     * 房间名查询
     * @date
     */
    JSONObject selectByRoomName(JSONObject jsonObject);


    /**
     * 预约申请
     * @param id
     * @return
     */
    int booking(Long id);

    /**
     * 审核通过，房间使用中
     * @param roomName
     * @return
     */
    int  usingRoom(String  roomName);

    /**
     * 会议结束，房间恢复空闲
     * @param roomName
     * @return
     */
    int finish(String  roomName);

    /**
     * 根据房间名修改状态 房间名唯一
     * @date
     */
    int updateByRoomName(JSONObject jsonObject);
}
