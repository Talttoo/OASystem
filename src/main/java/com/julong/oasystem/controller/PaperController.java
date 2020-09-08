package com.julong.oasystem.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.entity.AnswerVO;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.entity.QuestionVO;
import com.julong.oasystem.entity.view.*;
import com.julong.oasystem.service.AnswerService;
import com.julong.oasystem.service.PaperService;
import com.julong.oasystem.service.QuestionService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

/**
 * @Author Taltoo
 * @Date 2020/6/20 0004 下午 14:52
 * @Description：问卷调查相关
 */
@RestController
@RequestMapping("/paper")
public class PaperController {
    @Autowired @Lazy
    private PaperService paperService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommonUtils commonUtils;
    @Autowired @Lazy
    private PaperMethodHelp paperMethodHelp;

    //获取问卷列表
    @GetMapping("/lists")
    public JSONObject paperLists(HttpServletRequest request) throws ParseException {
        //Map<String, Object> map = new HashMap<>();

//        User u = new User();
//        u.setId("1");
//        request.getSession().setAttribute("admin", u);
//        request.setAttribute("session", request.getSession());

//        Session session = SecurityUtils.getSubject().getSession();
//        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
//        String username = userInfo.getString("username");
//        String userId = userInfo.getString("userId");

        System.out.println("username:"+ JsonResultUtil.getUserName()+"--userId:"+ JsonResultUtil.getUserid());

//        User user = (User) request.getAttribute("admin");
//        String userId = user.getUserid();

        //Cookie[] tokens = request.getCookies();
        JSONObject requestJson = JsonResultUtil.request2Json(request);
        int pageNum = requestJson.getIntValue("pageNum");
        int pageRow = requestJson.getIntValue("pageRow");


        //使用分页插件,核心代码就这一行，页数、每页行数
        Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
        List<PaperVO> list = paperService.queryPaper();
        //List<PaperVO> list = paperService.queryPaperByUserID(JsonResultUtil.getUserid());

        System.out.println("list:"+list.toString());
        ArrayList<PaperQueryView> queryViewlist = new ArrayList<PaperQueryView>();

        JSONArray jsonArray = new JSONArray();

        for (PaperVO p : list) {//遍历list，把Paper转换成PaperQueryView类型
            PaperQueryView paperQueryView = new PaperQueryView();
            paperQueryView.setId(p.getId());
            paperQueryView.setTitle(p.getTitle());
            paperQueryView.setStatus(p.getStatus());
            paperQueryView.setCreateTime(commonUtils.getLongByDate(p.getCreateTime()));
            paperQueryView.setStartTime(commonUtils.getDateStringByDate(p.getStartTime()));
            paperQueryView.setEndTime(commonUtils.getDateStringByDate(p.getEndTime()));
            queryViewlist.add(paperQueryView);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", p.getId());
            jsonObject.put("title", p.getTitle());
            jsonObject.put("status", p.getStatus());
            jsonObject.put("createTime", commonUtils.getLongByDate(p.getCreateTime()));
            jsonObject.put("startTime", commonUtils.getDateStringByDate(p.getStartTime()));
            jsonObject.put("endTime", commonUtils.getDateStringByDate(p.getEndTime()));
            System.out.println("p.getEndTime():"+p.getEndTime());
            System.out.println("commonUtils.getDateStringByDate(p.getEndTime()):"+commonUtils.getDateStringByDate(p.getEndTime()));
            jsonArray.add(jsonObject);

        }
        System.out.println("jsonArray:"+jsonArray.toJSONString());
        //JSONObject jsonObject = new JSONObject();

        //jsonArray.addAll(queryViewlist);

        /*for (int i = 0, n = jsonArray.size(); i < n; i++) {
            System.out.println(jsonArray.getJSONObject(i));
        }*/

//        map.put("code", 0);
//        map.put("msg", "ok");
//        map.put("data", jsonArray);
//        return map;

        return JsonResultUtil.successPage(page.getTotal(),page.getPages(),jsonArray.toJavaList(JSONObject.class));
    }

