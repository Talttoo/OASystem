package com.julong.oasystem.entity.wage;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 15:14
 * @Description：
 */
public class InputWageItemVO {
    private String wageEmployee;
    private String wageEmployeeDept;
    private List<String> wageDetails;

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
        return "InputWageItemVO{" +
                "wageEmployee='" + wageEmployee + '\'' +
                ", wageEmployeeDept='" + wageEmployeeDept + '\'' +
                ", wageDetails=" + wageDetails +
                '}';
    }
}
