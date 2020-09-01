package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.ScheduleVO;

/**
 * @author 永健
 */
public interface ScheduleService {
    int deleteByPrimaryKeys(Integer[] id);

    int insertSelective(ScheduleVO record, String[] userIds);

    JSONObject selectByPrimaryKey(Integer id);

    JSONObject getCalendarSchedule(JSONObject jsonObject);

    int updateByPrimaryKeySelective(ScheduleVO record, String[] userIds);

    JSONObject selectScheduleList(JSONObject jsonObject);

    int updateComplete(ScheduleVO scheduleVO);
}
