package com.julong.oasystem.entity.wage;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:17
 * @Description：
 */
public class WageItemVO {

    private String id;//工资itemID
    private String wageId;//工资ID，外键
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;// 工资item创建时间
    private String wageEmployee;//员工ID
    private String wageEmployeeDept;//员工科室
    private String wageDetails;// 工资各个栏目详情

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWageId() {
        return wageId;
    }

    public void setWageId(String wageId) {
        this.wageId = wageId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getWageEmployee() {
        return wageEmployee;
    }

    public void setWageEmployee(String wageEmployee) {
        this.wageEmployee = wageEmployee;
    }

    public String getWageEmployeeDept() {
        return wageEmployeeDept;
    }

    public void setWageEmployeeDept(String wageEmployeeDept) {
        this.wageEmployeeDept = wageEmployeeDept;
    }

    public String getWageDetails() {
        return wageDetails;
    }

    public void setWageDetails(String wageDetails) {
        this.wageDetails = wageDetails;
    }

    @Override
    public String toString() {
        return "WageItemVO{" +
                "id='" + id + '\'' +
                ", wageId='" + wageId + '\'' +
                ", createTime=" + createTime +
                ", wageEmployee=" + wageEmployee +
                ", wageEmployeeDept='" + wageEmployeeDept + '\'' +
                ", wageDetails='" + wageDetails + '\'' +
                '}';
    }
}
