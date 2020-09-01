package com.julong.oasystem.config;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.AccessWeChatService;
import com.julong.oasystem.service.CriticalRemindService;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：自动发送危急值提醒
 */
@Configuration
@EnableScheduling
public class CompleteScheduleConfig implements SchedulingConfigurer {


    public static String times;

    @Autowired
    @SuppressWarnings("all")
    CriticalRemindService criticalRemindService;


    @Autowired
    WeChatConfigService weChatConfigService;

    @Autowired
    @SuppressWarnings("all")
    AccessWeChatService accessWeChatService;


    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

//        taskRegistrar.addTriggerTask(
//                //1.添加任务内容(Runnable)
//                () -> getSchedule(),
//                //2.设置执行周期(Trigger)
//                triggerContext -> {
//                    //2.1 从数据库获取执行周期
//                   // List<JSONObject> cron = weChatUserDao.getWeChatUser(times);
//                   // 2.2 合法性校验.
////                    if (StringUtils.isEmpty(cron)) {
////                        // Omitted Code ..
////                    }
//                   // 2.3 返回执行周期(Date)
//                    return new CronTrigger("0/10 * * * * ?").nextExecutionTime(triggerContext);
//                }
//        );

    }

    /**
     * 远程调用连接微信接口，发送消息提醒，写进数据库
     */
    @Transactional
    public  void  getSchedule(){
//        List<JSONObject> list = weChatUserDao.getWeChatUser(new JSONObject());
//
//        System.out.println("list:"+list.toString());
        if(times==null||times=="") {
            times = JsonResultUtil.getCurrentTime();
        }
        System.out.println("times:"+times);
        //查询新插入且未发送提醒的数据
        List<JSONObject> cron = criticalRemindService.getNewInsert(times);
        //查完一次更新时间
        times = JsonResultUtil.getCurrentTime();
       // List<JSONObject> users = userDao.listUser(new JSONObject());

        if(cron.size()>0){
            //从数据库获取微信访问参数
            JSONObject jsonParam = weChatConfigService.getMessageParam(new JSONObject());
            if(jsonParam.containsKey("appid")) {
                for (int i = 0; i < cron.size(); i++) {
                    //将微信访问参数加入请求参数
                    cron.get(i).put("appid",jsonParam.getString("appid"));
                    cron.get(i).put("appSecret",jsonParam.getString("appSecret"));
                    cron.get(i).put("agentid",jsonParam.getString("agentid"));
                    //发送提醒消息
                    JSONObject result = accessWeChatService.sendRemindTextcard(cron.get(i));
                    int  errcode =  result.getIntValue("errcode");
                    //发送成功
                    if(errcode==0){
                        System.out.println("result:"+result.toJSONString());
                        //更新发送数据的发送状态
                        int send_flag = criticalRemindService.updateSend (cron.get(i).getString("repNo"));
                        //记录发送消息
                       JSONObject res = criticalRemindService.insertTextcardMessage(jsonParam, cron.get(i));
                    }else {
                        System.out.println("发送提醒消息失败！");
                    }
                }

//                JSONObject result = asCriticalRemindService.sendmessages(jsonParam, requestJson);



            }
            System.out.println("cron:"+cron.toString());
        }else {
            System.out.println("暂无新提醒");
        }
    }

}
