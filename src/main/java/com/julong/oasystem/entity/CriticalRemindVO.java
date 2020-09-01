package com.julong.oasystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CriticalRemindVO {
    private String repNo ;
    private String reqNo ;
    private String name ;
    private String pid ;
    private String sex ;
    private String deptCode ;
    private String deptName ;
    private String operNo ;
    private String roomCode ;
    private String reqDr ;
    private String operName ;
    private String reqDate ;
    private String repDate ;
    private String reader ;
    private String groupCode ;
    private String criticalNote;
}
