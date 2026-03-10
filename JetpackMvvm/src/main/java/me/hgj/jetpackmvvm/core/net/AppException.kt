package me.hgj.jetpackmvvm.core.net

class AppException : Exception {
    var errorMsg: String //错误消息
    var errCode: String = "" //错误码

    constructor(errCode: String, error: String?) : super(error) {
        this.errorMsg = error ?: "请求失败，请稍后再试"
        this.errCode = errCode
    }

    constructor(error: Error, e: Throwable?) {
        errCode = error.getKey().toString()
        errorMsg = error.getValue()
    }
}