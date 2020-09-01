package com.julong.oasystem.dao;

import com.julong.oasystem.entity.QuestionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * Author:
 * Date:
 */
public interface QuestionDao {
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
    public int insertQuestion(QuestionVO questionVO);

    /**
     * 根据id更新该Question
     *
     * @param questionVO
     * @return
     */
    public int updateQuestion(QuestionVO questionVO);

    /**
     * 根据id删除该Question
     *
     * @param id
     * @return
     */
    public int deleteQuestion(String id);

    /**
     * 根据paperId删除question
     *
     * @param id
     * @return
     */
    public int deleteQuestionsByPaperId(String id);

    /**
     * 根据paperId和问题的类型获取questions
     *
     * @param id, questionType
     * @return
     */
    public List<QuestionVO> getQuestionsByPaperIdAndQuestionType(@Param("id") String id, @Param("questionType") Integer questionType);
}
