package com.julong.oasystem.service.impl;


import com.julong.oasystem.dao.QuestionDao;
import com.julong.oasystem.entity.QuestionVO;
import com.julong.oasystem.service.QuestionService;
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
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Override
    public List<QuestionVO> queryQuestion() {
        return questionDao.queryQuestion();
    }

    @Override
    public List<QuestionVO> queryQusetionByPaperId(String paperId) {
        return questionDao.queryQusetionByPaperId(paperId);
    }

    @Override
    public QuestionVO queryQuestionById(String id) {
        return questionDao.queryQuestionById(id);
    }

    @Transactional
    @Override
    public boolean insertQuestion(QuestionVO questionVO) {
        if (questionVO != null && !"".equals(questionVO.getId())) {
            try {
                int i = questionDao.insertQuestion(questionVO);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:插入问题失败！" + questionVO);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:插入问题失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:插入问题失败，问题id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean updateQuestion(QuestionVO questionVO) {
        if (questionVO != null && !"".equals(questionVO.getId())) {
            try {
                int i = questionDao.updateQuestion(questionVO);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:更新问题失败！" + questionVO);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:更新问题失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:更新问题失败，问题id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteQuestion(String id) {
        if (id != null && !"".equals(id)) {
            try {
                int i = questionDao.deleteQuestion(id);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:删除问题失败！" + id);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:删除问题失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:删除问题失败，问题id不能为空！");
        }
    }

    @Transactional
    @Override
    public boolean deleteQuestionsByPaperId(String id) {
        if (id != null && !"".equals(id)) {
            try {
                int i = questionDao.deleteQuestionsByPaperId(id);
                if (i >= 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:删除问题失败！" + id);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:删除问题失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:删除问题失败，试卷id不能为空！");
        }
    }

    @Override
    public List<QuestionVO> getQuestionsByPaperIdAndQuestionType(String id, Integer questionType) {

        if (id != null && !"".equals(id) && (questionType == 1 || questionType == 2 || questionType == 3)) {
            //System.out.println("id:" + id + "questionType" + questionType);
            return questionDao.getQuestionsByPaperIdAndQuestionType(id, questionType);
        }
        throw new RuntimeException("a:根据PaperId和问题类型获取问题失败！" + "ID:" + id + "   questionType:" + questionType);
    }
}
