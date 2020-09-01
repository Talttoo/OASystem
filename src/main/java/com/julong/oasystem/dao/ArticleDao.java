package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;
import com.julong.oasystem.entity.ArticleVO;

import java.util.List;

/**
 * @author: lyt
 * @description: 文章Dao层
 * @date:
 */
public interface ArticleDao {
	/**
	 * 新增文章
	 */
	int addArticle(JSONObject jsonObject);

	/**
	 * 统计文章总数
	 */
	int countArticle(JSONObject jsonObject);

	/**
	 * 文章列表
	 */
	List<ArticleVO> listArticle(JSONObject jsonObject);

	/**
	 * 首页文章列表
	 */
	List<ArticleVO> newListArticle(JSONObject jsonObject);


	ArticleVO selectArticleById(String id);


	/**
	 * 更新文章
	 */
	int updateArticle(JSONObject jsonObject);

	/**
	 * 文章分类列表
	 * @return
	 */
	List<JSONObject> listType(JSONObject jsonObject);
}
