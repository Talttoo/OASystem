package com.julong.oasystem.entity;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @Author Taltoo
 * @Date 2020/8/28 0028 上午 9:18
 * @Description：
 */
public class CalendarScheduleVO {

    private String  sId;
    private String createBy;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private String title;
    private String descr;
    private String status;
    private String isComplete;

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "CalendarScheduleVO{" +
                "sId='" + sId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createTime='" + createTime + '\'' +
                ", title='" + title + '\'' +
                ", descr='" + descr + '\'' +
                ", status='" + status + '\'' +
                ", isComplete='" + isComplete + '\'' +
                '}';
    }
}
