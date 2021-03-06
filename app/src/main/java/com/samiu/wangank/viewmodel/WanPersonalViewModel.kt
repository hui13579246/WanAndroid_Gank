package com.samiu.wangank.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samiu.wangank.global.IS_LOGIN
import com.samiu.wangank.model.bean.Article
import com.samiu.wangank.model.http.WanResult
import com.samiu.wangank.model.repository.WanCollectionRepository
import com.samiu.wangank.model.repository.WanLoginRepository
import com.samiu.wangank.util.Preference
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/5/20
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanPersonalViewModel(
    private val collectionRepository: WanCollectionRepository,
    private val loginRepository: WanLoginRepository
) : ViewModel() {

    val mCollections = MutableLiveData<List<Article>>()
    private var isLogin by Preference(IS_LOGIN, false)

    fun getCollections(page: Int) = viewModelScope.launch {
        val articleList = collectionRepository.getCollections(page)
        if (articleList is WanResult.Success)
            mCollections.value = articleList.data.datas
    }

    fun logout() = viewModelScope.launch {
        loginRepository.logout()
        isLogin = false
    }
}