package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.MeetDao;
import com.julong.oasystem.dao.MeetingRoomDao;
import com.julong.oasystem.dao.UserMeetDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.UserMeetVO;
import com.julong.oasystem.service.MeetService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议相关service实现类
 */
@Service
public class MeetServiceImpl implements MeetService {

    @Autowired
    private MeetDao meetDao;

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    @Autowired
    private UserMeetDao userMeetDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        //System.out.println("jsonObject"+jsonObject.toJSONString());
        int res = meetDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject insert(JSONObject jsonObject){
        int res = meetDao.insert(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        System.out.println("jsonObject:"+jsonObject.toJSONString());
        JsonResultUtil.fillPageParam(jsonObject);
//        int count = meetDao.count(jsonObject);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = meetDao.listByPage(jsonObject);
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    /**
     * 获取首页会议列表
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject newListMeeting(JSONObject jsonObject) {
        List<JSONObject> list = meetDao.newListMeeting(jsonObject);
        return JsonResultUtil.successJson(list);
    }

    /**
     * 根据id获取会议详情
     * @param id
     * @return
     */
    @Override
    public JSONObject selectMeetingById(String id) {
        JSONObject jsonObject =  meetDao.selectMeetingById(id);
        return  JsonResultUtil.successJson(jsonObject);
    }

    @Override
    public JSONObject delete (Long id){
        int res = meetDao.delete(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject selectList(JSONObject jsonObject) {
        return JsonResultUtil.successJson(meetDao.selectList(jsonObject));
    }

    /**
     *
     * @描述 通过房间名称查询
     *
     * @date
     */
    @Override
    public JSONObject selectByRoomName(JSONObject jsonObject)
    {
        return meetingRoomDao.selectByRoomName(jsonObject);
      //  return  jsonObject;
    }

    /**
     *
     * @描述: 添加
     *
     * @params:
     * @return:
     * @date:
     */
    @Override
    public int insertSelective(JSONObject jsonObject, String[] userIds)
    {
        int i = meetDao.insert(jsonObject);

        Integer meetId = jsonObject.getIntValue("id");

       int j = insertSelective(meetId,userIds);

        return (i>0&&j>0)? 1:0;
    }

    @Override
    public int updateByPrimaryKeySelective(JSONObject jsonObject, String[] userIds) {
        return 0;
    }


    public  int insertSelective(Integer meetId, String[] userIds) {

        int res = 0;
        if (userIds!=null && userIds.length > 0)
        {
            for (Object uId : userIds)
            {
                UserMeetVO userMeetVO = new UserMeetVO();
                userMeetVO.setMeetId(meetId);
                userMeetVO.setUserId(uId);
                System.out.println("+++++userMeetVO:"+ userMeetVO.toString());
                if(uId!=null) {
                    res = userMeetDao.insertSelective(userMeetVO);
                }
               //list.add(userMeetVO);
                if (res<0) return res;

            }
        }
        return res;
    }

    public static List<UserMeetVO> getListUserMeet(Integer meetId, String[] userIds) {
        List<UserMeetVO> list = new ArrayList<>();
        if (userIds!=null && userIds.length > 0)
        {
            for (Object uId : userIds)
            {
                UserMeetVO userMeetVO = new UserMeetVO();
                userMeetVO.setMeetId(meetId);
                userMeetVO.setUserId(uId);
                list.add(userMeetVO);
            }
        }
        return list;
    }
}
