package com.shequn.liming.mobaidanche.ui.login

import com.shequn.liming.mobaidanche.api.API
import com.shequn.liming.mobaidanche.api.HttpResult
import com.shequn.liming.mobaidanche.api.bean.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Liming on 2017/7/27.
 */
class LoginModel(override var presenter: LoginContract.ILoginPresenter) :LoginContract.ILoginModel{

    override fun getCode(phone: String) {

        API.apiService.getVerificationCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HttpResult<String?>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HttpResult<String?>) {
                        if (t.success) {
                            presenter.getCodeSucc()
                        } else {
                            presenter.getCodeFail()
                        }
                    }

                    override fun onError(e: Throwable) {
                        presenter.getCodeFail()
                    }

                    override fun onComplete() {
                    }

                })
    }

    override fun loginOrRegister(phone: String, code: String) {

        API.apiService.loginOrRegister(phone, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<HttpResult<User?>> {
                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: HttpResult<User?>) {
                        if (t.success) {
                            presenter.loginSucc(t.result)
                        } else {
                            presenter.loginFain()
                        }
                    }

                    override fun onError(e: Throwable) {
                        presenter.loginFain()
                    }

                    override fun onComplete() {
                    }

                })
    }

}