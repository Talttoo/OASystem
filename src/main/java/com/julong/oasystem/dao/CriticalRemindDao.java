package com.julong.oasystem.dao;


import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.CriticalMessageVO;
import com.julong.oasystem.entity.CriticalRemindVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lyt
 * @since 2020-06-24
 */
public interface CriticalRemindDao {

    List<JSONObject> getList();

    CriticalRemindVO getListByRepNo(String repNo);

    List<CriticalRemindVO> getListByDate(String date);

    List<JSONObject> getNewInsert(String times);

    int insertRemindMessage(JSONObject jsonObject);

    List<CriticalMessageVO> getMessageList();

    int updateSend (String repNo);

}
