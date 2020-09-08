package com.julong.oasystem.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.MeetingRoomDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.MeetingRoomService;

import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议室相关service实现类
 */
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        System.out.println("jsonObject"+jsonObject.toJSONString());
        int res = meetingRoomDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject insert(JSONObject jsonObject){
        int res = meetingRoomDao.insert(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        JsonResultUtil.fillPageParam(jsonObject);

//        int count = meetingRoomDao.count(jsonObject);

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);

        List<JSONObject> list = meetingRoomDao.listByPage(jsonObject);
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    @Override
    public JSONObject delete (Long id){
        int res = meetingRoomDao.delete(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject selectList(JSONObject jsonObject) {

        List<JSONObject>  jsonObjects = meetingRoomDao.selectList(jsonObject);
        System.out.println("listjson:"+jsonObjects.toString());
        return JsonResultUtil.successJson(jsonObjects);
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
    }


    @Override
    public JSONObject booking (Long id){
        int res = meetingRoomDao.booking(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    /**
     * @描述 根据房间名修改状态 房间名唯一
     * @date
     */
    public int updateByRoomName(JSONObject jsonObject)
    {
        return meetingRoomDao.updateByRoomName(jsonObject);
    }
}