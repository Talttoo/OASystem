package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.wage.WageItemVO;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:03
 * @Description：
 */
public interface WageItemService {

    /**
     * 查询所有的WageItemVo,返回List<WageItemVo>
     *
     * @return
     */
    public List<WageItemVO> queryWageItem();

    /**
     * 根据试卷的ID查询该试卷所有的WageItem
     *
     * @param paperId
     * @return
     */
    public List<WageItemVO> queryWageItemBywageId(String paperId);

    /**
     *
     * @param jsonObject
     * @return
     */
    public List<WageItemVO> queryWageItemBywageIdAndUser(JSONObject jsonObject);

    /**
     * 根据id查询WageItem
     *
     * @param id
     * @return
     */
    public WageItemVO queryWageItemById(String id);

    /**
     *
     * @param jsonObject
     * @return
     */
    public JSONObject queryWageItemByUser(JSONObject jsonObject);

    /**
     * 插入一个WageItem
     *
     * @param WageItem
     * @return
     */
    public boolean insertWageItem(WageItemVO WageItem);

    /**
     * 根据id更新该WageItem
     *
     * @param WageItem
     * @return
     */
    public boolean updateWageItem(WageItemVO WageItem);

    /**
     * 根据id删除该WageItem
     *
     * @param id
     * @return
     */
    public boolean deleteWageItem(String id);

    /**
     * 根据paperId删除WageItem
     *
     * @param id
     * @return
     */
    public boolean deleteWageItemsByPaperId(String id);

    /**
     * 根据paperId和问题的类型获取WageItems
     *
     * @param id
     * @return
     */
    public List<WageItemVO> getWageItemsByPaperIdAndWageItemType(String id, Integer WageItemType);
}
