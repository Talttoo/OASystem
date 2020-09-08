package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.ApplyRoomDao;
import com.julong.oasystem.dao.MeetingRoomDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.ApplyRoomService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ApplyRoomServiceImpl  implements ApplyRoomService {
    @Autowired
    private ApplyRoomDao applyRoomDao;

    @Autowired
    private MeetingRoomDao meetingRoomDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        System.out.println("更新会议室申请："+jsonObject.toJSONString());
        int res = applyRoomDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public int  insert(JSONObject jsonObject){

       // System.out.println("申请预约会议室："+jsonObject.toJSONString());
        return applyRoomDao.insert(jsonObject);
//        if(res>0){
//            return JsonResultUtil.successJson();
//        }else {
//            return JsonResultUtil.errorJson(401,"插入数据异常");
//        }
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        JsonResultUtil.fillPageParam(jsonObject);
//        int count = applyRoomDao.count(jsonObject);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = applyRoomDao.listByPage(jsonObject);
        System.out.println("请求会议室申请列表："+list.toString());
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    @Transactional
    @Override
    public JSONObject approve(JSONObject jsonObject) {
        int res = applyRoomDao.approve(jsonObject);
        if(res>0){
            meetingRoomDao.usingRoom(jsonObject.getString("roomName"));
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject delete (Long id){
        System.out.println("删除会议室申请id："+id);
        int res = applyRoomDao.delete(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject selectList(JSONObject jsonObject) {
        return JsonResultUtil.successJson(applyRoomDao.selectList(jsonObject));
    }
}
