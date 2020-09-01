package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.CriticalValueDao;
import com.julong.oasystem.entity.CriticalValueVO;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.CriticalValueService;
import com.julong.oasystem.utils.JsonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/6/21 0004 下午 15:02
 * @Description：危急值极限值定义
 */
@Slf4j
@Service
public class CriticalValueServiceImpl implements CriticalValueService {

    @Autowired
    CriticalValueDao criticalValueDao;

    @Override
    public JSONObject addCritical(JSONObject jsonObject) {

        CriticalValueVO findCritical = criticalValueDao.findCriticalValue(jsonObject.getString("itemCode"));
        if(findCritical!=null){
            log.error("该危急值极限值已存在，请修改或者删除重新添加");
            return JsonResultUtil.errorJson(401,"该危急值极限值已存在，请修改或者删除重新添加！");
        }else {
            criticalValueDao.addCritical(jsonObject);

            return JsonResultUtil.successJson();
        }

    }

    @Override
    public JSONObject updateCritical(JSONObject jsonObject) {
        int res =  criticalValueDao.updateCritical(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            log.error("更新危急值极限值数据异常");
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject deleteCritical(JSONObject jsonObject) {
        int res = criticalValueDao.deleteCritical(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            log.error("删除危急值极限值数据异常");
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject listItem() {

        return JsonResultUtil.successJson(criticalValueDao.listItem());
    }

    @Override
    public JSONObject listCriticalValue(JSONObject jsonObject) {

        JsonResultUtil.fillPageParam(jsonObject);
//		int count = userDao.countUser(jsonObject);
        //JSONObject requestJson = JsonResultUtil.request2Json(request);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");

        //使用分页插件,核心代码就这一行，页数、每页行数
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<CriticalValueVO> list = criticalValueDao.listCriticalValue();
        List<JSONObject> jsonlist = new ArrayList<>();

        for(int i=0;i<list.size();i++){
            String jsonString=JSONObject.toJSONString(list.get(i));
            JSONObject jSONObject=JSONObject.parseObject(jsonString);

            jsonlist.add(jSONObject);
        }


        return  JsonResultUtil.successPage(jsonObject, jsonlist, (int)page.getTotal());
    }
}
