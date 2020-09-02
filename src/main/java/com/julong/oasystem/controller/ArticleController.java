package com.julong.oasystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.service.ArticleService;
import com.julong.oasystem.utils.JsonResultUtil;
import com.julong.oasystem.utils.constants.ErrorEnum;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author Taltoo
 * @Date 2020/6/1 0004 下午 14:52
 * @Description：文章相关
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Value("${prop.upload-folder}")
	private String UPLOAD_FOLDER;

	/**
	 * 查询文章列表
	 */
	@RequiresPermissions("article:list")
	@GetMapping("/listArticle")
	public JSONObject listArticle(HttpServletRequest request) {
		//System.out.println("listArticle");
		return articleService.listArticle(JsonResultUtil.request2Json(request));
	}

	/**
	 * 首页文章列表
	 */
	@RequiresPermissions("article:list")
	@GetMapping("/newListArticle")
	public JSONObject newListArticle(HttpServletRequest request) {
		//System.out.println("listArticle");
		return articleService.newListArticle(JsonResultUtil.request2Json(request));
	}

	@GetMapping("/selectArticleById")
	public  JSONObject selectArticleById(@RequestParam("id") String id){
		return articleService.selectArticleById(id);
	}

	/**
	 * 查询文章分类列表
	 */
	@RequiresPermissions("article:list")
	@GetMapping("/listType")
	public JSONObject listType(HttpServletRequest request) {
		System.out.println("listType");
		return articleService.listType(JsonResultUtil.request2Json(request));
	}

	/**
	 * 新增文章
	 */
	@RequiresPermissions("article:add")
	@PostMapping("/addArticle")
	public JSONObject addArticle(@RequestBody JSONObject requestJson) {
		System.out.println("addArticle:"+requestJson.toJSONString());
		JsonResultUtil.hasAllRequired(requestJson, "content");
		return articleService.addArticle(requestJson);
	}

	/**
	 * 修改文章
	 */
	@RequiresPermissions("article:update")
	@PostMapping("/updateArticle")
	public JSONObject updateArticle(@RequestBody JSONObject requestJson) {
		System.out.println("updateArticle:"+requestJson.toJSONString());
		JsonResultUtil.hasAllRequired(requestJson, "id,content");
		return articleService.updateArticle(requestJson);
	}

	/**
	 * 修改文章
	 */
	@RequiresPermissions("article:delete")
	@PostMapping("/deleteArticle")
	public JSONObject deleteArticle(@RequestBody JSONObject id ) {

		return articleService.deleteArticle(id);
	}


	@PostMapping("/singlefile")
	public JSONObject singleFileUpload(MultipartFile file, String id) {

		System.out.println("上传文件："+file.toString());
		System.out.println("上传文件："+id);


		try {
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			System.out.println("文件路径："+path);
			//如果没有files文件夹，则创建
			if (!Files.isWritable(path)) {
				Files.createDirectories(Paths.get(UPLOAD_FOLDER));
			}
			//文件写入指定路径
			Files.write(path, bytes);
			System.out.println("fileName:"+file.getOriginalFilename());
			//busStopService.updateBusStopimg(id, file.getOriginalFilename());
			return JsonResultUtil.successJson(file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
			return JsonResultUtil.errorJson(ErrorEnum.E_500);
		}
	}
}
