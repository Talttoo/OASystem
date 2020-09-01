package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface ApplyRoomDao {

    int update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    int delete(Long id);

    int count(JSONObject jsonObject);

    List<JSONObject> selectList(JSONObject jsonObject);
}
