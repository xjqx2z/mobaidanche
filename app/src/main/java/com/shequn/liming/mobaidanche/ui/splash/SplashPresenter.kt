package com.shequn.liming.mobaidanche.ui.splash

/**
 * Created by Liming on 2017/7/25.
 */
class SplashPresenter(override var view: SplashContract.ISplashView?) : SplashContract.ISplashPresenter{


    override var model: SplashContract.ISplashModel = SplashModel(this)

    override fun getAdvertisementImage() {
        model.getAdvertisementImage()
    }

    override fun getAdvertisementImageSucc(url: String?) {
        view?.showNetImage(url)
        view?.showSkipButton()
    }

    override fun getAdvertisementImageFail() {
        view?.showLocalImage()
        view?.showSkipButton()
    }

}