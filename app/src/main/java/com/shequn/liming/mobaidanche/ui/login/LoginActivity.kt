package com.shequn.liming.mobaidanche.ui.login

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.shequn.liming.mobaidanche.R
import com.shequn.liming.mobaidanche.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.doAsync

class LoginActivity : BaseActivity(), LoginContract.ILoginView {



    override var presenter: LoginContract.ILoginPresenter = LoginPresenter(this)

    private var getCodeEnable = false
    private var okEnable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setStatusBar()
    }

    override fun initData() {

    }

    override fun initListener() {

        et_phone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length == 11) {
                    getCodeEnable = true
                    tv_get_code.setBackgroundDrawable(resources.getDrawable(R.drawable.button_get_code))
                } else {
                    getCodeEnable = false
                    tv_get_code.setBackgroundColor(Color.parseColor("#d9d9d9"))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        et_code.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s!!.length == 4 && et_phone.text.toString().length == 11) {
                    okEnable = true
                    tv_ok.setBackgroundDrawable(resources.getDrawable(R.drawable.button_get_code))
                } else {
                    okEnable = false
                    tv_ok.setBackgroundColor(Color.parseColor("#d9d9d9"))
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        iv_close.setOnClickListener { finish() }
        tv_ok.setOnClickListener { presenter.loginOrRegister(et_phone.text.toString(), et_code.text.toString()) }
        tv_get_code.setOnClickListener {
            if (getCodeEnable) {
                presenter.getCode(et_phone.text.toString())
            }
        }
    }

    override fun showLoading(text: String) {
        loadView.toast(text)
        loadView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadView.visibility = View.GONE
    }

    override fun countDown() {
        tv_get_code.text = "60秒"
        tv_get_code.setBackgroundColor(Color.parseColor("#D9D9D9"))
        getCodeEnable = false

        doAsync {

            for (index in 1..60) {
                Thread.sleep(1000)
                runOnUiThread {
                    tv_get_code.text = "${60 - index}秒"
                }
            }

            runOnUiThread {
                tv_get_code.text = "获取验证码"
                if (et_phone.text.toString().length == 11) {
                    getCodeEnable = true
                    tv_get_code.setBackgroundDrawable(resources.getDrawable(R.drawable.button_get_code))
                } else {
                    getCodeEnable = false
                    tv_get_code.setBackgroundColor(Color.parseColor("#d9d9d9"))
                }
            }
        }
    }
}
