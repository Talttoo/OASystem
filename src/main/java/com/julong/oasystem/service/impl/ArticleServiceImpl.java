package com.julong.oasystem.service.impl;

import com.alibaba.fastjson.JSONObject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.julong.oasystem.dao.ArticleDao;
import com.julong.oasystem.entity.ArticleVO;
import com.julong.oasystem.entity.PaperVO;
import com.julong.oasystem.service.ArticleService;
import com.julong.oasystem.utils.JsonResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author Taltoo
 * @Date 2020/6/1 0004 下午 14:52
 * @Description：文章相关
 */
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleDao articleDao;

	/**
	 * 新增文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject addArticle(JSONObject jsonObject) {
		int res = articleDao.addArticle(jsonObject);
		if(res>0){
			return JsonResultUtil.successJson();
		}else {
			return JsonResultUtil.errorJson(401,"插入数据异常");
		}
	}

	/**
	 * 文章列表
	 */
	@Override
	public JSONObject listArticle(JSONObject jsonObject) {
		System.out.println("请求文章列表："+jsonObject.toJSONString());
		JsonResultUtil.fillPageParam(jsonObject);

//		int count = articleDao.countArticle(jsonObject);
//		System.out.println("文章列表数："+count);
		int pageNum = jsonObject.getIntValue("pageNum");
		int pageRow = jsonObject.getIntValue("pageRow");
		Page<PaperVO> page = PageHelper.startPage(pageNum, pageRow);
		List<ArticleVO> list = articleDao.listArticle(jsonObject);
		System.out.println("文章列表："+list.toString());
//		JSONArray jsonArray = JSON.parseArray(list.toString());
//		List<JSONObject> jsonlist  = JSON.parseArray(jsonArray.toJSONString(),JSONObject.class) ;
		return JsonResultUtil.successPageArticle(jsonObject, list, (int)page.getTotal());
	}

	/**
	 * 首页文章列表
	 * @param jsonObject
	 * @return
	 */
	@Override
	public JSONObject newListArticle(JSONObject jsonObject) {
		List<ArticleVO> list = articleDao.newListArticle(jsonObject);
		return JsonResultUtil.successJson(list);
	}

	@Override
	public JSONObject selectArticleById(String id) {

		ArticleVO article =  articleDao.selectArticleById(id);
		return JsonResultUtil.successJson(article);
	}

	/**
	 * 更新文章
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public JSONObject updateArticle(JSONObject jsonObject) {
		int res = articleDao.updateArticle(jsonObject);
		if(res>0){
			return JsonResultUtil.successJson();
		}else {
			return JsonResultUtil.errorJson(401,"更新数据异常");
		}
	}

	@Override
	public JSONObject deleteArticle(JSONObject id) {
		int res = articleDao.deleteArticle(id);
		if(res>0){
			return JsonResultUtil.successJson();
		}else {
			return JsonResultUtil.errorJson(401,"删除数据异常");
		}
	}

	/**
	 * 文章分类列表
	 */
	@Override
	public JSONObject listType(JSONObject jsonObject){

		 return JsonResultUtil.successJson(articleDao.listType(jsonObject));
	}
}
