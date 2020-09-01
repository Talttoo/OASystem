package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.WageItemDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.wage.WageItemVO;
import com.julong.oasystem.entity.wage.WageVO;
import com.julong.oasystem.service.WageItemService;
import com.julong.oasystem.service.WageService;
import com.julong.oasystem.utils.CommonUtils;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:26
 * @Description：
 */
@Service
public class WageItemServiceImpl implements WageItemService {

    @Autowired
    WageItemDao wageItemDao;

    @Autowired
    WageService wageService;

    @Autowired
    private CommonUtils commonUtils;

    @Override
    public List<WageItemVO> queryWageItem() {
        return null;
    }

    @Override
    public List<WageItemVO> queryWageItemBywageId(String wageId) {
        return wageItemDao.queryWageItemByWageId(wageId);
    }

    @Override
    public List<WageItemVO> queryWageItemBywageIdAndUser(JSONObject jsonObject) {
        return wageItemDao.queryWageItemBywageIdAndUser(jsonObject);
    }

    @Override
    public WageItemVO queryWageItemById(String id) {
        return null;
    }

    @Transactional
    @Override
    public JSONObject queryWageItemByUser(JSONObject jsonParam) {

        JsonResultUtil.fillPageParam(jsonParam);

        int pageNum = jsonParam.getIntValue("pageNum");
        int pageRow = jsonParam.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<WageItemVO> wageItemVOS = wageItemDao.queryWageItemByUser(jsonParam.getString("id"),jsonParam.getString("name"));


        List<WageVO> wageVOList = new ArrayList<>();

        for (WageItemVO w : wageItemVOS) {

            WageVO wage = wageService.queryWageByID(w.getWageId());
            wageVOList.add(wage);
        }

        return JsonResultUtil.successPageObject(jsonParam, wageVOList, (int)page.getTotal());
    }

    @Override
    public boolean insertWageItem(WageItemVO WageItem) {
        if(wageItemDao.insertWageItem(WageItem)>0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean updateWageItem(WageItemVO WageItem) {
        return false;
    }

    @Override
    public boolean deleteWageItem(String id) {
        return false;
    }

    @Override
    public boolean deleteWageItemsByPaperId(String id) {
        return false;
    }

    @Override
    public List<WageItemVO> getWageItemsByPaperIdAndWageItemType(String id, Integer WageItemType) {
        return null;
    }
}
