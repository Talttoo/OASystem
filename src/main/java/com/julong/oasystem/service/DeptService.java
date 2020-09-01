package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.DeptAndUserVO;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/5/12 0004 下午 15:02
 * @Description：科室
 */
public interface DeptService {

    JSONObject update(JSONObject jsonObject);

    JSONObject sysnyc(JSONObject jsonObject);

    JSONObject insert(JSONObject jsonObject);

    JSONObject list(JSONObject jsonObject);

    JSONObject delete(Long id);

    JSONObject selectList(JSONObject jsonObject);

    /**
     *
     * @描述: 查询所有部门下的所有用户 用户归类 树状数据
     *
     * @date:
     */
    List<DeptAndUserVO> selectDeptAndUser();

}
