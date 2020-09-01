package com.julong.oasystem.service;


import com.julong.oasystem.entity.QuestionVO;

import java.util.List;

/**
 * description:
 * Author:
 * Date:
 */
public interface QuestionService {
    /**
     * 查询所有的Question,返回List<QuestionVO>
     *
     * @return
     */
    public List<QuestionVO> queryQuestion();

    /**
     * 根据试卷的ID查询该试卷所有的Question
     *
     * @param paperId
     * @return
     */
    public List<QuestionVO> queryQusetionByPaperId(String paperId);

    /**
     * 根据id查询Question
     *
     * @param id
     * @return
     */
    public QuestionVO queryQuestionById(String id);

    /**
     * 插入一个Question
     *
     * @param questionVO
     * @return
     */
    public boolean insertQuestion(QuestionVO questionVO);

    /**
     * 根据id更新该Question
     *
     * @param questionVO
     * @return
     */
    public boolean updateQuestion(QuestionVO questionVO);

    /**
     * 根据id删除该Question
     *
     * @param id
     * @return
     */
    public boolean deleteQuestion(String id);

    /**
     * 根据paperId删除question
     *
     * @param id
     * @return
     */
    public boolean deleteQuestionsByPaperId(String id);

    /**
     * 根据paperId和问题的类型获取questions
     *
     * @param id
     * @return
     */
    public List<QuestionVO> getQuestionsByPaperIdAndQuestionType(String id, Integer questionType);
}
