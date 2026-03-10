package me.hgj.jetpackmvvm.core.init

import android.app.Application

/**
 * 初始化任务接口
 */
interface InitTask {
    /** 任务名称  */
    val name: String
    /** 是否运行在主线程 */
    val runOnMainThread: Boolean
    /** 是否阻塞冷启动（关键任务） */
    val isBlocking: Boolean
    suspend fun init(app: Application)
}