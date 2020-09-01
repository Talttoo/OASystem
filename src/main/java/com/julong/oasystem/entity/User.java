package com.julong.oasystem.entity;


import java.util.Date;
//@Getter
//@Setter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class User {

    private String userid;

    private String name;

    private String password;


    private String gender;


    private Integer age;


    private String mobile;


    private String email;


    private Integer main_department;


    private String role_id;


    private String avatar;


    private Date create_time;

    private Integer status;

    private DepartmentVO deptPo;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getMain_department() {
        return main_department;
    }

    public void setMain_department(Integer main_department) {
        this.main_department = main_department;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DepartmentVO getDeptPo() {
        return deptPo;
    }

    public void setDeptPo(DepartmentVO deptPo) {
        this.deptPo = deptPo;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", main_department=" + main_department +
                ", role_id=" + role_id +
                ", avatar='" + avatar + '\'' +
                ", create_time=" + create_time +
                ", status=" + status +
                '}';
    }
}