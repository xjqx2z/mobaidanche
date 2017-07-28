package com.shequn.liming.mobaidanche.ui.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.view.View
import com.baidu.location.BDLocation
import com.baidu.mapapi.map.BaiduMap
import com.baidu.mapapi.map.MapStatus
import com.baidu.mapapi.map.MapStatusUpdateFactory
import com.baidu.mapapi.map.MapView
import com.baidu.mapapi.model.LatLng
import com.shequn.liming.mobaidanche.App
import com.shequn.liming.mobaidanche.R
import com.shequn.liming.mobaidanche.base.BaseActivity
import com.shequn.liming.mobaidanche.ui.login.LoginActivity
import com.shequn.liming.mobaidanche.ui.openlock.OpenLockActivity
import com.shequn.liming.mobaidanche.utils.Utils
import com.shequn.liming.mobaidanche.utils.image.ImageLoader
import com.shequn.liming.shiji_newproject.utils.DensityUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_content.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity(), MainContract.IMainView, View.OnClickListener {


    override var presenter: MainContract.IMainPresenter = MainPresenter(this)


    private lateinit var mMapView: MapView
    private lateinit var baiduMap: BaiduMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setStatusBar()
        initListener()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //半透明状态栏
            toolbar.setPadding(0, DensityUtil.getStatusBarH(this@MainActivity), 0, 0)
        }



        mMapView = find(R.id.bmapView)
        baiduMap = mMapView.map
        mMapView.showZoomControls(false)

        presenter.location()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        initData()
    }

    override fun moveToLocation(location: BDLocation) {

        val ll = LatLng(location.latitude,
                location.longitude)
        val builder = MapStatus.Builder()
        builder.target(ll).zoom(18.0f)
        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()))

    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }


    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun initData() {
        val user = App.instance.getUser()
        if(user != null) {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            tv_to_login.visibility = View.GONE
            ImageLoader.loadCircleImage(this, user.headImg, iv_user_portrait)
            tv_phone.text = user.phoneNum.replaceRange(3,7, "****")
        } else {
            drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        }
    }

    override fun initListener() {

        tv_to_login.setOnClickListener(this)
        tv_user_info.setOnClickListener (this)
        tv_red_pocket.setOnClickListener (this)
        tv_open_lock.setOnClickListener (this)
        iv_call.setOnClickListener (this)
        tv_location.setOnClickListener { presenter.location() }
    }

    override fun onClick(v: View?) {
        if (!isLogin()) {
            goToLogin()
            return
        }
        when (v!!.id) {
            R.id.tv_user_info -> {
                if (drawer_layout.isDrawerOpen(nav_view)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                } else {
                    drawer_layout.openDrawer(GravityCompat.START, true)
                }
            }
            R.id.tv_open_lock -> {
                Utils.checkCameraPermissions(this, object : Utils.OnpermissionListener {
                    override fun hasPermission(have: Boolean) {
                        if (have) {
                            startActivity(Intent(this@MainActivity, OpenLockActivity::class.java))
                        } else {

                        }
                    }
                })

            }
        }
    }

    fun isLogin(): Boolean {
        return App.instance.getUser() != null
    }

    fun goToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }


}
