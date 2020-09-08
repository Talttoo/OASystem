package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/9/8 0008 上午 9:48
 * @Description：
 */
public interface MessageNoticesDao {

    int insert(JSONObject jsonObject);

    int updateById(String id);

    int updateByType(int type);

    /**
     * 不删除记录，更新status
     * @param id
     * @return
     */
    int removeById(String  id);

    /**
     * 删除记录
     * @param id
     * @return
     */
    int deleteById(String  id);

    /**
     * 统计总的未读消息数
     * @return
     */
    int count();

    /**
     * 统计某一类型的未读消息数
     * @param type
     * @return
     */
    int countByType(int type);

    /**
     * 分别统计各类型的未读消息数
     * @return
     */
    int countByAllType();


    /**
     * 查询某一类型的未读消息
     * @param type
     * @return
     */
    JSONObject listByType(int type);

    /**
     * 分别查询所有类型的未读消息和未读数量
     * @return
     */
    JSONObject latestNewsByTypeAndCount();

    /**
     * 分别查询所有类型的未读消息
     * @return
     */
    JSONObject latestNewsByType();

}
