package com.shequn.liming.mobaidanche.ui.login

import com.shequn.liming.mobaidanche.api.bean.User

/**
 * Created by Liming on 2017/7/27.
 */
object LoginContract {

    interface ILoginModel{

        var presenter: ILoginPresenter

        fun getCode(phone: String)

        fun loginOrRegister(phone: String, code: String)

    }

    interface ILoginView{
        var presenter: ILoginPresenter


        fun showLoading(text: String)

        fun hideLoading()

        fun countDown()


    }

    interface ILoginPresenter{

        var model: ILoginModel
        var view: ILoginView?

        fun getCode(phone: String)

        fun getCodeSucc()

        fun getCodeFail()

        fun loginOrRegister(phone: String, code: String)

        fun loginSucc(user: User?)

        fun loginFain()

    }
}