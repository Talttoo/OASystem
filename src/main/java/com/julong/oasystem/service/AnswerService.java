package com.julong.oasystem.service;



import com.julong.oasystem.entity.AnswerVO;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：问卷调查
 */
public interface AnswerService {
    /**
     * 查询所有的Answer,返回List<AnswerVO>
     *
     * @return
     */
    public List<AnswerVO> queryAnswer();

    /**
     * 根据试卷Id查询Answer,返回List<AnswerVO>
     *
     * @param paperId
     * @return
     */
    public List<AnswerVO> queryAnswerByPaperId(String paperId);

    /**
     * 根据QuestionId查询Answer,返回List<AnswerVO>
     *
     * @param questionId
     * @return
     */
    public List<AnswerVO> queryAnswerByQuestionId(String questionId);

    /**
     * 根据Id查询Answer,返回Answer对象
     *
     * @param id
     * @return
     */
    public AnswerVO queryAnswerById(String id);

    /**
     * 插入Answer,返回影响行数
     *
     * @param answerVO
     * @return
     */
    public boolean insertAnswer(AnswerVO answerVO);

    /**
     *  <P>插入一份试卷的所有答案 </p>
     *
     *  @param answerVO
     *  @return
     */
    public boolean insertAnswerList(List<AnswerVO> answerVO);

    /**
     * 更新Answer,返回影响行数
     *
     * @param answerVO
     * @return
     */
    public boolean updateAnswer(AnswerVO answerVO);

    /**
     * 删除Answer,返回影响行数
     *
     * @param id
     * @return
     */
    public boolean deleteAnswer(String id);

    /**
     * 查询Answer被答次数,返回次数
     *
     * @param paperId,questionId
     * @return
     */
    public int countAnswer(String paperId, String questionId);
}
