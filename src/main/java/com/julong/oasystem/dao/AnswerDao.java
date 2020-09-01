package com.julong.oasystem.dao;


import com.julong.oasystem.entity.AnswerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * Author:
 * Date:
 */
@Mapper
public interface AnswerDao {
    /**
     * 查询所有的Answer,返回List<AnswerVO>
     *
     * @return
     */
     List<AnswerVO> queryAnswer();

    /**
     * 根据试卷Id查询Answer,返回List<AnswerVO>
     *
     * @param paperId
     * @return
     */
     List<AnswerVO> queryAnswerByPaperId(String paperId);

    /**
     * 根据QuestionId查询Answer,返回List<AnswerVO>
     *
     * @param questionId
     * @return
     */
     List<AnswerVO> queryAnswerByQuestionId(String questionId);

    /**
     * 根据Id查询Answer,返回Answer对象
     *
     * @param id
     * @return
     */
     AnswerVO queryAnswerById(String id);

    /**
     * 插入Answer,返回影响行数
     *
     * @param answerVO
     * @return
     */
     int insertAnswer(AnswerVO answerVO);

    /**
     * 更新Answer,返回影响行数
     *
     * @param answerVO
     * @return
     */
     int updateAnswer(AnswerVO answerVO);

    /**
     * 删除Answer,返回影响行数
     *
     * @param id
     * @return
     */
     int deleteAnswer(String id);

    /**
     * 查询Answer被答次数,返回次数
     *
     * @param paperId,questionId
     * @return
     */
     int countAnswer(@Param("paperId") String paperId, @Param("questionId") String questionId);

}
