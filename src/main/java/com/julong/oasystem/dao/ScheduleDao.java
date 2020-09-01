package com.julong.oasystem.dao;


import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.CalendarScheduleVO;
import com.julong.oasystem.entity.ScheduleVO;

import java.util.List;

public interface ScheduleDao {

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKeys(Integer[] id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(ScheduleVO record);

    /**
     * 主键查询
     * @param id
     * @return
     */
    ScheduleVO selectByPrimaryKey(Integer id);


    /**
     * 首页数据查询
     * @param jsonObject
     * @return
     */
    List<CalendarScheduleVO> getCalendarSchedule(JSONObject jsonObject);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ScheduleVO record);

    /**
     * 列表
     * @param jsonObject
     * @return
     */
    List<ScheduleVO> selectScheduleList(JSONObject jsonObject);
}
