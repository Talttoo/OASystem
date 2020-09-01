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
     * 根据房间名修改状态 房间名唯一
     * @date
     */
    int updateByRoomName(JSONObject jsonObject);
}
