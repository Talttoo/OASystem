package com.julong.oasystem.dao;

import com.julong.oasystem.entity.UserMeetVO;

public interface UserMeetDao {

    int deleteByMeetIdKeys(Integer[] id);


    int insertSelective(UserMeetVO userMeetVO);



}