package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.CheckItemDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.CheckItemService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/8/1 0004 下午 14:52
 * @Description：检查项目
 */
@Service
public class CheckItemServiceImpl  implements CheckItemService {

    @Autowired
    CheckItemDao checkItemDao;

    @Override
    public JSONObject insert(JSONObject jsonObject) {

        int res = checkItemDao.insert(jsonObject);

        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }

    }

    @Override
    public JSONObject update(JSONObject jsonObject) {
        int res = checkItemDao.update(jsonObject);
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

//		int count = articleDao.countArticle(jsonObject);
//		System.out.println("文章列表数："+count);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = checkItemDao.listByPage(jsonObject);
        System.out.println("项目列表："+list.toString());
//		JSONArray jsonArray = JSON.parseArray(list.toString());
//		List<JSONObject> jsonlist  = JSON.parseArray(jsonArray.toJSONString(),JSONObject.class) ;
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());


    }

    @Override
    public JSONObject delete(Long id) {
        int res = checkItemDao.delete(id);

        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }


    @Override
    public JSONObject selectList(JSONObject jsonObject) {

        List<JSONObject> list =  checkItemDao.selectList(jsonObject);

        return JsonResultUtil.successJson(list);
    }
}
