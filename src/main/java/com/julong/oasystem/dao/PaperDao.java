package com.julong.oasystem.dao;

import com.julong.oasystem.entity.PaperVO;

import java.util.List;

/**
 * description:
 * Author:
 * Date
 */
public interface PaperDao {
    /**
     * 查询所有的Paper,返回List<PaperVO>
     *
     * @return
     */
    List<PaperVO> queryPaper();

    /**
     *  <P>查询状态为已发布的问卷 </p>
     *  
     *  @param 
     *  @return 
     */
    List<PaperVO> queryPaperWithStatus1();


    /**
     * 查询某用户的所有Paper，返回List<PaperVO>
     *
     * @param userId
     * @return
     */
    List<PaperVO> queryPaperByUserID(String userId);

    /**
     * 根据Paper对象的id查询Paper，返回Paper
     *
     * @param id
     * @return
     */
    PaperVO queryPaperByID(String id);

    /**
     * 插入一个Paper对象到数据库中
     *
     * @param paperVO
     * @return
     */
    int insertPaper(PaperVO paperVO);

    /**
     * 更新Paper，根据id更新
     *
     * @param paperVO
     * @return
     */
    int updatePaper(PaperVO paperVO);

    /**
     * 根据id删除Paper对象
     *
     * @param id
     * @return
     */
    int deletePaper(String id);

    /**
     * 根据Paper的title查询Paper,注意：模糊查询
     *
     * @param title
     * @return
     */
    List<PaperVO> queryPaperByTitle(String title);
}