    //查看问卷
    @PostMapping("/view")
    public JSONObject viewPaper( @RequestBody String id) throws ParseException {
  /*      User u = new User();
        u.setId("1");
        request.getSession().setAttribute("admin", u);
        request.setAttribute("session", request.getSession());*/


        JSONObject resultJson = JSONObject.parseObject(id);

        JSONObject json = JSONObject.parseObject(id);
        id = json.getString("id");


        if (id == null) {
//            map.put("code", 2);
//            map.put("msg", "id 不能为空");
            resultJson =  JsonResultUtil.resultJson(400,"id 不能为空");
        } else {
            PaperVO p = paperService.queryPaperByID(id);
            System.out.println("+++=paper----"+p.toString());
            if (p == null) {
//                map.put("code", 2);
//                map.put("msg", "id 不正确");
                resultJson =  JsonResultUtil.resultJson(400,"id 不正确");
            } else {
//                map.put("code", 0);

                if (p.getStatus()==0){
                    //map.put("msg", "该问卷未发布");
                    resultJson =  JsonResultUtil.resultJson(200,"该问卷未发布");
                }else if (p.getStatus()==2){
//                    map.put("msg", "该问卷已结束");
                    resultJson =  JsonResultUtil.resultJson(200,"该问卷已结束");
                }else if (p.getStatus()==3){
//                    map.put("msg", "无此问卷");
                    resultJson =  JsonResultUtil.resultJson(200,"无此问卷");
                }else if (p.getStatus()==4){
//                    map.put("msg", "已发布但未到开始时间");
                    resultJson =  JsonResultUtil.resultJson(200,"已发布但未到开始时间");
                }

                JSONObject jsonObject = new JSONObject();
                //jsonObject.put("id",id);
                jsonObject.put("id", id);
                jsonObject.put("title", p.getTitle());
                jsonObject.put("status", p.getStatus());
                jsonObject.put("createTime", commonUtils.getLongByDate(p.getCreateTime()));
                jsonObject.put("startTime", commonUtils.getDateStringByDate(p.getStartTime()));
                jsonObject.put("endTime", commonUtils.getDateStringByDate(p.getEndTime()));


                //查出该试卷的问题
                List<QuestionVO> list = questionService.queryQusetionByPaperId(id);

                System.out.println("QuestionVO-list"+list.toString());

                ArrayList<ViewPaperQuestion> viewPaperQuestionArrayList = new ArrayList<>();


                for (QuestionVO q : list) {
                    //转换question，变成ViewPaperQuestion对象
                    ViewPaperQuestion viewPaperQuestion = new ViewPaperQuestion();
                    viewPaperQuestion.setId(q.getId());
                    viewPaperQuestion.setQuestionType(q.getQuestionType());
                    viewPaperQuestion.setQuestionTitle(q.getQuestionTitle());
//                    viewPaperQuestion.setQuestionOption(JSONObject.parseArray(q.getQuestionOption(),String.class));Arrays.asList(strs)
//                    viewPaperQuestion.setQuestionOption(JSONArray.parseArray(q.getQuestionOption()).toJavaList(String.class));

                     String res = JSON.toJSON(q.getQuestionOption()).toString();
                    System.out.println("###--res****"+res);
                    //List<String> departmentList = JSONArray.parseArray(res, String.class);
                    viewPaperQuestion.setQuestionOption(JSONArray.parseArray(res, String.class));

                    System.out.println("###--viewPaperQuestion****"+viewPaperQuestion.toString());
                    viewPaperQuestionArrayList.add(viewPaperQuestion);
                }
                JSONArray jsonArray = new JSONArray();
                jsonArray.addAll(viewPaperQuestionArrayList);

                jsonObject.put("questions", viewPaperQuestionArrayList);

                //return JsonResultUtil.successJson(jsonObject);

                System.out.println("questions,jsonObject:"+jsonObject.toJSONString());
                resultJson.put("info", jsonObject);
            }

        }
        return resultJson;
    }

