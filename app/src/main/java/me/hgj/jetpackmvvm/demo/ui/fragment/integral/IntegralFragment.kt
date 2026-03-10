package me.hgj.jetpackmvvm.demo.ui.fragment.integral

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.drake.brv.utils.setup
import me.hgj.jetpackmvvm.core.data.ApiResult
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.demo.app.core.base.BasePageListFragment
import me.hgj.jetpackmvvm.demo.app.core.ext.nav
import me.hgj.jetpackmvvm.demo.data.model.entity.ApiPagerResponse
import me.hgj.jetpackmvvm.demo.data.model.entity.BannerResponse
import me.hgj.jetpackmvvm.demo.data.model.entity.IntegralResponse
import me.hgj.jetpackmvvm.demo.data.vm.IntegralViewModel
import me.hgj.jetpackmvvm.demo.databinding.FragmentIntegralBinding
import me.hgj.jetpackmvvm.demo.databinding.ItemIntegralBinding
import me.hgj.jetpackmvvm.demo.ui.activity.WebActivity
import me.hgj.jetpackmvvm.ext.util.intent.bundle
import me.hgj.jetpackmvvm.ext.util.notNull
import me.hgj.jetpackmvvm.ext.view.divider
import me.hgj.jetpackmvvm.ext.view.gone
import me.hgj.jetpackmvvm.ext.view.vertical
import me.hgj.jetpackmvvm.util.decoration.DividerOrientation

/**
 * 描述　: 积分排行
 */
class IntegralFragment : BasePageListFragment<IntegralViewModel, FragmentIntegralBinding,IntegralResponse>() {

    override val showTitle = true

    override val title = "积分排行"

    private val rank: IntegralResponse? by bundle(null)

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        baseBinding.includeToolbar.toolbar.run {
            inflateMenu(R.menu.integral_menu)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.integral_rules -> {
                        WebActivity.start(banner = BannerResponse(title = "积分规则",url = "https://www.wanandroid.com/blog/show/2653"))
                    }

                    R.id.integral_history -> {
                        nav().navigate(IntegralFragmentDirections.toIntegralHistoryFragment())
                    }
                }
                true
            }
        }
        rank.notNull({
            mBind.integralName.text = it.username
            mBind.integralRank.text = it.rank
            mBind.integralCount.text = it.coinCount
        }, {
            mBind.integralCardview.gone()
        })
    }

    override fun bindIncludeList() = mBind.includeLayout.root

    override fun provideRequest(
        isRefresh: Boolean,
        loadingXml: Boolean
    ): LiveData<ApiResult<ApiPagerResponse<IntegralResponse>>> {
       return mViewModel.getIntegralRankData(isRefresh, loadingXml)
    }

    override fun setupAdapter(rv: RecyclerView) {
        rv.vertical().divider {
            setDivider(8, true)
            includeVisible = true
            orientation = DividerOrientation.VERTICAL
        }.setup {
            addType<IntegralResponse>(R.layout.item_integral)
            onBind {
                val model = getModel<IntegralResponse>()
                val binding = getBinding<ItemIntegralBinding>()
                binding.itemIntegralName.text = model.username
                binding.itemIntegralRank.text = model.rank
                binding.itemIntegralCount.text = model.coinCount
            }
        }
    }

    override fun getLoadingView() = mBind.includeLayout.refresh

}