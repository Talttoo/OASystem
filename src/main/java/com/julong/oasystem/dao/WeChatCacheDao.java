package com.julong.oasystem.dao;

import com.alibaba.fastjson.JSONObject;

/** @version: 1.0 
* @Description: （对类进行功能描述） 
* @author:
* @date:
*/
public interface WeChatCacheDao {
	/**
     *
     * @param jsonObject
     * @return
     */
	JSONObject getWeChatCache(JSONObject jsonObject);

    /**
     *
     *
     * @param jsonObject
     * @return
     */
    int deleteWeChatCache(JSONObject jsonObject);

    /**
     *
     *
     * @param jsonObject
     * @return
     */
    int insertWeChatCache(JSONObject jsonObject);
}
