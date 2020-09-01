package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.ScheduleUser;

import java.util.List;

public interface ScheduleUserDao {
    int deleteByPrimaryKeys(Integer[] id);


    int insertSelective(ScheduleUser scheduleUser);

}