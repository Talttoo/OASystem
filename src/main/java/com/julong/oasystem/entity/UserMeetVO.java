package com.julong.oasystem.entity;

//@Getter
//@Setter
//@ToString
//@Accessors(chain = true)
//@NoArgsConstructor
//@AllArgsConstructor
public class UserMeetVO {
    private Integer id;

    private Integer meetId;

    private Object userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMeetId() {
        return meetId;
    }

    public void setMeetId(Integer meetId) {
        this.meetId = meetId;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserMeetVO{" +
                "id=" + id +
                ", meetId=" + meetId +
                ", userId=" + userId +
                '}';
    }
}