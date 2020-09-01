package com.julong.oasystem.entity.wage;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 15:13
 * @Description：
 */
public class InputWageVO {

    private String wageMonth;
    private List<String> wageColumn;
    private String createTime;
    private Integer status;
    private List<InputWageItemVO> wageItem;

    public String getWageMonth() {
        return wageMonth;
    }

    public void setWageMonth(String wageMonth) {
        this.wageMonth = wageMonth;
    }

    public List<String> getWageColumn() {
        return wageColumn;
    }

    public void setWageColumn(List<String> wageColumn) {
        this.wageColumn = wageColumn;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<InputWageItemVO> getWageItem() {
        return wageItem;
    }

    public void setWageItem(List<InputWageItemVO> wageItem) {
        this.wageItem = wageItem;
    }

    @Override
    public String toString() {
        return "InputWageVO{" +
                "wageMonth='" + wageMonth + '\'' +
                ", wageColumn=" + wageColumn +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                ", wageItem=" + wageItem +
                '}';
    }
}
