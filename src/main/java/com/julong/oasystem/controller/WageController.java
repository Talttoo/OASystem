package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.wage.ViewWageDetail;
import com.julong.oasystem.entity.wage.WageItemVO;
import com.julong.oasystem.entity.wage.WageVO;
import com.julong.oasystem.service.WageItemService;
import com.julong.oasystem.service.WageService;
import com.julong.oasystem.utils.CommonUtils;
import com.julong.oasystem.utils.JsonResultUtil;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Taltoo
 * @Date 2020/8/23 0023 下午 15:58
 * @Description：
 */
@RestController
@RequestMapping("wage")
public class WageController {

    @Autowired
    WageService wageService;

    @Autowired
    WageItemService wageItemService;


    @Autowired
    private CommonUtils commonUtils;

    @GetMapping("/list")
    public JSONObject listWage(HttpServletRequest request) {
        return wageService.queryWage(JsonResultUtil.request2Json(request));
    }

    //查看工资详情
    @PostMapping("/view")
    public JSONObject viewWage( @RequestBody String id) throws ParseException {

        JSONObject resultJson = new JSONObject();

        JSONObject json = JSONObject.parseObject(id);
        id = json.getString("id");


        if (id == null) {
            resultJson =  JsonResultUtil.resultJson(400,"id 不能为空");
        } else {
            WageVO wage = wageService.queryWageByID(id);
            //System.out.println("+++=wage----"+wage.toString());
            if (wage == null) {
                resultJson =  JsonResultUtil.resultJson(400,"id 不正确");
            } else {
                if (wage.getStatus()==1){
                    resultJson =  JsonResultUtil.resultJson(200,"该工资记录已删除");
                }
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", id);
                jsonObject.put("wageMonth", wage.getWageMonth());
                jsonObject.put("status", wage.getStatus());
                jsonObject.put("createTime", commonUtils.getLongByDate(wage.getCreateTime()));
                jsonObject.put("wageColumn", JSONArray.parseArray(JSON.toJSON(wage.getWageColumn()).toString(), String.class) );


                //查出该工资所有记录
                List<WageItemVO> list = wageItemService.queryWageItemBywageId(id);

                System.out.println("WageItemVO-list"+list.toString());

//                ArrayList<ViewWageDetail> viewWageDetailArrayList = new ArrayList<>();
                ArrayList<List<String>> wageDetailList = new ArrayList<>();

//                for (WageItemVO w : list) {
//                    //转换wageitem，变成ViewWageDetail对象
//                    ViewWageDetail viewWageDetail = new ViewWageDetail();
//                    viewWageDetail.setId(w.getId());
//                    viewWageDetail.setWageEmployee(w.getWageEmployee());
//                    viewWageDetail.setWageEmployeeDept(w.getWageEmployeeDept());
//                    viewWageDetail.setWageDetails(JSONArray.parseArray(JSON.toJSONString(w.getWageDetails()), String.class));
//
//                    System.out.println("###--viewWageDetail****"+viewWageDetail.toString());
//                    viewWageDetailArrayList.add(viewWageDetail);
//                }

                for (WageItemVO w : list) {
                    List<String> wageitem = new ArrayList<>();
                    wageitem.add(w.getWageEmployee());
                    wageitem.add(w.getWageEmployeeDept());
                    wageitem.addAll(JSONArray.parseArray(JSON.toJSON(w.getWageDetails()).toString(), String.class));

                    System.out.println("###--wageitem****"+wageitem.toString());
                    wageDetailList.add(wageitem);
                }

                JSONArray jsonArray = new JSONArray();
                jsonArray.addAll(wageDetailList);

                jsonObject.put("wageDetail", wageDetailList);

                //return JsonResultUtil.successJson(jsonObject);

                resultJson.put("code", 200);
                resultJson.put("msg", "success");
                resultJson.put("info", jsonObject);
            }

        }
       //System.out.println("resultJson:"+resultJson.toJSONString());
        return resultJson;
    }

