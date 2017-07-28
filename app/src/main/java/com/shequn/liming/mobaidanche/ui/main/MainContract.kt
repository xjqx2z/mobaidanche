package com.shequn.liming.mobaidanche.ui.main

import com.baidu.location.BDLocation
import com.baidu.location.LocationClient

/**
 * Created by Liming on 2017/7/26.
 */
object MainContract{

    interface IMainModel{

        var presenter: IMainPresenter

        fun location(client: LocationClient?)


    }

    interface IMainView{
        var presenter: IMainPresenter


        fun moveToLocation(location: BDLocation)


    }

    interface IMainPresenter{

        var model: IMainModel
        var view: IMainView?

        fun location()

        fun locationSucc(location: BDLocation)

        fun locationFail()


    }

}