package me.hgj.jetpackmvvm.demo.data.vm

import me.hgj.jetpackmvvm.base.vm.BaseViewModel
import me.hgj.jetpackmvvm.core.data.request
import me.hgj.jetpackmvvm.core.data.requestFlow
import me.hgj.jetpackmvvm.core.net.LoadingType
import me.hgj.jetpackmvvm.demo.data.repository.request.UserRepository

class UserViewModel : BaseViewModel() {

    fun loginLiveData(username: String, password: String) = request {
        onRequest {
            UserRepository.loginLivedata(username, password).await()
        }
        loadingType = LoadingType.LOADING_DIALOG
        loadingMessage = "正在登录中..."
    }

    fun loginFlow(username: String, password: String) = requestFlow {
        onRequest {
            UserRepository.loginFlow(username, password)
        }
        loadingType = LoadingType.LOADING_DIALOG
        loadingMessage = "正在登录中..."
    }

    fun register(username: String, password: String) = request {
        onRequest {
            //先注册，注册成功后调用登录，登录成功后返回用户信息 。 协程写起来真的太舒服
            UserRepository.register(username, password).await()
            UserRepository.loginLivedata(username, password).await()
        }
        loadingType = LoadingType.LOADING_DIALOG
        loadingMessage = "正在注册中..."
    }

}