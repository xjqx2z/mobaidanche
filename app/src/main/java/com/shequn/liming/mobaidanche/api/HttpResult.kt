package com.shequn.liming.mobaidanche.api

/**
 * Created by Liming on 2017/7/25.
 */
class HttpResult<T> {

    var success: Boolean = false
    var result: T? = null
    var errorCode: String? = null
    var msg: String? = null
    var login: Boolean = false
}