package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.ScheduleDao;
import com.julong.oasystem.dao.ScheduleUserDao;
import com.julong.oasystem.entity.*;
import com.julong.oasystem.service.ScheduleService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.StringTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 永健
 */
@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleDao scheduleDao;

    @Autowired
    ScheduleUserDao scheduleUserDao;


    /**
     *
     * @描述: 批量删除
     *
     * @params:
     * @return:
     * @date:
     */
    @Override
    public int deleteByPrimaryKeys(Integer[] id)
    {
        //删除中间表
        scheduleUserDao.deleteByPrimaryKeys(id);
        return scheduleDao.deleteByPrimaryKeys(id);
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
    public int insertSelective(ScheduleVO record, String[] userIds)
    {
        int i = scheduleDao.insertSelective(record);
        if (!StringTools.isNullOrEmpty(userIds) && userIds.length>0)
        {
            List<ScheduleUser> listScheduleList = getListScheduleList(record.getId(),userIds);
            for(ScheduleUser scheduleUser: listScheduleList) {
                System.out.println("scheduleUser:"+scheduleUser.toString());
                int j = scheduleUserDao.insertSelective(scheduleUser);
                System.out.println("插入是否成功:"+j );
                if(j<0) return j;
            }
        }
        return i;
    }


    /**
     *
     * @描述: 主键查询
     *
     * @params:
     * @return:
     * @date:
     */
    @Override
    public JSONObject selectByPrimaryKey(Integer id)
    {

        return JSON.parseObject(JSON.toJSONString(scheduleDao.selectByPrimaryKey(id)));
    }

    /**
     * 首页日程查询
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject getCalendarSchedule(JSONObject jsonObject) {
        List<CalendarScheduleVO> calendarScheduleVOList =  scheduleDao.getCalendarSchedule(jsonObject);
        List<CalendarDataVO> list = tranCalendarData(calendarScheduleVOList);
        System.out.println("日程："+list.toString());
        return JsonResultUtil.successJson(list);
    }

    /**
     * 分割日期，转换成前端所需数据
     * @param list
     * @return
     */
    public List<CalendarDataVO> tranCalendarData( List<CalendarScheduleVO> list){

        List<CalendarDataVO> calendarDataList =  new ArrayList<>();
        for(CalendarScheduleVO schedule :list) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(schedule.getStartTime());
           // System.out.println("Calendar.MONTH:"+String.valueOf(calendar.get(Calendar.MONTH)));

            CalendarDataVO calendarData = new CalendarDataVO();
            System.out.println("calendar:"+calendar.toString());
            calendarData.setYears(String.valueOf(calendar.get(Calendar.YEAR)));
            if(calendar.get(Calendar.MONTH)<9){
                calendarData.setMonths("0"+(calendar.get(Calendar.MONTH)+1));
            }else {
                calendarData.setMonths(String.valueOf(calendar.get(Calendar.MONTH)+1));
            }
            if(calendar.get(Calendar.DAY_OF_MONTH)<10) {
                calendarData.setDays("0"+(calendar.get(Calendar.DAY_OF_MONTH)));
            }else {
                calendarData.setDays(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            }
            calendarData.setId(schedule.getsId());
            calendarData.setThings(schedule.getTitle());

            calendarDataList.add(calendarData);

//            String[] temp;
//            temp = schedule.getStartTime().split("-"); // 分割字符串
//            // 普通 for 循环
//            for (int i = 0; i < temp.length; i++) {
//                System.out.println(temp[i]);
//                System.out.println("");
//            }

        }

        return calendarDataList;


    }

    /**
     *
     * @描述: 修改
     *
     * @params:
     * @return:
     * @date:
     */
    @Override
    public int updateByPrimaryKeySelective(ScheduleVO record, String[] userIds)
    {
        //删除原有的
        Integer[] ids={record.getId()};
        scheduleUserDao.deleteByPrimaryKeys(ids);

        if (!StringTools.isNullOrEmpty(userIds) && userIds.length>0)
        {
            //插入新的
            List<ScheduleUser> listScheduleList = getListScheduleList(record.getId(),userIds);
            for(ScheduleUser scheduleUser: listScheduleList) {
                scheduleUserDao.insertSelective(scheduleUser);
            }
        }

        return scheduleDao.updateByPrimaryKeySelective(record);
    }



    /**
     *
     * @描述:  修改日程工作完成状态
     *
     * @params:
     * @return:
     * @date:
    */
    public int updateComplete(ScheduleVO scheduleVO){
        return scheduleDao.updateByPrimaryKeySelective(scheduleVO);
    }





    /**
     *
     * @描述: 列表
     *
     * @params:
     * @return:
     * @date:
     */
    @Override
    public JSONObject selectScheduleList(JSONObject jsonObject)
    {
        System.out.println("jsonObject:"+jsonObject.toJSONString());
        JsonResultUtil.fillPageParam(jsonObject);
//        int count = meetDao.count(jsonObject);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<ScheduleVO> list = scheduleDao.selectScheduleList(jsonObject);
        System.out.println("返回表格数据："+list.toString());
        return JsonResultUtil.successPageSchedule(jsonObject, list, (int)page.getTotal());
    }


    private static List<ScheduleUser>  getListScheduleList(Integer sId, String[] userIds)
    {
        List<ScheduleUser> objects = new ArrayList<>();
        for (String id:userIds)
        {
            ScheduleUser scheduleUser = new ScheduleUser();
            scheduleUser.setsId(sId);
            scheduleUser.setSuId(id);
            objects.add(scheduleUser);
        }
        return objects;
    }

//    private static List<JSONObject>  getListScheduleList(Integer sId, String[] userIds)
//    {
//        List<JSONObject> objects = new ArrayList<>();
//        for (String id:userIds)
//        {
//            ScheduleUser scheduleUser = new ScheduleUser();
//            scheduleUser.setsId(sId);
//            scheduleUser.setSuId(id);
//            objects.add(JSONObject.parseObject(JSON.toJSONString(scheduleUser)));
//        }
//        return objects;
//    }
}