    /**
     * // 增加问卷
     *
     * @ResponseBody
     * @RequestMapping(value = "/api/v1/add-paper", method = RequestMethod.POST)
     * public Map<String, Object> addPaper(HttpServletRequest request, @RequestBody AddPaperViewPaper paper) throws ParseException {
     * <p>
     * <p>
     * User user = (User) request.getAttribute("admin");
     * <p>
     * //no need id for add new paper
     * String id = null;
     * PaperMethodHelp paperMethodHelp = new PaperMethodHelp();
     * //return updatePaper((request,  paper));
     * return paperMethodHelp.insertPaper(paper, user.getId(), id);
     * <p>
     * }
     */
    //修改问卷 & 新增问卷
    @PostMapping("/update")
    public JSONObject updatePaper( @RequestBody UpdatePaperViewPaper paper) throws ParseException {
        //HttpServletRequest request,
        System.out.println("新增问卷....");

//        User u = new User();
//        u.setId("1");
//        request.getSession().setAttribute("admin", u);
//        request.setAttribute("session", request.getSession());

//        Map<String, Object> map = new HashMap<>();
      //  UpdatePaperViewPaper paper = (UpdatePaperViewPaper)JSONObject.parseObject(object.toJSONString(),UpdatePaperViewPaper.class);
        //转换addPaper
        AddPaperViewPaper addPaperViewPaper = new AddPaperViewPaper();
        addPaperViewPaper.setTitle(paper.getTitle());
        addPaperViewPaper.setEndTime(paper.getEndTime());
        addPaperViewPaper.setQuestions(paper.getQuestions());
        addPaperViewPaper.setStartTime(paper.getStartTime());
        addPaperViewPaper.setStatus(paper.getStatus());
        JSONObject resultJson = new JSONObject();
        //添加Paper
        if (paper.getId() == null) {
            //插入代码
//            resultJson = paperMethodHelp.insertPaper(addPaperViewPaper, JsonResultUtil.getUserid(), null);
//            if ((int) resultJson.getIntValue("code") == 200) {
//                map.put("data", 0);
//            }
            return paperMethodHelp.insertPaper(addPaperViewPaper, JsonResultUtil.getUserid(), null);
        } else {//更新

            if (JsonResultUtil.getUserid() != null) {
                //通过检测，准备添加数据
                if (paperService.updatePaperQuestions(paper, JsonResultUtil.getUserid(), addPaperViewPaper)) {
//                    map.put("code", 0);
//                    map.put("msg", "ok");
//                    map.put("data", 0);
//                    return map;
                    return JsonResultUtil.successJson();
                }
            }
        }
//        map.put("code", 1);
//        map.put("msg", "系统异常");
//        return map;
        return JsonResultUtil.errorJson(1,"系统异常");
    }

    ///delete-paper
    @PostMapping("/delete")
    public JSONObject deletePaper(@RequestBody JSONObject idList) {

        System.out.println("idList:"+idList.toString());
        List<String> listId = new ArrayList<>();
        JSONObject j = JSON.parseObject(idList.toString());

        JSONArray array = JSON.parseArray(String.valueOf(j.get("idList")));

        for (int i = 0; i < array.size(); i++) {
            listId.add((String) array.get(i));
            //System.out.println(array.get(i));
        }

        //System.out.println(j);

        //System.out.println(idList);

        /*User u = new User();
        u.setId("1");
        request.getSession().setAttribute("admin", u);
        request.setAttribute("session", request.getSession());*/

        //JSONObject json = JSONObject.fromObject(idList);

        //Collections.addAll(listId,array);

        Map<String, Object> map = new HashMap<>();
        if (listId.size() <= 0) {
//            map.put("code", 2);
//            map.put("msg", "要删除试卷的id不能为空");
//            map.put("data", 2);
//            return map;
            return JsonResultUtil.errorJson(2,"要删除试卷的id不能为空");
        } else {
            if (paperService.deleteManyPaper(listId)) {
//                map.put("code", 0);
//                map.put("msg", "ok");
//                map.put("data", 0);
//                return map;
                return JsonResultUtil.successJson();
            } else {
//                map.put("code", 1);
//                map.put("msg", "系统异常");
//                map.put("data", 1);
//                return map;
                return JsonResultUtil.errorJson(1,"系统异常");
            }
        }
    }

