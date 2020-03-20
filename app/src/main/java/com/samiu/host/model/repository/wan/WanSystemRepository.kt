package com.samiu.host.model.repository.wan

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.wan.BaseWanRepository
import com.samiu.host.model.http.wan.WanClient
import com.samiu.host.model.http.wan.WanResult

/**
 * @author Samiu 2020/3/6
 */
class WanSystemRepository : BaseWanRepository() {

    suspend fun getSystem(): WanResult<List<SystemParent>> {
        return readyCall(
            call = {
                call(WanClient.service.getSystemType())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getSystemDetail(page: Int, cid: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getSystemTypeDetail(page, cid))
            }, errorMessage = NETWORK_ERROR
        )
    }
}