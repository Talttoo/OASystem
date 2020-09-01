package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.CriticalValueVO;

import java.util.List;

public interface CriticalValueDao {

    int addCritical(JSONObject jsonObject);

    CriticalValueVO findCriticalValue(String itemCode);

    int updateCritical(JSONObject jsonObject);

    int deleteCritical(JSONObject jsonObject);

    List<JSONObject> listItem();

    List<CriticalValueVO> listCriticalValue();


}
