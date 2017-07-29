package com.shequn.liming.mobaidanche.ui.openlock

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Liming on 2017/7/29.
 */
class OpenLockPresenter(override var view: OpenLockContract.IOpenLockView?) : OpenLockContract.IOpenLockPresenter{


    override var model: OpenLockContract.IOpenLockModel = OpenLockModel(this)

    override fun openLock(bikeNumber: String) {
        model.openLock(bikeNumber)
        view?.showLoading("正在开锁...")
    }

    override fun openLockSucc() {
        view?.hideLoading()
        view?.gotoMain()
    }

    override fun openLockFail() {
        view?.showLoading("开锁失败...")
        doAsync {
            Thread.sleep(1500)
            uiThread {
                view?.hideLoading()
            }
        }
    }


}