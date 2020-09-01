package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * ${comments}
 * 
 * @author
 * @date
 */

public interface WeChatConfigDao {

    int update(JSONObject jsonObject);

    int insert(JSONObject jsonObject);

    List<JSONObject> listByPage(JSONObject jsonObject);

    List<JSONObject> list();

    List<JSONObject> listAll();


    int delete(Long id);

    int count(JSONObject jsonObject);

    JSONObject getByPkOrg(JSONObject jsonObject);

    JSONObject getMessageParam(JSONObject jsonObject);
}
