package com.julong.oasystem.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author Taltoo
 * @Date 2020/6/1 0004 下午 14:52
 * @Description：文章相关
 */
public interface ArticleService {
	/**
	 * 新增文章
	 */
	JSONObject addArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	JSONObject listArticle(JSONObject jsonObject);

	/**
	 * 首页文章列表
	 * @param jsonObject
	 * @return
	 */
	JSONObject newListArticle(JSONObject jsonObject);

	/**
	 * 获取文章详情
	 * @param id
	 * @return
	 */
	JSONObject selectArticleById(String id);

	/**
	 * 更新文章
	 */
	JSONObject updateArticle(JSONObject jsonObject);

	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	JSONObject deleteArticle(JSONObject id);

	/**
	 * 文章分类列表
	 */
	JSONObject listType(JSONObject jsonObject);
}
