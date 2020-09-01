package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.ApplyRoomService;
import com.julong.oasystem.service.MeetingRoomService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.CsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Taltoo
 * @Date 2020/5/1 0004 下午 15:52
 * @Description：
 */
@RestController
@RequestMapping("applyRoom")
public class ApplyRoomController {

    @Autowired
    private ApplyRoomService applyRoomService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    /**
     * 新增会议室预约
     * @param requestJson
     * @return
     */
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {
        //return applyRoomService.insert(requestJson);
        //判断房间使用状况


        JSONObject meetingRoom = meetingRoomService.selectByRoomName(requestJson);
        System.out.println("meetingRoom:"+meetingRoom.toJSONString());
        Integer status = Integer.valueOf(meetingRoom.getString("status"));
        if (status == CsEnum.meetRoom.MEET_ROOM_STATUS_FREE.getValue())
        {
            //插入预约申请表
            applyRoomService.insert(requestJson);
            //更新会议室的状态
           return meetingRoomService.booking(Long.valueOf(requestJson.getString("id")));

//            Integer code = Integer.valueOf(jsonObject.getString("code"));
//            System.out.println("code:"+code);
//            if(code == Constants.SUCCESS_CODE){
//                 meetingRoomService.booking(Long.valueOf(requestJson.getString("id")));
//            }
//            return  JsonResultUtil.successJson();
        }
        else if (status == CsEnum.meetRoom.MEET_ROOM_STATUS_APPLYING.getValue())
        {
            return JsonResultUtil.errorJson(101," 会议室已预约中！");
        }
        else if (status == CsEnum.meetRoom.MEET_ROOM_STATUS_USING.getValue())
        {
            return JsonResultUtil.errorJson(102," 会议室使用中！");
        }
        else
        {
            return JsonResultUtil.errorJson(103," 会议室已停用！");
        }

    }

    /**
     * 更新会议室预约情况
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return applyRoomService.update(requestJson);
    }

    /**
     * 删除会议室预约申请
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return applyRoomService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取会议室预约申请列表数据
     * @param request
     * @return
     */
    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return applyRoomService.list(jsonObject);
    }


    @GetMapping("selectList")
    public JSONObject selectList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return applyRoomService.selectList(jsonObject);
    }
}
