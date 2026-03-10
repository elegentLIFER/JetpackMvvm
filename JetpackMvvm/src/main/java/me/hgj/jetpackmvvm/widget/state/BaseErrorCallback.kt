package me.hgj.jetpackmvvm.widget.state

import me.hgj.jetpackmvvm.R
import me.hgj.jetpackmvvm.widget.loadsir.callback.Callback

class BaseErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_base_error
    }
}