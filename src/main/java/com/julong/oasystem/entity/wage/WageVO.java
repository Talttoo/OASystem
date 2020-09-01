package com.julong.oasystem.entity.wage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:12
 * @Description：
 */
public class WageVO {
    private String id;//工资ID
    private String userId;//用户ID，外键
    private String wageMonth;//工资月份标题
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;//工资创建时间
    private Integer status;//状态值：0：未发布1：已发布2：已结束
    private String wageColumn;//工资栏目

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWageMonth() {
        return wageMonth;
    }

    public void setWageMonth(String wageMonth) {
        this.wageMonth = wageMonth;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWageColumn() {
        return wageColumn;
    }

    public void setWageColumn(String wageColumn) {
        this.wageColumn = wageColumn;
    }

    @Override
    public String toString() {
        return "WageVO{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", wageMonth='" + wageMonth + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", wageColumn=" + wageColumn +
                '}';
    }
}
