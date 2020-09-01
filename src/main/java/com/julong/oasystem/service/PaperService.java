package com.julong.oasystem.service;


import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.view.AddPaperViewPaper;
import com.julong.oasystem.entity.view.UpdatePaperViewPaper;

import java.text.ParseException;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/20 0004 下午 14:52
 * @Description：问卷调查相关
 */
public interface PaperService {
    /**
     * 查询所有的Paper,返回List<PaperVO>
     *
     * @return
     */
    List<PaperVO> queryPaper();

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
    boolean insertPaper(PaperVO paperVO);

    /**
     * 更新Paper，根据id更新
     *
     * @param paperVO
     * @return
     */
    boolean updatePaper(PaperVO paperVO);

    /**
     * 根据id删除Paper对象
     *
     * @param id
     * @return
     */
    boolean deletePaper(String id);

    /**
     * 根据Paper的title查询Paper,注意：模糊查询
     *
     * @param title
     * @return
     */
    List<PaperVO> queryPaperByTitle(String title);


    /**
     * 根据id批量删除Paper对象
     *
     * @param id
     * @return
     */
    boolean deleteManyPaper(List<String> id);

    public Object dataPaper(String id) throws ParseException;


    boolean updatePaperQuestions(UpdatePaperViewPaper paper, String userId, AddPaperViewPaper addPaperViewPaper) throws ParseException;
}
