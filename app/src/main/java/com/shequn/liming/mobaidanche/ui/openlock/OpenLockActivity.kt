package com.shequn.liming.mobaidanche.ui.openlock

import android.os.Build
import android.os.Bundle
import cn.bingoogolapple.qrcode.core.QRCodeView
import com.shequn.liming.mobaidanche.R
import com.shequn.liming.mobaidanche.base.BaseActivity
import com.shequn.liming.mobaidanche.utils.Utils
import com.shequn.liming.mobaidanche.utils.toast
import com.shequn.liming.shiji_newproject.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_open_lock.*
import kotlinx.android.synthetic.main.layout_toolbar.*


class OpenLockActivity : BaseActivity(), QRCodeView.Delegate {
    override fun onScanQRCodeSuccess(result: String?) {
    }

    override fun onScanQRCodeOpenCameraError() {
    }

    override fun initData() {
    }

    override fun initListener() {

        zxingview.setDelegate(this)
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
