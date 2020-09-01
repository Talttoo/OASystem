package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author Taltoo
 * @Date 2020/6/21 0004 下午 15:02
 * @Description：危急值极限值定义
 */
public interface CriticalValueService {

    JSONObject addCritical(JSONObject jsonObject);

    JSONObject updateCritical(JSONObject jsonObject);

    JSONObject deleteCritical(JSONObject jsonObject);

    JSONObject listItem();

    JSONObject listCriticalValue(JSONObject jsonObject);
}
