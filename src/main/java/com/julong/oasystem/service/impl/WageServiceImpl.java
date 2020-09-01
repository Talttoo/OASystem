package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.WageDao;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.wage.WageVO;
import com.julong.oasystem.service.WageService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 16:23
 * @Description：
 */
@Service
public class WageServiceImpl implements WageService {

    @Autowired
    WageDao wageDao;

    @Override
    public JSONObject queryWage(JSONObject jsonObject) {
        JsonResultUtil.fillPageParam(jsonObject);

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageRow = jsonObject.getIntValue("pageRow");
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<WageVO> list =  wageDao.queryWage();
        return JsonResultUtil.successPageObject(jsonObject, list, (int)page.getTotal());
    }

    @Override
    public List<WageVO> queryWageByUser(String userId,String username) {
        return wageDao.queryWageByUser(userId,username);
    }

    @Override
    public WageVO queryWageByID(String id) {
        return wageDao.queryWageByID(id);
    }

    @Override
    public boolean  insertWage(WageVO WageVO) {
        if(wageDao.insertWage(WageVO)>0) {
            return true;
        }else {
            return false;
        }

    }

    @Transactional
    @Override
    public boolean updateWage(WageVO WageVO) {

            if (WageVO != null && !"".equals(WageVO.getId())) {
                try {
                    int i = wageDao.updateWage(WageVO);
                    if (i == 1) {
                        return true;
                    } else {
                        throw new RuntimeException("a:修改工资失败！" + WageVO);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("b:修改工资失败：" + e.getMessage());
                }
            } else {
                throw new RuntimeException("c:修改工资失败，Wage的id不能为空！");
            }

    }
    @Transactional
    @Override
    public boolean deleteWage(String id) {
        if (id != null && !"".equals(id)) {
            try {
                //System.out.println(id);
                int i = wageDao.deleteWage(id);
                if (i == 1) {
                    return true;
                } else {
                    throw new RuntimeException("a:删除工资失败！" + id);
                }
            } catch (Exception e) {
                throw new RuntimeException("b:删除工资失败：" + e.getMessage());
            }
        } else {
            throw new RuntimeException("c:删除工资失败，Wage的id不能为空！");
        }
    }

    @Override
    public List<WageVO> queryWageByTitle(String title) {
        return null;
    }

    @Transactional
    @Override
    public boolean deleteManyWage(List<String> id) {
        if (id == null || id.size() <= 0) {
            throw new RuntimeException("c:删除试卷失败，Wage的id不能为空！");
        }
        //int t = id.size();
        for (String i : id) {
            WageVO wage = new WageVO();
            wage.setId(i);
            wage.setStatus(1);
            updateWage(wage);
        }


        return true;
    }

    @Override
    public Object dataWage(String id) throws ParseException {
        return null;
    }
}
