package me.hgj.jetpackmvvm.demo.app.core.util

import androidx.appcompat.app.AppCompatDelegate
import me.hgj.jetpackmvvm.demo.data.model.CacheConfig

object ThemeUtil {
    fun changeTheme(){
        when (CacheConfig.themeModel) {
            0 -> {
                //跟随系统
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
            1 -> {
                //浅色
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            2 -> {
                //深色
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
    }
}