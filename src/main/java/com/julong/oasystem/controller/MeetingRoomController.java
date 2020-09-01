package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.MeetingRoomService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：会议室相关
 */
@RestController
@RequestMapping("meetingRoom")
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService MeetingRoomService;

    /**
     * 新增会议室
     * @param requestJson
     * @return
     */
    @PostMapping
    public JSONObject insert(@RequestBody JSONObject requestJson) {
        return MeetingRoomService.insert(requestJson);
    }

    /**
     * 更新会议室数据
     * @param requestJson
     * @return
     */
    @PutMapping
    public JSONObject update(@RequestBody JSONObject requestJson) {
        return MeetingRoomService.update(requestJson);
    }

    /**
     * 删除会议室
     * @param request
     * @return
     */
    @DeleteMapping
    public JSONObject delete(HttpServletRequest request) {
        return MeetingRoomService.delete(Long.valueOf(request.getParameter("id")));
    }

    /**
     * 获取会议室数据列表
     * @param request
     * @return
     */
    @GetMapping
    public JSONObject list(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return MeetingRoomService.list(jsonObject);
    }

    /**
     * 获取会议室列表数据，用于下拉列表框
     * @param request
     * @return
     */
    @GetMapping("selectList")
    public JSONObject selectList(HttpServletRequest request) {
        JSONObject jsonObject= JsonResultUtil.request2Json(request);
        return MeetingRoomService.selectList(jsonObject);
    }
}
