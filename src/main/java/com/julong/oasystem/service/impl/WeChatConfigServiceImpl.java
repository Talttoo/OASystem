package com.julong.oasystem.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.WeChatConfigDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.WeChatConfigService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/7/10 0004 下午 14:52
 * @Description：微信配置参数service实现类
 */
@Service
public class WeChatConfigServiceImpl implements WeChatConfigService {

    @Autowired
    private WeChatConfigDao wechatConfigDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        int res  = wechatConfigDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }

    @Override
    public JSONObject insert(JSONObject jsonObject){
        int res = wechatConfigDao.insert(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        JsonResultUtil.fillPageParam(jsonObject);
        JsonResultUtil.fillOrgPk(jsonObject);
//        int count = wechatConfigDao.count(jsonObject);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = wechatConfigDao.listByPage(jsonObject);
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    @Override
    public JSONObject listAll(JSONObject jsonObject){
        List<JSONObject> list = wechatConfigDao.listAll();
        return JsonResultUtil.successPage(list);
    }

    @Override
    public JSONObject delete (Long id){
        int res = wechatConfigDao.delete(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject getByPkOrg(JSONObject jsonObject) {
        return wechatConfigDao.getByPkOrg(jsonObject);
    }

    @Override
    public JSONObject getMessageParam(JSONObject jsonObject){
        return wechatConfigDao.getMessageParam(jsonObject);
    }
}