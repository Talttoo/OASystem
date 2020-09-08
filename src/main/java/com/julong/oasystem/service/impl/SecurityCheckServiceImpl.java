package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.SecurityCheckDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.SecurityCheckService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：安全检查service实现类
 */
@Service
public class SecurityCheckServiceImpl  implements SecurityCheckService {

    @Autowired
    SecurityCheckDao securityCheckDao;

    @Override
    public JSONObject insert(JSONObject jsonObject) {
        int res = securityCheckDao.insert(jsonObject);

        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    @Override
    public JSONObject update(JSONObject jsonObject) {
        int res = securityCheckDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject listByPage(JSONObject jsonObject) {
        System.out.println("请求检查项目列表："+jsonObject.toJSONString());
        JsonResultUtil.fillPageParam(jsonObject);

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = securityCheckDao.listByPage(jsonObject);
       // System.out.println("项目列表："+list.toString());
//		JSONArray jsonArray = JSON.parseArray(list.toString());
//		List<JSONObject> jsonlist  = JSON.parseArray(jsonArray.toJSONString(),JSONObject.class) ;
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    @Override
    public JSONObject delete(Long id) {
        int res = securityCheckDao.delete(id);

        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject selectList(JSONObject jsonObject) {
        return null;
    }

    /**
     * 我的检查
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listByUser(JSONObject jsonObject) {

        JsonResultUtil.fillPageParam(jsonObject);

//        int pageNum = jsonObject.getIntValue("pageNum");
//        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(jsonObject.getIntValue("pageNum"), jsonObject.getIntValue("pageRow"));
        List<JSONObject> list = securityCheckDao.listByUser(jsonObject);
        System.out.println("请求检查项目列表："+jsonObject.toJSONString());
        System.out.println("项目列表："+list.toString());
//		JSONArray jsonArray = JSON.parseArray(list.toString());
//		List<JSONObject> jsonlist  = JSON.parseArray(jsonArray.toJSONString(),JSONObject.class) ;
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }
}
