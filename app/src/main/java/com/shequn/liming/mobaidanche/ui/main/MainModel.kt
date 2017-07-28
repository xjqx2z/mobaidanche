package com.shequn.liming.mobaidanche.ui.main

import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.shequn.liming.mobaidanche.utils.Utils

/**
 * Created by Liming on 2017/7/27.
 */
class MainModel(override var presenter: MainContract.IMainPresenter) : MainContract.IMainModel{

    override fun location(client: LocationClient?) {
        client?.registerLocationListener(object : BDLocationListener {
            override fun onReceiveLocation(location: BDLocation?) {
                client?.stop()
                if (location == null) return

                if (location.locType < 162) {
                    presenter.locationSucc(location)
                } else {
                    presenter.locationFail()
                }


            }

            override fun onConnectHotSpotMessage(p0: String?, p1: Int) {
            }
        })

        client?.locOption = Utils.getLocationClientOption()

        client?.start()

    }

}