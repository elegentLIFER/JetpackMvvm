package me.hgj.jetpackmvvm.core.init

abstract class BaseInitTask : InitTask {
    @Volatile
    var isFinished = false
}
