package com.julong.oasystem.entity;



import java.util.Date;
import java.util.List;
//@Getter
//@Setter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class DeptAndUserVO {

    private Integer deptId;

    private Date createTime;

    private String deptName;

    private String parentid  ;

    private String introduce;

    private Integer status;

    private List<User> users;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "DeptAndUserVO{" +
                "deptId=" + deptId +
                ", createTime=" + createTime +
                ", deptName='" + deptName + '\'' +
                ", parentid='" + parentid + '\'' +
                ", introduce='" + introduce + '\'' +
                ", status=" + status +
                ", users=" + users +
                '}';
    }
}