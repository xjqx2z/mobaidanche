package com.shequn.liming.mobaidanche.ui.main

import android.app.Activity
import com.baidu.location.BDLocation
import com.shequn.liming.mobaidanche.App
import com.shequn.liming.mobaidanche.utils.Utils

/**
 * Created by Liming on 2017/7/27.
 */
class MainPresenter(override var view: MainContract.IMainView?) : MainContract.IMainPresenter {


    override var model: MainContract.IMainModel = MainModel(this)


    override fun location() {
        val activity: Activity = view as Activity
        Utils.checkLocationPermissions(activity, object : Utils.OnpermissionListener {
            override fun hasPermission(have: Boolean) {
                if (have){
                    model.location(App.instance.getmLocationClient())
                } else {
                    //TODO 提示用户开启权限
                }

            }

        })
    }

    override fun locationSucc(location: BDLocation) {
        view?.moveToLocation(location)
    }

    override fun locationFail() {

    }
}