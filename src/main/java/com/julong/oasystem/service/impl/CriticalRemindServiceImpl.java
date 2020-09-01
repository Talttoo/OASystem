package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.CriticalRemindDao;
import com.julong.oasystem.entity.CriticalMessageVO;
import com.julong.oasystem.entity.CriticalRemindVO;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.CriticalRemindService;
import com.julong.oasystem.utils.JsonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/20 0004 下午 14:52
 * @Description：危急值提醒
 */
@Slf4j
@Transactional
@Service
public class CriticalRemindServiceImpl implements CriticalRemindService {

    @Autowired
    CriticalRemindDao criticalRemindDao;

   public JSONObject getList(JSONObject jsonObject){


       int pageNum = jsonObject.getIntValue("pageNum");
       int pageRow = jsonObject.getIntValue("pageRow");
       Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
       List<JSONObject> reminds = criticalRemindDao.getList();

      // System.out.println("reminds:"+reminds.toString());
       return JsonResultUtil.successPage(jsonObject, reminds, (int)page.getTotal());

   }


    public JSONObject getListByRepNo(String repNo){

        CriticalRemindVO reminds = criticalRemindDao.getListByRepNo(repNo);

        System.out.println("reminds:"+reminds.toString());

        return JsonResultUtil.successJson(reminds);
    }
    public JSONObject getListByDate(String date){

        List<CriticalRemindVO> reminds = criticalRemindDao.getListByDate(date);

        System.out.println("reminds:"+reminds.toString());

        JSONObject result = new JSONObject();

        result.put("code",200);
        result.put("msg","success");
        result.put("data",reminds.toString());


        return result;

    }

    public List<JSONObject> getNewInsert(String times){
       return criticalRemindDao.getNewInsert(times);
    }

    public  JSONObject insertRemindMessage(JSONObject jsonParam,JSONObject requestJson){

        JSONObject msg=new JSONObject();
        System.out.println("doctorid:"+requestJson.getString("userid"));

        msg.put("touser",requestJson.getString("userid"));
        msg.put("username",requestJson.getString("username"));
        msg.put("deptname",requestJson.getString("deptName"));
        msg.put("toparty", "");

        msg.put("sender",requestJson.getString("sender"));

        msg.put("totag", "");
        msg.put("msgtype", "text");
        msg.put("agentid", requestJson.getString("agentid"));

        JSONObject content=new JSONObject();
        content.put("content", requestJson.getString("content"));

        msg.put("content", content.toJSONString());
        msg.put("safe", "0");
        msg.put("enable_id_trans", "0");
        msg.put("enable_duplicate_check", "0");
        msg.put("duplicate_check_interval", "1800");

        int res = criticalRemindDao.insertRemindMessage(msg);

        JSONObject result = new JSONObject();
       if(res>0){
           result.put("code",res);
           result.put("msg","success");
       }else {
           result.put("code",0);
           result.put("msg","error");
           log.error("插入提醒消息失败");
       }

        return result;
    }


    public  JSONObject insertTextcardMessage(JSONObject jsonParam,JSONObject requestJson){

        JSONObject msg=new JSONObject();
        System.out.println("doctorid:"+requestJson.getString("userid"));

        msg.put("touser",requestJson.getString("userid"));
        msg.put("username",requestJson.getString("username"));
        msg.put("deptname",requestJson.getString("deptName"));
        msg.put("toparty", "");

        msg.put("sender",requestJson.getString("sender"));

        msg.put("totag", "");
        msg.put("msgtype", "textcard");
        msg.put("agentid", jsonParam.getString("agentid"));

        JSONObject content=new JSONObject();

        content.put("title", "危急值提醒");
        content.put("description", "<div class=\"gray\">"+ requestJson.getString("inTimes")+"</div> <div class=\"normal\">"+"姓名："+requestJson.getString("name")+"<br/>检验报告号："+requestJson.getString("repNo")+"<br/>科室："+requestJson.getString("deptName")+"<br/>检验结果为："+requestJson.getString("criticalNote")+""+"</div><div class=\"highlight\">请立即前往处理</div>");
        content.put("url", "http://localhost:9092/");
        content.put("btntxt", "更多");

        msg.put("content", content.toJSONString());
        msg.put("safe", "0");
        msg.put("enable_id_trans", "0");
        msg.put("enable_duplicate_check", "0");
        msg.put("duplicate_check_interval", "1800");

        int res = criticalRemindDao.insertRemindMessage(msg);

        JSONObject result = new JSONObject();
        if(res>0){
            result.put("code",res);
            result.put("msg","success");
        }else {
            result.put("code",0);
            result.put("msg","error");
            log.error("插入自动提醒消息失败");
        }

        return result;
    }

    public JSONObject getMessageList(JSONObject jsonObject){

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<CriticalMessageVO> reminds = criticalRemindDao.getMessageList();

        System.out.println("service-messages:"+reminds.toString());
        return JsonResultUtil.successPageMessage(jsonObject, reminds, (int)page.getTotal());
    }


   public int updateSend (String repNo){

       return criticalRemindDao.updateSend (repNo);
   }
}
