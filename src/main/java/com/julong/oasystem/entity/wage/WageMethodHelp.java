package com.julong.oasystem.entity.wage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.WageItemService;
import com.julong.oasystem.service.WageService;
import com.julong.oasystem.utils.CommonUtils;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 23:22
 * @Description：
 */
@Service
public class WageMethodHelp {
    @Autowired
    private WageItemService wageItemService;

    @Autowired @Lazy
    private WageService wageService;

    @Autowired
    private CommonUtils commonUtils;


    public JSONObject insertWage(InputWageVO wage, String userId, String id) throws ParseException {

        if (checkPaper(wage) == null) {
            //放入插入代码
            //id = null;
            //System.out.println(id);
            return insert(wage, userId, id);
        } else {
            return checkPaper(wage);
        }
    }

    @Transactional
    public JSONObject insert(InputWageVO wage, String userId, String id) throws ParseException {
        Map<String, Object> map = new HashMap<>();
        //HttpSession session = (HttpSession) request.getAttribute("session");
        //System.out.println(id);
        if (userId != null) {
            //通过检测，准备添加数据
            //System.out.println("准备添加数据");
            WageItemVO wageItemVO = new WageItemVO();
            WageVO wageVO = new WageVO();
            //String paper1Id = commonUtils.getUUID();
            if (id == null) {
                wageVO.setId(commonUtils.getUUID());
                //System.err.println("id==null时：id"+paper1.getId());
            } else {
                wageVO.setId(id);
                //System.err.println("id！=null时：id"+paper1.getId());
            }

            wageVO.setUserId(userId);
            wageVO.setCreateTime(new Date());
            wageVO.setWageMonth(wage.getWageMonth());
            System.out.println("wage.getWageColumn()"+wage.getWageColumn().toString());
            wageVO.setWageColumn(JSONArray.parseArray(JSON.toJSONString(wage.getWageColumn())).toJSONString());

            wageVO.setStatus(wage.getStatus());

            //吧paper1存入数据库
            wageService.insertWage(wageVO);


            //paper添加完成，接下来添加Questions
            for (InputWageItemVO a : wage.getWageItem()) {
                wageItemVO.setId(commonUtils.getUUID());
                wageItemVO.setWageId(wageVO.getId());
                wageItemVO.setCreateTime(new Date());
                wageItemVO.setWageEmployeeDept(a.getWageEmployeeDept());
                wageItemVO.setWageEmployee(a.getWageEmployee());
                wageItemVO.setWageDetails(JSONArray.parseArray(JSON.toJSONString(a.getWageDetails())).toJSONString());


                System.out.println("details:" + wageItemVO.getWageDetails());

                //把question存入数据库
                wageItemService.insertWageItem(wageItemVO);
            }

//            map.put("code", 0);
//            map.put("msg", "ok");
//
            return JsonResultUtil.successJson();

        } else {
            return JsonResultUtil.errorJson(-1,"not token");
        }
    }

    public JSONObject checkPaper(InputWageVO wage) {
        if (wage.getStatus() == 0) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        if (wage.getWageMonth() == null || "".equals(wage.getWageMonth())) {

            return JsonResultUtil.errorJson(2,"工资月份 不能为空");
        } else if (wage.getWageMonth().length() < 2 || wage.getWageMonth().length() > 64) {

            return JsonResultUtil.errorJson(2,"工资月份 应该在2个字符到64个字符之间");
        } else if (wage.getStatus() != 0 && wage.getStatus() != 1) {

            return JsonResultUtil.errorJson(2,"工资状态 应该是0或者是1");
        } else if (wage.getWageColumn().size() <= 0) {
//            map.put("code", 2);
//            map.put("msg", "该试卷没有添加问题");
//            return map;
            return JsonResultUtil.errorJson(2,"该工资没有添加工资项目");
        } else {
            for (InputWageItemVO a : wage.getWageItem()) {
                if (a.getWageEmployee().length() <= 1 || a.getWageEmployee().length() >= 128) {

                    return JsonResultUtil.errorJson(2, "月份工资为" + wage.getWageMonth() + "的员工ID 应该是1到128个字符");
                }  else if (a.getWageEmployeeDept().length() <= 1 || a.getWageEmployeeDept().length() >= 128) {

                    return JsonResultUtil.errorJson(2, "月份工资为" + wage.getWageMonth() + "的员工科室 应该是1到128个字符 ");
                } else if (a.getWageDetails().size() <= 0) {
                    return JsonResultUtil.errorJson(2, "员工ID为" + a.getWageEmployee()+ "的员工的工资不应该为空");
                }
            }
        }
        return null;
    }
}
