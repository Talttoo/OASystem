package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.ScheduleVO;
import com.julong.oasystem.service.ScheduleService;
import com.julong.oasystem.utils.CommonUtils;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.CsEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taltoo
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    ScheduleService scheduleService;


    /**
     *
     * @描述: 修改日程工作完成状态
     *ScheduleVO schedule = JSON.parseObject(jsonObject.getJSONObject("schedule").toJSONString(),ScheduleVO.class);
     * @params:
     * @return:
     * @date: 2018/9/29 14:00
     */
    @PostMapping("/updateComplete")
    public JSONObject updateComplete(@RequestBody JSONObject jsonObject) {
        System.out.println("完成..."+jsonObject.getIntValue("id"));

        ScheduleVO scheduleVO = new ScheduleVO();
        scheduleVO.setId(jsonObject.getIntValue("id"));
        scheduleVO.setIsComplete(CsEnum.scheduled.SCHEDULE_YES_COMPLETE.getValue());
        return (scheduleService.updateComplete(scheduleVO)>0)? JsonResultUtil.successJson():JsonResultUtil.errorJson(401,"修改日程工作完成状态失败");
    }


    /**
     *
     * @描述: ajaxgetList
     *
     * @params:
     * @return:
     * @date:
     */
    @RequestMapping("/ajaxgetMap")
    public Map<String, String> ajaxgetMap() {
        Map<String, String> map = new HashMap<>();
//        List<ScheduleVO> schedules = scheduleService.selectScheduleList(new JSONObject());
//        for (ScheduleVO s : schedules)
//        {
//            if (s.getIsComplete() == CsEnum.scheduled.SCHEDULE_NO_COMPLETE.getValue())
//            {
//                map.put(CommonUtils.DateToSTr(s.getStartTime()).substring(0, 10), s.getTitle());
//            }
//        }

        return map;
    }


    /**
     *
     * @描述: 通过安排日期模糊查询出当天的安排
     *     返回多个数据
     * @params:
     * @return:
     * @date:
     */
    @PostMapping ("/editMore")
    @RequiresPermissions("schedule:update")
    public JSONObject editMore(@RequestParam("date") String date) {
        ScheduleVO scheduleVO = new ScheduleVO();
        //查询未完成的
        scheduleVO.setIsComplete(CsEnum.scheduled.SCHEDULE_NO_COMPLETE.getValue());
        scheduleVO.setStartTime(CommonUtils.StrToDate(date));
        System.out.println(CommonUtils.DateToSTr(CommonUtils.StrToDate(date)));
       // List<ScheduleVO> schedules = scheduleService.selectScheduleList(JSON.parseObject(JSON.toJSONString(scheduleVO)));

        return scheduleService.selectScheduleList(JSON.parseObject(JSON.toJSONString(scheduleVO)));
    }


    /**
     *
     * @描述: 返回表格数据
     *
     * @params:
     * @return:
     * @date:
     */
    @PostMapping("/tableList")
    public JSONObject tableList(@RequestBody JSONObject request) {
//        System.out.println("请求表格数据。。。。"+request.toJSONString());
       // List<ScheduleVO> schedules = scheduleService.selectScheduleList(JsonResultUtil.request2Json(request));
        //System.out.println("返回表格数据："+schedules.toString());
        return scheduleService.selectScheduleList(request);
    }

    /**
     *
     * @描述: 返回首页日程数据
     *
     * @params:
     * @return:
     * @date:
     */
    @PostMapping("/calendarData")
    public JSONObject calendarData(@RequestBody JSONObject request) {
         System.out.println("首页日程数据。。。。");
        // List<ScheduleVO> schedules = scheduleService.selectScheduleList(JsonResultUtil.request2Json(request));
        JSONObject jsonObject = scheduleService.getCalendarSchedule(request);
        System.out.println("返回数据："+jsonObject.toJSONString());
        return jsonObject;
        //return scheduleService.getCalendarSchedule(request);
    }




    /**
     *
     * @描述: 添加保存
     *
     * @params:
     * @return:
     * @date:
     */
    @PostMapping("/addSave")
    public JSONObject addSave(@RequestBody JSONObject jsonObject) throws Exception {
        System.out.println("jsonObject:"+jsonObject.toJSONString());
        ScheduleVO scheduleVO = JSON.parseObject(jsonObject.getJSONObject("schedule").toJSONString(), ScheduleVO.class);
        String[] userIds = jsonObject.getJSONArray("userList").toArray(new String[0]);
        System.out.println("userIds.length:"+userIds.length);
        System.out.println("scheduleVO:"+ scheduleVO.toString());
        System.out.println("userIds:"+userIds.toString());
        return (scheduleService.insertSelective(scheduleVO, userIds)>0)?JsonResultUtil.successJson():JsonResultUtil.errorJson(401,"日程安排添加保存失败");
    }

    /**
     *
     * @描述: 删除
     *
     * @params:
     * @return:
     * @date:
     */
    @PostMapping("/del")
    public JSONObject del(@RequestBody JSONObject jsonObject) {
        Integer[] ids = jsonObject.getJSONArray("idList").toArray(new Integer[0]);
        System.out.println("ids:"+ids.toString());
        return (scheduleService.deleteByPrimaryKeys(ids)>0)?JsonResultUtil.successJson():JsonResultUtil.errorJson(401,"日程安排添加保存失败");
    }


    /**
     *
     * @描述: 编辑页面
     *
     * @params:
     * @return:
     * @date:
     */
    @PostMapping("/edit")
    public JSONObject toEdit(@RequestParam("id") Integer id) {

        return JsonResultUtil.successJson(scheduleService.selectByPrimaryKey(id));


    }


    /**
     *
     * @描述: 通过id获取单条
     *
     * @params:
     * @return:
     * @date:
     */
    @RequestMapping("/selectById")
    public JSONObject selectById(@RequestParam("id") Integer id)
    {

        return scheduleService.selectByPrimaryKey(id);
    }


    /**
     *
     * @描述: 修改保存
     *
     * @params:
     * @return:
     * @date:
     */
    @RequestMapping("/editSave")
    public JSONObject editSave(@RequestBody JSONObject jsonObject)
    {
        ScheduleVO scheduleVO = JSON.parseObject(jsonObject.getJSONObject("scheduleVO").toJSONString(), ScheduleVO.class);
        String[] userIds = jsonObject.getJSONArray("userList").toArray(new String[0]);
        return (scheduleService.updateByPrimaryKeySelective(scheduleVO, userIds)>0)?JsonResultUtil.successJson():JsonResultUtil.errorJson(401,"日程安排修改保存失败");
    }
}
