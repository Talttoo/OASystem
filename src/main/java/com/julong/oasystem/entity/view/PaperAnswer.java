package com.julong.oasystem.entity.view;

import javax.validation.constraints.NotNull;
import java.util.List;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-09-20 Thursday 09:08
 */
//@Setter
//@Getter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class PaperAnswer {
    @NotNull(message = "问卷id不能为空")
    private String id;
    @NotNull(message = "答案列表不能为空")
    private List<QuestionAnswer> answers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<QuestionAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuestionAnswer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "PaperAnswer{" +
                "id='" + id + '\'' +
                ", answers=" + answers +
                '}';
    }
}
