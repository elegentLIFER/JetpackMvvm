package me.hgj.jetpackmvvm.demo.ui.fragment.tree

import androidx.lifecycle.LiveData
import me.hgj.jetpackmvvm.core.data.ApiResult
import me.hgj.jetpackmvvm.demo.app.core.base.BaseArticleListFragment
import me.hgj.jetpackmvvm.demo.data.model.entity.ApiPagerResponse
import me.hgj.jetpackmvvm.demo.data.model.entity.ArticleResponse
import me.hgj.jetpackmvvm.demo.data.vm.TreeViewModel

/**
 * 描述　: 每日一问
 */
class AskFragment : BaseArticleListFragment<TreeViewModel, ArticleResponse>() {

    override fun provideRequest(
        isRefresh: Boolean,
        loadingXml: Boolean
    ): LiveData<ApiResult<ApiPagerResponse<ArticleResponse>>> {
        return mViewModel.getAskData(isRefresh, loadingXml)
    }

}