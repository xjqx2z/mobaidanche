package com.shequn.liming.mobaidanche.utils

import android.Manifest
import android.app.Activity
import android.hardware.Camera
import com.baidu.location.LocationClientOption
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * Created by Liming on 2017/7/27.
 */
object Utils{

    /**
     * 定位参数
     * */
    fun getLocationClientOption(): LocationClientOption{

        val option = LocationClientOption()
        option.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll")
        //可选，默认gcj02，设置返回的定位结果坐标系
        val span = 1
        option.setScanSpan(span)
        //可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true)
        //可选，设置是否需要地址信息，默认不需要
        option.isOpenGps = true
        //可选，默认false,设置是否使用gps
        option.isLocationNotify = true
        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true)
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true)
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false)
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false)
        //可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false)
        //可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        return option
    }

    interface OnpermissionListener{
        fun hasPermission(have: Boolean)
    }

    /**
     * 检查定位权限
     * */
    fun checkLocationPermissions(context: Activity, listener: OnpermissionListener) {


        RxPermissions(context)
                .requestEach(
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe { p ->
                    if (p.name == "android.permission.ACCESS_COARSE_LOCATION") {
                        listener.hasPermission(p.granted)
                    }
                }
    }

    fun isCameraCanUse(): Boolean {
        var canUse = true
        var mCamera: Camera? = null
        try {
            mCamera = Camera.open()
            // setParameters 是针对魅族MX5 做的。MX5 通过Camera.open() 拿到的Camera
            // 对象不为null
            val mParameters = mCamera!!.parameters
            mCamera.parameters = mParameters
        } catch (e: Exception) {
            canUse = false
        }

        if (mCamera != null) {
            mCamera.release()
        }
        return canUse
    }


    /**
     * 检查定位权限
     * */
    fun checkCameraPermissions(context: Activity, listener: OnpermissionListener) {


        RxPermissions(context)
                .requestEach(
                        Manifest.permission.CAMERA,
                        Manifest.permission.VIBRATE
                        )
                .subscribe { p ->
                    if (p.name == "android.permission.CAMERA") {
                        listener.hasPermission(p.granted)
                    }
                }
    }

}