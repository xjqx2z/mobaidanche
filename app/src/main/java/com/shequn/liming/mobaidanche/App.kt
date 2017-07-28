package com.shequn.liming.mobaidanche

import android.app.Application
import android.content.Context
import com.baidu.location.LocationClient
import com.baidu.mapapi.SDKInitializer
import com.shequn.liming.mobaidanche.api.bean.User
import com.shequn.liming.mobaidanche.utils.save
import com.shequn.liming.shiji_newproject.utils.JsonUtils



/**
 * Created by Liming on 2017/7/27.
 */
class App: Application() {

    companion object {
        lateinit var instance: App
        lateinit var mLocationClient: LocationClient
    }

    init {
        instance = this
        mLocationClient = LocationClient(this)
    }


    private var user: User? = null

    override fun onCreate() {
        super.onCreate()
        SDKInitializer.initialize(this)
    }


    fun setUser(user: User?) {
        this.user = user
        val jsonStr = JsonUtils.toJson(user)
        val sp = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        sp.save( "user_login", jsonStr!!)
    }

    fun getUser():User? {
        return this.user
    }


    fun getmLocationClient()  :LocationClient{

        return mLocationClient
    }



}