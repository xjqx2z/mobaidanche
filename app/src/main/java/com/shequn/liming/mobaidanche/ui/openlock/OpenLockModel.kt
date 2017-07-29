package com.shequn.liming.mobaidanche.ui.openlock

import com.shequn.liming.mobaidanche.App
import com.shequn.liming.mobaidanche.api.API
import com.shequn.liming.mobaidanche.api.HttpResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Liming on 2017/7/29.
 */
class OpenLockModel(override var presenter: OpenLockContract.IOpenLockPresenter) :OpenLockContract.IOpenLockModel{

    override fun openLock(bikeNumber: String) {
        API.apiService.openLock(bikeNumber, App.instance.getUser()!!.userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HttpResult<String?>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HttpResult<String?>) {
                        if (t.success) {
                            presenter.openLockSucc()
                        } else {
                            presenter.openLockFail()
                        }
                    }

                    override fun onError(e: Throwable) {
                        presenter.openLockSucc()
                    }

                    override fun onComplete() {
                    }

                })
    }

}