package com.samiu.host.model.repository.wan

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.wan.BaseWanRepository
import com.samiu.host.model.http.wan.WanClient
import com.samiu.host.model.http.wan.WanResult

/**
 * @author Samiu 2020/3/5
 */
class WanProjectRepository : BaseWanRepository() {

    suspend fun getRecentProjects(page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getLastedProject(page))
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getProjectType(): WanResult<List<SystemParent>> {
        return readyCall(
            call = {
                call(WanClient.service.getProjectType())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getAllProjects(page: Int, cid: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getProjectTypeDetail(page, cid))
            }, errorMessage = NETWORK_ERROR
        )
    }
}