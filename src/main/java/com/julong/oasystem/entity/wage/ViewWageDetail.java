package com.julong.oasystem.entity.wage;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/24 0024 上午 9:09
 * @Description：
 */
public class ViewWageDetail {
    private String id;//工资itemID
    private String wageEmployee;
    private String wageEmployeeDept;
    private List<String> wageDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<String> getWageDetails() {
        return wageDetails;
    }

    public void setWageDetails(List<String> wageDetails) {
        this.wageDetails = wageDetails;
    }

    @Override
    public String toString() {
        return "ViewWageDetail{" +
                "id='" + id + '\'' +
                ", wageEmployee='" + wageEmployee + '\'' +
                ", wageEmployeeDept='" + wageEmployeeDept + '\'' +
                ", wageDetails=" + wageDetails +
                '}';
    }
}
