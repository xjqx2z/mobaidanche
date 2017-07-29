package com.shequn.liming.mobaidanche.ui.openlock

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.shequn.liming.mobaidanche.R
import com.shequn.liming.mobaidanche.base.BaseActivity
import com.shequn.liming.mobaidanche.ui.main.MainActivity
import com.shequn.liming.mobaidanche.utils.Utils
import com.shequn.liming.mobaidanche.utils.toast
import com.shequn.liming.shiji_newproject.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_open_lock.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class OpenLockActivity : BaseActivity(), QRCodeView.Delegate , OpenLockContract.IOpenLockView
 {

    override fun gotoMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("open_lock", true)
        startActivity(intent)
    }

    override var presenter: OpenLockContract.IOpenLockPresenter = OpenLockPresenter(this)

    override fun showLoading(text: String) {
        loadingView.toast(text)
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

    override fun lightIsOn() {
        tv_light.text = "关闭手电筒"
        zxingview.openFlashlight()

    }

    override fun lightIsOff() {
        tv_light.text = "打开手电筒"
        zxingview.closeFlashlight()
    }

    override fun onScanQRCodeSuccess(result: String?) {
        if (result == null) return
        presenter.openLock(result)
    }

    override fun onScanQRCodeOpenCameraError() {
        showLoading("扫描出错...")
        doAsync {
            Thread.sleep(1500)
            uiThread {
                hideLoading()
            }
        }
    }

    override fun initData() {
    }

    override fun initListener() {

        zxingview.setDelegate(this)
        tv_light.setOnClickListener {
            tv_light.isSelected = !tv_light.isSelected
            if (tv_light.isSelected) {
                lightIsOn()
            } else {
                lightIsOff()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_lock)
        title_middle.text = "扫码开锁"
        title_right.text = "使用帮助"
        title_left.setCompoundDrawablesWithIntrinsicBounds(resources.getDrawable(R.mipmap.common_ab_back_white), null, null, null)
        setStatusBar()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //半透明状态栏
            layout.setPadding(0, DensityUtil.getStatusBarH(this@OpenLockActivity), 0, 0)
        }

    }


    override fun onStart() {
        super.onStart()
        if (Utils.isCameraCanUse()) {
            zxingview.startCamera()
            zxingview.startSpot()
        } else {
            toast("摄像头不可用")
        }

        //        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);
    }

    override fun onStop() {
        zxingview.stopCamera()
        super.onStop()
        zxingview.stopSpot()
    }

    override fun onDestroy() {
        super.onDestroy()
        zxingview.onDestroy()
    }
}
