package com.julong.oasystem.dao;

import com.julong.oasystem.entity.wage.WageVO;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:30
 * @Description：
 */
public interface WageDao {
    /**
     * 查询所有的Wage,返回List<Wage>
     *
     * @return
     */
    List<WageVO> queryWage();

    /**
     *  <P>查询状态为已发布的问卷 </p>
     *
     *  @param
     *  @return
     */
    List<WageVO> queryWageWithStatus1();


    /**
     * 查询某用户的所有Wage，返回List<Wage>
     *
     * @param userId
     * @return
     */
    List<WageVO> queryWageByUser(String userId, String username);

    /**
     * 根据Wage对象的id查询Wage，返回Wage
     *
     * @param id
     * @return
     */
    WageVO queryWageByID(String id);

    /**
     * 插入一个Wage对象到数据库中
     *
     * @param Wage
     * @return
     */
    int insertWage(WageVO Wage);

    /**
     * 更新Wage，根据id更新
     *
     * @param Wage
     * @return
     */
    int updateWage(WageVO Wage);

    /**
     * 根据id删除Wage对象
     *
     * @param id
     * @return
     */
    int deleteWage(String id);

    /**
     * 根据Wage的title查询Wage,注意：模糊查询
     *
     * @param title
     * @return
     */
    List<WageVO> queryWageByTitle(String title);
}
