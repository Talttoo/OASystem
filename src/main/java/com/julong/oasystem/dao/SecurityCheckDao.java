package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecurityCheckDao {

    int insert(JSONObject jsonObject);

    int update(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    int delete(Long id);

    int count(JSONObject jsonObject);

    List<JSONObject> selectList(JSONObject jsonObject);

    List<JSONObject> listByUser(JSONObject jsonObject);
}
