package com.julong.oasystem.service;


import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议室相关service
 */
public interface MeetingRoomService {
    JSONObject update(JSONObject jsonObject);

    JSONObject insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject selectList(JSONObject jsonObject);

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
    JSONObject booking(Long id);

    /**
     * @描述 根据房间名修改状态 房间名唯一
     * @date
     */
     int updateByRoomName(JSONObject jsonObject);
}