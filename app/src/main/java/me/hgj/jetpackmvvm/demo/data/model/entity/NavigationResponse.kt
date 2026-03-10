package me.hgj.jetpackmvvm.demo.data.model.entity

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * 导航数据
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class NavigationResponse(var articles: ArrayList<ArticleResponse>,
                              var cid: Int,
                              var name: String) : Parcelable
