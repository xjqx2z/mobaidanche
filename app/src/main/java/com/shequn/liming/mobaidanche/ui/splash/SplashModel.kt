package com.shequn.liming.mobaidanche.ui.splash

import com.shequn.liming.mobaidanche.api.API
import com.shequn.liming.mobaidanche.api.HttpResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Liming on 2017/7/25.
 */
class SplashModel(override var presenter: SplashContract.ISplashPresenter) :SplashContract.ISplashModel{

    override fun getAdvertisementImage() {

        API.apiService.getAdvertisementImage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HttpResult<String?>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HttpResult<String?>) {
                        if (t.success) {
                            presenter.getAdvertisementImageSucc(t.result)
                        } else {
                            presenter.getAdvertisementImageFail()
                        }
                    }

                    override fun onError(e: Throwable) {
                        presenter.getAdvertisementImageFail()
                    }

                    override fun onComplete() {
                    }

                })

    }

}