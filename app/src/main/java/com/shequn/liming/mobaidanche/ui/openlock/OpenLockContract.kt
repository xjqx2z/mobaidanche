package com.shequn.liming.mobaidanche.ui.openlock

/**
 * Created by Liming on 2017/7/27.
 */
object OpenLockContract {

    interface IOpenLockModel{

        var presenter: IOpenLockPresenter


    }

    interface IOpenLockView{
        var presenter: IOpenLockPresenter




    }

    interface IOpenLockPresenter{

        var model: IOpenLockModel
        var view: IOpenLockView?


    }
}