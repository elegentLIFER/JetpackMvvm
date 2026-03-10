package me.hgj.jetpackmvvm.ext.util

import me.hgj.jetpackmvvm.core.net.AppException
import me.hgj.jetpackmvvm.core.net.ExceptionHandle

val Throwable.code: String
    get() {
        val errorCode = when (this) {
            is AppException -> this.errCode     // 业务code异常
            else -> "-1"
        }
        return errorCode
    }

val Throwable.msg: String
    get() {
        return ExceptionHandle.handleException(this).errorMsg
    }


