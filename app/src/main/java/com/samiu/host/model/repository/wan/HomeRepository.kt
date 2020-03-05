package com.samiu.host.model.repository.wan

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient
import com.samiu.host.model.http.WanResult
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.Banner

/**
 * @author Samiu 2020/3/3
 */
class HomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> {
        return readyCall(
            call = {
                call(WanClient.service.getBanner())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getHomeArticles(page))
            }, errorMessage = NETWORK_ERROR
        )
    }
}