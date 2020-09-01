package com.julong.oasystem.entity;

public class CriticalMessageVO {
    private String id;
    private String touser;
    private String username;
    private String deptname;
    private String sender;
    private String sendername;
    private String toparty;
    private String content;
    private String create_time;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSendername() {
        return sendername;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
    }

    public String getToparty() {
        return toparty;
    }

    public void setToparty(String toparty) {
        this.toparty = toparty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CriticalMessageVO{" +
                "id='" + id + '\'' +
                ", touser='" + touser + '\'' +
                ", username='" + username + '\'' +
                ", deptname='" + deptname + '\'' +
                ", sender='" + sender + '\'' +
                ", sendername='" + sendername + '\'' +
                ", toparty='" + toparty + '\'' +
                ", content='" + content + '\'' +
                ", create_time='" + create_time + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