    //查看工资详情
    @GetMapping("/myWage")
    public JSONObject myWage( HttpServletRequest request) throws ParseException {

        JSONObject jsonParam = JsonResultUtil.request2Json(request);

        if (jsonParam.getString("id") == null) {
            return  JsonResultUtil.resultJson(400,"id 不能为空");
        } else {
            //查出该工资所有记录
            return  wageItemService.queryWageItemByUser(jsonParam);
        }
    }

    //查看工资详情
    @PostMapping("/myWageDetail")
    public JSONObject viewMyWage( @RequestBody JSONObject jsonParam) throws ParseException {

       // System.out.println("个人工资。。。。。。。。。。。。。。。。。。。。");

        JSONObject resultJson = new JSONObject();

        if (jsonParam.getString("wageid") == null) {
            resultJson =  JsonResultUtil.resultJson(400,"id 不能为空");
        } else {
            WageVO wage = wageService.queryWageByID(jsonParam.getString("wageid"));
            //System.out.println("+++=wage----"+wage.toString());
            if (wage == null) {
                resultJson =  JsonResultUtil.resultJson(400,"id 不正确");
            } else {
                if (wage.getStatus()==1){
                    resultJson =  JsonResultUtil.resultJson(200,"该工资记录已删除");
                }
                JSONObject jsonObject = new JSONObject();

                jsonObject.put("id", jsonParam.getString("wageid"));
                jsonObject.put("wageMonth", wage.getWageMonth());
                jsonObject.put("status", wage.getStatus());
                jsonObject.put("createTime", commonUtils.getLongByDate(wage.getCreateTime()));
                jsonObject.put("wageColumn", JSONArray.parseArray(JSON.toJSON(wage.getWageColumn()).toString(), String.class) );


                //查出该工资所有记录
                List<WageItemVO> list = wageItemService.queryWageItemBywageIdAndUser(jsonParam);

                //System.out.println("WageItemVO-list"+list.toString());

//                ArrayList<ViewWageDetail> viewWageDetailArrayList = new ArrayList<>();
                ArrayList<List<String>> wageDetailList = new ArrayList<>();


                for (WageItemVO w : list) {
                    List<String> wageitem = new ArrayList<>();
                    wageitem.add(w.getWageEmployee());
                    wageitem.add(w.getWageEmployeeDept());
                    wageitem.addAll(JSONArray.parseArray(JSON.toJSON(w.getWageDetails()).toString(), String.class));

                    //System.out.println("###--wageitem****"+wageitem.toString());
                    wageDetailList.add(wageitem);
                }

                JSONArray jsonArray = new JSONArray();
                jsonArray.addAll(wageDetailList);

                jsonObject.put("wageDetail", wageDetailList);

                //return JsonResultUtil.successJson(jsonObject);

                resultJson.put("code", 200);
                resultJson.put("msg", "success");
                resultJson.put("info", jsonObject);
            }

        }
        System.out.println("resultJson:"+resultJson.toJSONString());
        return resultJson;
    }

    @PostMapping("/delete")
    public JSONObject deleteWage(@RequestBody JSONObject idList) {
        System.out.println("idList:"+idList.toString());
        List<String> listId = new ArrayList<>();
        JSONObject j = JSON.parseObject(idList.toString());

        JSONArray array = JSON.parseArray(String.valueOf(j.get("idList")));

        for (int i = 0; i < array.size(); i++) {
            listId.add((String) array.get(i));
            //System.out.println(array.get(i));
        }
        Map<String, Object> map = new HashMap<>();
        if (listId.size() <= 0) {

            return JsonResultUtil.errorJson(2,"要删除工资的id不能为空");
        } else {
            if (wageService.deleteManyWage(listId)) {
                return JsonResultUtil.successJson();
            } else {
                return JsonResultUtil.errorJson(1,"系统异常");
            }
        }
    }



}
