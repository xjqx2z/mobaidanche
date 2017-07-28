package com.shequn.liming.mobaidanche

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.shequn.liming.mobaidanche.api.bean.User
import com.shequn.liming.mobaidanche.base.BaseActivity
import com.shequn.liming.mobaidanche.ui.main.MainActivity
import com.shequn.liming.mobaidanche.ui.splash.SplashContract
import com.shequn.liming.mobaidanche.ui.splash.SplashPresenter
import com.shequn.liming.mobaidanche.utils.get
import com.shequn.liming.mobaidanche.utils.image.ImageLoader
import com.shequn.liming.shiji_newproject.utils.JsonUtils
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SplashActivity : BaseActivity() ,SplashContract.ISplashView{


    override var presenter: SplashContract.ISplashPresenter = SplashPresenter(this)

    private var isFinish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        val sp = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val userStr: String = sp.get("user_login", "") as String
        if (!TextUtils.isEmpty(userStr)) {
            val user: User = JsonUtils.fromJson(userStr, User::class.java)
            App.instance.setUser(user)
        }


    }

    override fun initData() {

        presenter.getAdvertisementImage()
    }

    override fun initListener() {
        tv_skip.setOnClickListener { goMain() }
    }

    override fun showNetImage(url: String?) {
        ImageLoader.loadImage(this@SplashActivity, url, iv_advertisement)
    }

    override fun showLocalImage() {
        iv_advertisement.setImageResource(R.mipmap.share_mobike_img)
    }

    override fun showSkipButton() {
        tv_skip.visibility = View.VISIBLE
        doAsync {
            for (second in 1..3) {
                Thread.sleep(1000)
                uiThread {
                    tv_skip.text = "跳过 ${3 - second}"
                }
            }
            uiThread {
                goMain()
            }
        }
    }

    override fun goMain() {
        if (isFinish) return
        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        isFinish = true
        finish()
    }
}
