package me.hgj.jetpackmvvm.demo.data.repository.request

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import me.hgj.jetpackmvvm.demo.app.core.net.NetUrl
import me.hgj.jetpackmvvm.demo.data.model.entity.UserInfo
import rxhttp.tryAwait
import rxhttp.wrapper.coroutines.Await
import rxhttp.wrapper.coroutines.CallFlow
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toAwaitResponse
import rxhttp.wrapper.param.toFlowResponse

/**
 * 描述　: 用户数据
 */
object UserRepository {

    /**
     * 登录 livedata版本
     */
    fun loginLivedata(userName: String, password: String): Await<UserInfo> {
        return RxHttp.postForm(NetUrl.User.LOGIN)
            .add("username", userName)
            .add("password", password)
            .toAwaitResponse()
    }

    /**
     * 登录 flow 版本
     */
    fun loginFlow(userName: String, password: String): CallFlow<UserInfo> {
        return RxHttp.postForm(NetUrl.User.LOGIN)
            .add("username", userName)
            .add("password", password)
            .toFlowResponse<UserInfo>()
    }

    fun loginFlow2(userName: String, password: String) = flow {
        //简单测试一个轮询，切记：多结果发送，一定要try catch，不然会将整个flow流都中断了
        while (true) {
            val data = RxHttp.postForm(NetUrl.User.LOGIN)
                .add("username", userName)
                .add("password", password)
                .toAwaitResponse<UserInfo>().tryAwait() //rxhttp提供的错误处理，请求失败就返回null
            if (data != null) {
                emit(data)
            }
            delay(5000)
        }
    }


    /**
     * 注册
     */
    fun register(userName: String, password: String): Await<Any> {
        return RxHttp.postForm(NetUrl.User.REGISTER)
            .add("username", userName)
            .add("password", password)
            .add("repassword", password)
            .toAwaitResponse()
    }

}

