package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.MeetService;
import com.julong.oasystem.service.MeetingRoomService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.CsEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议相关Controller
 */
@RestController
@RequestMapping("/meet")
public class MeetController {

    @Autowired
    private MeetService meetService;

    @Autowired
    private MeetingRoomService meetingRoomService;

    /**
     * 新增会议
     * @param requestJson
     * @return
     */
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {

        System.out.println("requestJson:"+requestJson.toJSONString());
        //return applyRoomService.insert(requestJson);

        //获取开会人员id数组
        List<String> userIds = JSON.parseArray(JSON.toJSONString(requestJson.get("userIds"))).toJavaList(String.class);
        String[] userIdsArr = new String[userIds.size() + 1];
        for (int i=0;i<userIds.size();i++)
        {
            userIdsArr[i]=userIds.get(i);
        }
        System.out.println("userIds:"+userIds.toString());
        //List<String> userIds =JSONObject.parseArray(requestJson.get("userIds"), String.class);
        //获取负责人id
        String create_By = requestJson.getString("create_By");

        //判断房间使用状况
        JSONObject meetingRoom = meetingRoomService.selectByRoomName(requestJson);
        System.out.println("meetingRoom:"+meetingRoom.toJSONString());
        Integer status = Integer.valueOf(meetingRoom.getString("status"));
        if (status == CsEnum.meetRoom.MEET_ROOM_STATUS_FREE.getValue())
        {

            //把自己带上
            boolean contains = Arrays.asList(userIds).contains(create_By);
            if (!contains)
            {
                System.out.println("没有包含");
                String[] arrNew = new String[userIdsArr.length + 1];
                for (int i=0;i<userIdsArr.length;i++)
                {
                    arrNew[i]=userIdsArr[i];
                }
//                String[] arrNew = new String[userIds.size() + 1];
//                for (int i=0;i<userIds.size();i++)
//                {
//                    arrNew[i]=userIds.get(i);
//                }
                arrNew[arrNew.length-1]=create_By;
                return JsonResultUtil.result(meetService.insertSelective(requestJson, arrNew));
            }

            return JsonResultUtil.result(meetService.insertSelective(requestJson, userIdsArr));

           // return JsonResultUtil.errorJson(101," 会议室已预约中！");//
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
     * 更新会议数据
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return meetService.update(requestJson);
    }

    /**
     * 删除会议
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return meetService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取会议列表数据
     * @param request
     * @return
     */
    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return meetService.list(jsonObject);
    }

    /**
     * 首页获取最新会议列表数据
     * @param request
     * @return
     */
    @GetMapping("/newMeetingList")
    public JSONObject newList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return meetService.newListMeeting(jsonObject);
    }

    @GetMapping("/meetingDetail")
    public JSONObject selectMeetingById(@RequestParam("id") String id){
        return meetService.selectMeetingById(id);
    }

    @GetMapping("selectList")
    public JSONObject selectList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return meetService.selectList(jsonObject);
    }
    
}
