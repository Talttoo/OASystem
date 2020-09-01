package com.julong.oasystem.controller;

import com.julong.oasystem.entity.view.AddPaperViewPaper;
import com.julong.oasystem.entity.view.PaperMethodHelp;
import com.julong.oasystem.entity.wage.InputWageVO;
import com.julong.oasystem.entity.wage.WageMethodHelp;
import com.julong.oasystem.utils.FileConvertToEntity;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Taltoo
 * @Date 2020/8/21 0021 下午 15:50
 * @Description：
 */
@RestController
@RequestMapping("/wageExcel")
public class UploadWageController {
    @Autowired
    private FileConvertToEntity convert;

    @Autowired @Lazy
    private WageMethodHelp wageMethodHelp;

    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws ParseException {

        System.out.println("上传工资文件.....");
        Map<String, Object> map = new HashMap<>();

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
        System.out.println("file:"+file.toString());
        InputWageVO  wage = convert.convert2Wage(file);

        System.out.println("wage:"+wage.toString());
        if (wage != null) {
            map = wageMethodHelp.insertWage(wage, JsonResultUtil.getUserid(), null);
        }else{
            map.put("code", 2);
            map.put("msg", "文件转换失败，请注意格式要求！");
        }

        return map;
    }

}
