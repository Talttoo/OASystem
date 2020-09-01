package com.julong.oasystem.dao;


import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.wage.WageItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 17:12
 * @Description：
 */
public interface WageItemDao {
    /**
     * 查询所有的WageItem,返回List<WageItem>
     *
     * @return
     */
    public List<WageItemVO> queryWageItem();

    /**
     * 根据试卷的ID查询该试卷所有的WageItem
     *
     * @param wageId
     * @return
     */
    public List<WageItemVO> queryWageItemByWageId(String wageId);

    /**
     * 根据id查询WageItem
     *
     * @param id
     * @return
     */
    public WageItemVO queryWageItemById(String id);


    /**
     * 根据id查询WageItem
     *
     * @param userId username
     * @return
     */
    public  List<WageItemVO> queryWageItemByUser(String userId,String username);


    /**
     *
     * @param jsonObject
     * @return
     */
    public List<WageItemVO> queryWageItemBywageIdAndUser(JSONObject jsonObject);

    /**
     * 插入一个WageItem
     *
     * @param WageItem
     * @return
     */
    public int insertWageItem(WageItemVO WageItem);

    /**
     * 根据id更新该WageItem
     *
     * @param WageItem
     * @return
     */
    public int updateWageItem(WageItemVO WageItem);

    /**
     * 根据id删除该WageItem
     *
     * @param id
     * @return
     */
    public int deleteWageItem(String id);

    /**
     * 根据WageId删除WageItem
     *
     * @param id
     * @return
     */
    public int deleteWageItemsByWageId(String id);

    /**
     * 根据WageId和问题的类型获取WageItems
     *
     * @param id, WageItemType
     * @return
     */
    public List<WageItemVO> getWageItemsByWageIdAndWageItemType(@Param("id") String id, @Param("WageItemType") Integer WageItemType);
}
