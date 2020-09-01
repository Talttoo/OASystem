package com.julong.oasystem.service.impl;


import com.julong.oasystem.dao.AnswerDao;
import com.julong.oasystem.entity.AnswerVO;
import com.julong.oasystem.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * description:
 * Author:
 * Date:
 */
@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerDao answerDao;

    @Override
    public List<AnswerVO> queryAnswer() {
        return answerDao.queryAnswer();
    }

    @Override
    public List<AnswerVO> queryAnswerByPaperId(String paperId) {
        return answerDao.queryAnswerByPaperId(paperId);
    }

    @Override
    public List<AnswerVO> queryAnswerByQuestionId(String questionId) {
        return answerDao.queryAnswerByQuestionId(questionId);
    }

    @Override
    public AnswerVO queryAnswerById(String id) {
        return answerDao.queryAnswerById(id);
    }

    @Transactional
    @Override
    public boolean insertAnswer(AnswerVO answerVO) {
        if (answerVO != null && !"".equals(answerVO.getId())) {
            try {
                int i = answerDao.insertAnswer(answerVO);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:插入答案失败！" + answerVO);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:插入答案失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:插入答案失败，Answer的id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean insertAnswerList(List<AnswerVO> answerVO) {
        for (AnswerVO ans : answerVO) {
            if (answerDao.insertAnswer(ans) != 1) {
                throw new RuntimeException("插入答案失败");
            }
        }
        return true;
    }

    @Transactional
    @Override
    public boolean updateAnswer(AnswerVO answerVO) {
        if (answerVO != null && !"".equals(answerVO.getId())) {
            try {
                int i = answerDao.updateAnswer(answerVO);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:更新答案失败！" + answerVO);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:更新答案失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:更新答案失败，Answer的id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteAnswer(String id) {
        if (id != null && !"".equals(id)) {
            try {
                int i = answerDao.deleteAnswer(id);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:删除答案失败！" + id);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:删除答案失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:删除答案失败，Answer的id不能为空！");
        }
    }

    @Override
    public int countAnswer(String paperId, String questionId) {
        if (paperId != null && !"".equals(paperId) && questionId != null && !"".equals(questionId))
            return answerDao.countAnswer(paperId, questionId);
        return 0;
    }

}
