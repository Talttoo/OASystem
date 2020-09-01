package com.julong.oasystem.entity.view;

import com.alibaba.fastjson.JSONArray;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/*
 * Author:
 * Email :
 *
 */
//@Setter
//@Getter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class QuestionAnswer{
    @NotNull(message = "问题id不能为空")
    private String id;
    @Range(min = 1, max = 3, message = "问题类型错误")
    private Integer questionType;
    @NotNull(message = "答案不能为空")
    private JSONArray answerContent;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public JSONArray getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(JSONArray answerContent) {
        this.answerContent = answerContent;
    }

    @Override
    public String toString() {
        return "QuestionAnswer{" +
                "id='" + id + '\'' +
                ", questionType=" + questionType +
                ", answerContent=" + answerContent +
                '}';
    }
}