    //查看问卷数据
    @PostMapping( "/paperData")
    public JSONObject dataPaper(@RequestBody String id) throws ParseException {
        JSONObject json = JSON.parseObject(id);
        id = json.getString("id");
        System.out.println("---------------" + id + "---------------------");
        Map<String, Object> map = new HashMap<>();
        if (id == null) {
//            map.put("code", 2);
////            map.put("msg", "要查看data试卷的id不能为空");
////            return map;
            return JsonResultUtil.errorJson(2,"要查看data试卷的id不能为空");
        } else {
            if (paperService.dataPaper(id) == null) {
//                map.put("code", 2);
//                map.put("msg", "要查看data试卷的id无效");
//                return map;
                return JsonResultUtil.errorJson(2,"要查看data试卷的id无效");
            } else {
                //DataPaperViewPaper dataPaperViewPaper = (DataPaperViewPaper) paperService.dataPaper(id);
                JSONObject jsonObject = (JSONObject) paperService.dataPaper(id);
//                map.put("code", 0);
////                map.put("msg", "ok");
////                map.put("data", jsonObject);
////                return map;
                return JsonResultUtil.successJson(jsonObject);
            }
        }

    }



    /**
     * 用户答卷
     *
     * @param id
     * @return
     */
    @GetMapping("/user-view")
    public JSONObject userViewPaper(@RequestParam("id") String id) throws ParseException {
        System.out.println("用户答题："+id);
        //Map<String, Object> map = new HashMap<>();
        JSONObject result = new JSONObject();
        if (id == null) {
//            map.put("code", 2);
//            map.put("msg", "问卷id不能为空");
            result = JsonResultUtil.resultJson(400,"问卷id不能为空");
        } else {
            PaperVO p = paperService.queryPaperByID(id);
            if (p == null) {
//                map.put("code", 2);
//                map.put("msg", "问卷id无效");
                result = JsonResultUtil.resultJson(400,"问卷id无效");
            } else {
                //check time for update the status
                if (p.getStatus() == 0 || p.getStatus() == 1) {
                    if (new Date().after(p.getEndTime())) { //need be over
                        p.setStatus(2);
                        if (paperService.updatePaper(p)) {

                        }
                        //p.setStatus(2);
                    } else if (new Date().after(p.getStartTime())) {    //need be start
                        p.setStatus(1);
                        if (paperService.updatePaper(p)) {

                        }
                        //p.setStatus(1);
                    }
                }
                int status = p.getStatus();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("status", status);
                if (status == 0) {  // not start
//                    map.put("code", 0);
//                    map.put("msg", "该问卷还未开始");
                    result = JsonResultUtil.resultJson(200,"该问卷还未开始");
                } else if (status == 1) {   //ing
                    if (new Date().before(p.getStartTime())) {//已经发布，但是未到开始时间
//                        map.put("code", 0);
//                        map.put("msg", "该问卷还未开始");
                        result = JsonResultUtil.resultJson(200,"该问卷还未开始");

                        jsonObject.put("status", 4);
                        jsonObject.put("title", p.getTitle());
                        jsonObject.put("startTime", commonUtils.getDateStringByDate(p.getStartTime()));
                        jsonObject.put("endTime", commonUtils.getDateStringByDate(p.getEndTime()));
                        result.put("info", jsonObject);

                        return result;
                    }
//                    map.put("code", 0);
//                    map.put("msg", "ok");
                    result = JsonResultUtil.resultJson(200,"ok");

                    jsonObject.put("id", id);
                    jsonObject.put("title", p.getTitle());
                    jsonObject.put("createTime", commonUtils.getLongByDate(p.getCreateTime()));
                    jsonObject.put("startTime", commonUtils.getDateStringByDate(p.getStartTime()));
                    jsonObject.put("endTime", commonUtils.getDateStringByDate(p.getEndTime()));

                    //查出该试卷的问题
                    List<QuestionVO> list = questionService.queryQusetionByPaperId(id);

                    ArrayList<ViewPaperQuestion> viewPaperQuestionArrayList = new ArrayList<>();

                    for (QuestionVO q : list) {
                        //转换question，变成ViewPaperQuestion对象
                        ViewPaperQuestion viewPaperQuestion = new ViewPaperQuestion();
                        viewPaperQuestion.setId(q.getId());
                        viewPaperQuestion.setQuestionType(q.getQuestionType());
                        viewPaperQuestion.setQuestionTitle(q.getQuestionTitle());
                        viewPaperQuestion.setQuestionOption(JSONArray.parseArray(q.getQuestionOption(),String.class));
                        viewPaperQuestionArrayList.add(viewPaperQuestion);
                    }
                    JSONArray jsonArray = new JSONArray();
                    jsonArray.addAll(viewPaperQuestionArrayList);
                    jsonObject.put("questions", viewPaperQuestionArrayList);

                } else if (status == 2) {   //expired
//                    map.put("code", 0);
//                    map.put("msg", "问卷已结束");

                    result = JsonResultUtil.resultJson(200,"问卷已结束");
                    jsonObject.put("title", p.getTitle());
                    jsonObject.put("startTime", commonUtils.getDateStringByDate(p.getStartTime()));
                    jsonObject.put("endTime", commonUtils.getDateStringByDate(p.getEndTime()));
                } else if (status == 3) {   //deleted
//                    map.put("code", 0);
//                    map.put("msg", "该问卷已被删除");
                    result = JsonResultUtil.resultJson(200,"该问卷已被删除");
                } else {  //unknown status
//                    map.put("code", 0);
////                    map.put("msg", "未知的问卷状态");
                    result = JsonResultUtil.resultJson(200,"未知的问卷状态");
                }
                result.put("info", jsonObject);
            }

        }
        return result;

    }

