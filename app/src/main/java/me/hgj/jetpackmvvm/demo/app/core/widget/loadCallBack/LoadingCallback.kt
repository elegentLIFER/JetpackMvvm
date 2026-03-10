package me.hgj.jetpackmvvm.demo.app.core.widget.loadCallBack

import android.content.Context
import android.view.View
import me.hgj.jetpackmvvm.demo.R
import me.hgj.jetpackmvvm.widget.loadsir.callback.Callback

class LoadingCallback : Callback() {

    override fun onCreateView() = R.layout.layout_loading

    override fun onReloadEvent(context: Context?, view: View?) = true
}