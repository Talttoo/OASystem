package com.julong.oasystem.entity.view;

import java.util.ArrayList;

/**
 * description:
 * Author:
 * Date:
 */

//@Setter
//@Getter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class UpdatePaperViewPaper {
    private String id;
    private String title;
    private String startTime;
    private String endTime;
    private Integer status;
    private ArrayList<AddPaperViewQuestion> questions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ArrayList<AddPaperViewQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<AddPaperViewQuestion> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "UpdatePaperViewPaper{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", status=" + status +
                ", questions=" + questions +
                '}';
    }
}