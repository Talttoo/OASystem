package com.julong.oasystem.entity;

import java.util.List;
//@Getter
//@Setter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class DepartmentVO {
    private  int id;
    private  String deptCode;
    private  String name;
    private  int parentid;
    private int status;
    private String introduce;
    private List<DepartmentVO> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<DepartmentVO> getChildren() {
        return children;
    }

    public void setChildren(List<DepartmentVO> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "DepartmentVO{" +
                "id=" + id +
                ", deptCode='" + deptCode + '\'' +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                ", status=" + status +
                ", introduce='" + introduce + '\'' +
                ", children=" + children +
                '}';
    }
}