    /**
     * 用户提交问卷答案
     *
     * @param answer
     * @return
     */
    @PostMapping("/commit-paper")
    public JSONObject userViewPaper(@Valid @RequestBody PaperAnswer answer, BindingResult result) {
        System.out.println("提交答卷.....");
        Map<String, Object> map = new HashMap<>();
        JSONObject resultjson = new JSONObject();
        if (result.hasErrors()) {
            FieldError error = result.getFieldErrors().get(0);//获得第第一个错误
//            map.put("msg", error.getDefaultMessage());//将错误信息放入msg
////            map.put("code", 2);
            return JsonResultUtil.errorJson(400,error.getDefaultMessage());
        }
        String paperId = answer.getId();
        PaperVO paperVO = paperService.queryPaperByID(paperId);
        if (paperVO != null) {
            List<AnswerVO> ansList = new ArrayList<>();
            for (QuestionAnswer qa : answer.getAnswers()) {
                AnswerVO ans = new AnswerVO();
                ans.setId(commonUtils.getUUID());
                ans.setPaperId(paperId);
                ans.setQuestionId(qa.getId());
                ans.setQuestionType(qa.getQuestionType());
                ans.setCreateTime(new Date());
                JSONArray array = qa.getAnswerContent();
                ans.setAnswerContent(array.toString());
                ansList.add(ans);
            }
            if (answerService.insertAnswerList(ansList)) {
//                map.put("code", 0);
//                map.put("msg", "ok");
                resultjson = JsonResultUtil.resultJson(200,"ok");
            }
        } else {
//            map.put("code", 2);
//            map.put("msg", "问卷id无效");
            resultjson = JsonResultUtil.resultJson(400,"问卷id无效");
        }
        return resultjson;
    }




}