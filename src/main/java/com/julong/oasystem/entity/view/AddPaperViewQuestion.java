package com.julong.oasystem.entity.view;

import java.util.List;

/**
 * description:添加Paper所定义的中间Question类
 * Author:
 * Date:
 */
//@Getter
//@Setter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class AddPaperViewQuestion {

    private Integer questionType;
    private String questionTitle;
    private List<String> questionOption;

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public List<String> getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(List<String> questionOption) {
        this.questionOption = questionOption;
    }

    @Override
    public String toString() {
        return "AddPaperViewQuestion{" +
                "questionType=" + questionType +
                ", questionTitle='" + questionTitle + '\'' +
                ", questionOption=" + questionOption +
                '}';
    }
}
