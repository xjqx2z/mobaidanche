package com.shequn.liming.mobaidanche.ui.splash

/**
 * Created by Liming on 2017/7/25.
 */
object SplashContract{

    interface ISplashModel{

        var presenter: ISplashPresenter

        fun getAdvertisementImage()

    }

    interface ISplashView{
        var presenter: ISplashPresenter

        fun showNetImage(url: String?)

        fun showLocalImage()

        fun goMain()

        fun showSkipButton()


    }

    interface ISplashPresenter{

        var model: ISplashModel
        var view: ISplashView?

        fun getAdvertisementImage()

        fun getAdvertisementImageSucc(url: String?)

        fun getAdvertisementImageFail()
    }

}