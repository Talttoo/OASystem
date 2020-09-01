package com.julong.oasystem.entity.view;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.QuestionVO;
import com.julong.oasystem.service.PaperService;
import com.julong.oasystem.service.QuestionService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * Author:
 * Date:
 */
@Service
public class PaperMethodHelp {


    @Autowired
    private QuestionService questionService;

    @Autowired @Lazy
    private PaperService paperService;

    @Autowired
    private CommonUtils commonUtils;

    public JSONObject insertPaper(AddPaperViewPaper paper, String userId, String id) throws ParseException {

        if (checkPaper(paper) == null) {
            //放入插入代码
            //id = null;
            //System.out.println(id);
            return insert(paper, userId, id);
        } else {
            return checkPaper(paper);
        }
    }

    @Transactional
    public JSONObject insert(AddPaperViewPaper paper, String userId, String id) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        //HttpSession session = (HttpSession) request.getAttribute("session");
        //System.out.println(id);
        if (userId != null) {
            //通过检测，准备添加数据
            //System.out.println("准备添加数据");
            QuestionVO questionVO = new QuestionVO();
            PaperVO paperVO1 = new PaperVO();
            //String paper1Id = commonUtils.getUUID();
            if (id == null) {
                paperVO1.setId(commonUtils.getUUID());
                //System.err.println("id==null时：id"+paperVO1.getId());
            } else {
                paperVO1.setId(id);
                //System.err.println("id！=null时：id"+paperVO1.getId());
            }

            paperVO1.setUserId(userId);
            paperVO1.setCreateTime(new Date());
            paperVO1.setStartTime(commonUtils.getDateByDateString(paper.getStartTime()));
            paperVO1.setEndTime(commonUtils.getDateByDateString(paper.getEndTime()));
            paperVO1.setStatus(paper.getStatus());
            paperVO1.setTitle(paper.getTitle());
            //吧paper1存入数据库
            paperService.insertPaper(paperVO1);


            //paper添加完成，接下来添加Questions
            for (AddPaperViewQuestion a : paper.getQuestions()) {
                questionVO.setId(commonUtils.getUUID());
                questionVO.setPaperId(paperVO1.getId());
                questionVO.setCreateTime(new Date());
                questionVO.setQuestionType(a.getQuestionType());
                questionVO.setQuestionTitle(a.getQuestionTitle());
               // questionVO.setQuestionOption(String.valueOf(a.getQuestionOption()));
                questionVO.setQuestionOption(JSONArray.parseArray(JSON.toJSONString(a.getQuestionOption())).toJSONString());

                //System.out.println("options:" + questionVO.getQuestionOption());

                //把question存入数据库
                questionService.insertQuestion(questionVO);
            }

//            map.put("code", 0);
//            map.put("msg", "ok");
//
            return JsonResultUtil.successJson();

        } else {
//            map.put("code", -1);
//            map.put("msg", "not token");
//            return map;
            return JsonResultUtil.errorJson(-1,"not token");
        }
    }

    public JSONObject checkPaper(AddPaperViewPaper paper) {
        if (paper.getStatus() == 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        if (paper.getTitle() == null || "".equals(paper.getTitle())) {
//            map.put("code", 2);
//            map.put("msg", "试卷题目 不能为空");
//            return map;
            return JsonResultUtil.errorJson(2,"试卷题目 不能为空");
        } else if (paper.getTitle().length() < 2 || paper.getTitle().length() > 64) {
//            map.put("code", 2);
//            map.put("msg", "试卷题目 应该在2个字符到64个字符之间");
//            return map;
            return JsonResultUtil.errorJson(2,"试卷题目 应该在2个字符到64个字符之间");
        } else if (paper.getStatus() != 0 && paper.getStatus() != 1) {
//            map.put("code", 2);
//            map.put("msg", "试卷状态 应该是0或者是1");
//            return map;
            return JsonResultUtil.errorJson(2,"试卷状态 应该是0或者是1");
        } else if (paper.getQuestions().size() <= 0) {
//            map.put("code", 2);
//            map.put("msg", "该试卷没有添加问题");
//            return map;
            return JsonResultUtil.errorJson(2,"该试卷没有添加问题");
        } else {
            for (AddPaperViewQuestion a : paper.getQuestions()) {
                if (a.getQuestionTitle().length() <= 1 || a.getQuestionTitle().length() >= 128) {
//                    map.put("code", 2);
//                    map.put("msg", "问题名为" + a.getQuestionTitle() + "的题目 应该是1到128个字符");
//                    return map;
                    return JsonResultUtil.errorJson(2, "问题名为" + a.getQuestionTitle() + "的题目 应该是1到128个字符");
                } else if (a.getQuestionType() != 1 && a.getQuestionType() != 2 && a.getQuestionType() != 3) {
//                    map.put("code", 2);
//                    map.put("msg", "问题名为" + a.getQuestionTitle() + "的问题类型 应该是1，2，3");
//                    return map;
                    return JsonResultUtil.errorJson(2, "问题名为" + a.getQuestionTitle() + "的问题类型 应该是1，2，3");
                } else if ((a.getQuestionType() == 1 || a.getQuestionType() == 2) && a.getQuestionOption().size() < 2) {
//                    map.put("code", 2);
//                    map.put("msg", "问题名为" + a.getQuestionTitle() + "的问题类型不满足");
//                    return map;
                    return JsonResultUtil.errorJson(2, "问题名为" + a.getQuestionTitle() + "的问题类型不满足");
                } else if (a.getQuestionType() == 3 && a.getQuestionOption().size() > 0) {
//                    map.put("code", 2);
//                    map.put("msg", "问题名为" + a.getQuestionTitle() + "的问题的答案应该为空");
//                    return map;
                    return JsonResultUtil.errorJson(2, "问题名为" + a.getQuestionTitle() + "的问题的答案应该为空");
                }
            }
        }
        return null;
    }


}