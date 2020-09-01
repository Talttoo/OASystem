package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.view.AddPaperViewPaper;
import com.julong.oasystem.entity.view.PaperMethodHelp;
import com.julong.oasystem.utils.FileConvertToEntity;
import com.julong.oasystem.utils.FileUtil;
import com.julong.oasystem.utils.JsonResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Taltoo
 * @Date 2020/6/10 0004 下午 14:52
 * @Description：上传文件
 */
@Slf4j
@RestController
@RequestMapping("/quesExcel")
public class UploadPaperController {
    @Autowired
    private FileConvertToEntity convert;

    @Autowired @Lazy
    private PaperMethodHelp paperMethodHelp;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {

        System.out.println("上传文件.....");
        Map<String, Object> map = new HashMap<>();

//        User user = new User();
//        user.setId("1");

       // User user = (User) request.getAttribute("admin");

        if (JsonResultUtil.getUserid() == null) {
            map.put("code", -1);
            map.put("msg", "账号未登录或登录已经失效");
            return map;
        }
        if (file.isEmpty()) {
            map.put("code", 2);
            map.put("msg", "未选择文件！");
            return map;
        }
        if (!file.getContentType().equalsIgnoreCase("application/vnd.ms-excel") ||
                !file.getOriginalFilename().endsWith(".xls")) {
            map.put("code", 2);
            map.put("msg", "文件类型不支持！");
            return map;
        }
        if (file.getSize() > 100 * 1024) {  //file cannot large than 100KB
            map.put("code", 2);
            map.put("msg", "文件大小限制在100KB以内！");
            return map;
        }

        AddPaperViewPaper paper = convert.convert(file);
        if (paper != null) {
           // map = paperMethodHelp.insertPaper(paper, user.getUserid(), null);
            map = paperMethodHelp.insertPaper(paper, JsonResultUtil.getUserid(), null);
        }else{
            map.put("code", 2);
            map.put("msg", "文件转换失败，请注意格式要求！");
        }

        return map;
    }

    @GetMapping(value = "/download",produces = "application/octet-stream")
    public void downLoadExcel(HttpServletResponse response){
        //模板名称
        String fileName = "补录模板.xlsx";
       JSONObject result = FileUtil.downloadFile(response, fileName);
        int  code = result.getIntValue("code");
        if(code == 200 ){
            response.setStatus(200);
            log.info("补录模板下载成功");
        } else {
            response.setStatus(500);
            log.error(result.getString("msg"));
        }
    }

}
