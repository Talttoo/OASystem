package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.DeptDao;
import com.julong.oasystem.dao.WeChatCacheDao;
import com.julong.oasystem.entity.DeptAndUserVO;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.DeptService;
import com.julong.oasystem.service.LoginService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.wechat.WeChatCommon;
import com.julong.oasystem.wechat.WeChatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author Taltoo
 * @Date 2020/5/12 0004 下午 15:02
 * @Description：科室
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private LoginService loginService;

    @Autowired
    private WeChatCacheDao weChatCacheDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public JSONObject update(JSONObject jsonObject){
        System.out.println("jsonObject"+jsonObject.toJSONString());
        int res = deptDao.update(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"更新数据异常");
        }
    }


    @Transactional
    @Override
    public JSONObject sysnyc(JSONObject jsonObject){
        System.out.println("同步微信部门数据："+jsonObject.toJSONString());

        JSONObject queryJsonObject = new JSONObject();
        queryJsonObject.put("key", WeChatCommon.ACCESSTOKEN);

        String accessToken = loginService.getWeChatCache(queryJsonObject);

        JsonResultUtil.fillOrgPk(queryJsonObject);

        //科室先清空，再增加
        int res = deptDao.deleteWeChatDep(queryJsonObject);

        if(res>0){
            this.syschDep(accessToken);
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"同步数据异常");
        }




    }


    private void syschDep(String accessToken) {
        JSONArray orgList= WeChatUtil.getAllDep(accessToken).getJSONArray("department");

        JSONObject orgJson = null;
        JSONObject orgJsontemp = null;
        System.out.println("orgList:"+orgList.toJSONString());

        for(int i= 0;i<orgList.size();i++) {
            orgJson = orgList.getJSONObject(i);
            orgJsontemp = new JSONObject();
            orgJsontemp.put("id", orgJson.getString("id"));
            orgJsontemp.put("deptname", orgJson.getString("name"));
            orgJsontemp.put("parentid", orgJson.getString("parentid"));
            JsonResultUtil.fillOrgPk(orgJsontemp);
            deptDao.insertWeChatDep(orgJsontemp);
        }

    }
    @Override
    public JSONObject insert(JSONObject jsonObject){
        int res = deptDao.insert(jsonObject);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"插入数据异常");
        }
    }

    @Override
    public JSONObject list(JSONObject jsonObject){
        JsonResultUtil.fillPageParam(jsonObject);

//        int count = deptDao.count(jsonObject);
        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<JSONObject> list = deptDao.listByPage(jsonObject);
        return JsonResultUtil.successPage(jsonObject, list, (int)page.getTotal());
    }

    @Override
    public JSONObject delete (Long id){
        int res = deptDao.delete(id);
        if(res>0){
            return JsonResultUtil.successJson();
        }else {
            return JsonResultUtil.errorJson(401,"删除数据异常");
        }
    }

    @Override
    public JSONObject selectList(JSONObject jsonObject) {

        List<JSONObject>  jsonObjects = deptDao.selectList(jsonObject);
        System.out.println("dept——listjson:"+jsonObjects.toString());
        return JsonResultUtil.successJson(jsonObjects);
    }

    /**
     *
     * @描述: 查询所有部门下的所有用户 用户归类 树状数据
     *
     * @date:
     */
    @Override
    public List<DeptAndUserVO> selectDeptAndUser()
    {
        return deptDao.selectDeptAndUser();
    }
}
