package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.wage.InputWageVO;
import com.julong.oasystem.entity.wage.WageVO;

import java.text.ParseException;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:03
 * @Description：
 */
public interface WageService {
    /**
     * 查询所有的WageVO,返回List<WageVO>
     *
     * @return
     */
    JSONObject queryWage(JSONObject jsonObject);

    /**
     * 查询某用户的所有WageVO，返回List<WageVO>
     *
     * @param userId
     * @return
     */
    List<WageVO> queryWageByUser(String userId,String username);

    /**
     * 根据WageVO对象的id查询WageVO，返回WageVO
     *
     * @param id
     * @return
     */
    WageVO queryWageByID(String id);

    /**
     * 插入一个WageVO对象到数据库中
     *
     * @param WageVO
     * @return
     */
    boolean insertWage(WageVO WageVO);

    /**
     * 更新WageVO，根据id更新
     *
     * @param WageVO
     * @return
     */
    boolean updateWage(WageVO WageVO);

    /**
     * 根据id删除WageVO对象
     *
     * @param id
     * @return
     */
    boolean deleteWage(String id);

    /**
     * 根据WageVO的title查询WageVO,注意：模糊查询
     *
     * @param title
     * @return
     */
    List<WageVO> queryWageByTitle(String title);


    /**
     * 根据id批量删除WageVO对象
     *
     * @param id
     * @return
     */
    boolean deleteManyWage(List<String> id);

    public Object dataWage(String id) throws ParseException;



   // boolean updateWageQuestions(UpdateWageVOViewWageVO WageVO, String userId, AddWageVOViewWageVO addWageVOViewWageVO) throws ParseException;
}
