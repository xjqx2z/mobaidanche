package com.shequn.liming.mobaidanche.ui.login

import android.app.Activity
import android.content.Intent
import com.shequn.liming.mobaidanche.App
import com.shequn.liming.mobaidanche.api.bean.User
import com.shequn.liming.mobaidanche.ui.main.MainActivity
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by Liming on 2017/7/27.
 */
class LoginPresenter(override var view: LoginContract.ILoginView?) :LoginContract.ILoginPresenter{

    override var model: LoginContract.ILoginModel = LoginModel(this)

    override fun getCode(phone: String) {
        view?.countDown()
        view?.showLoading("获取验证码...")
        model.getCode(phone)
    }

    override fun getCodeSucc() {
        view?.hideLoading()
    }

    override fun getCodeFail() {
        view?.hideLoading()
    }

    override fun loginOrRegister(phone: String, code: String) {
        model.loginOrRegister(phone, code)
        view?.showLoading("正在登陆...")
    }

    override fun loginSucc(user: User?) {
        view?.showLoading("登陆成功...")
        doAsync {
            Thread.sleep(1000)
            uiThread {
                view?.hideLoading()
                App.instance.setUser(user)
                val activity: Activity = view as Activity
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
        }
    }

    override fun loginFain() {
        view?.showLoading("登陆成功...")
        doAsync {
            Thread.sleep(2000)
            uiThread {
                view?.hideLoading()
                val user = User("18306661593", "小明哥", "1", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=951120017,198117664&fm=117&gp=0.jpg")
                App.instance.setUser(user)
                val activity: Activity = view as Activity
                activity.startActivity(Intent(activity, MainActivity::class.java))
            }
        }
    }

}