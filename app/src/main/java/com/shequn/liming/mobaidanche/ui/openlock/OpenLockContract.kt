package com.shequn.liming.mobaidanche.ui.openlock

/**
 * Created by Liming on 2017/7/27.
 */
object OpenLockContract {

    interface IOpenLockModel{

        var presenter: IOpenLockPresenter

        fun openLock(bikeNumber: String)

    }

    interface IOpenLockView{
        var presenter: IOpenLockPresenter


        fun showLoading(text: String)

        fun hideLoading()


        fun lightIsOn()

        fun lightIsOff()


        fun gotoMain()


    }

    interface IOpenLockPresenter{

        var model: IOpenLockModel
        var view: IOpenLockView?

        fun openLock(bikeNumber: String)

        fun openLockSucc()

        fun openLockFail()


    }
}