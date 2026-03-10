package me.hgj.jetpackmvvm.demo.app.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.drake.brv.utils.bindingAdapter
import me.hgj.jetpackmvvm.base.vm.BaseViewModel
import me.hgj.jetpackmvvm.core.data.ApiResult
import me.hgj.jetpackmvvm.core.data.obs
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.demo.databinding.IncludeRecyclerviewBinding
import me.hgj.jetpackmvvm.ext.util.refresh
import me.hgj.jetpackmvvm.ext.util.loadListError
import me.hgj.jetpackmvvm.ext.util.loadListSuccess

/**
 * 说明　：通用不带分页列表（下拉刷新，没有数据展示为空布局，请求失败展示为错误布局，点击可布局可重新请求）
 * ，写列表的时候发现全特么是重复代码，所以这里直接封装了一个统一的列表，子类只要关心请求数据，和绑定对应的adapter就行了
 */
abstract class BaseListActivity<VM : BaseViewModel, VB: ViewBinding,T> : BaseActivity<VM, VB>() {

    // 子类布局中 include 的 Binding，框架自动查找或手动绑定
    private lateinit var includeBinding: IncludeRecyclerviewBinding
    // recyclerView
    protected val rv: RecyclerView get() = includeBinding.rv
    // smartRefreshLayout
    protected val refresh get() = includeBinding.refresh

    override fun initView(savedInstanceState: Bundle?) {
        // 看子类中有没有传递分页布局，为空就抛出异常
        bindIncludeList()?.let {
            includeBinding = IncludeRecyclerviewBinding.bind(it)
        } ?: throw IllegalArgumentException("请调用 bindIncludeList() 绑定分页布局 ")
        refresh.refresh {
            getList(true)
        }.setEnableLoadMore(false)
        setupAdapter(rv)
        onLoadRetry()
    }


    /**
     * 传入指定的IncludeRecyclerviewBinding 分页布局
     */
    abstract fun bindIncludeList(): View?

    override fun onLoadRetry() {
        super.onLoadRetry()
        getList(isRefresh = true,loadingXml = true)
    }

    private fun getList(isRefresh: Boolean = true, loadingXml: Boolean = false) {
        provideRequest(isRefresh, loadingXml).obs(this)  {
            onSuccess {
                loadListSuccess(it,rv.bindingAdapter,refresh,this@BaseListActivity)
            }
            onError {
                loadListError(it,refresh)
            }
        }
    }

    /**
     * 子类需要提供的请求逻辑
     */
    abstract fun provideRequest(isRefresh: Boolean, loadingXml: Boolean): LiveData<ApiResult<ArrayList<T>>>

    /**
     * 子类配置 Adapter
     */
    abstract fun setupAdapter(rv: RecyclerView)

}