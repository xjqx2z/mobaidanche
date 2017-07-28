package com.shequn.liming.mobaidanche.utils

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

/**
 * Created by wing on 11/23/16.
 */

fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun SharedPreferences.save( key: String, any: Any) {
    val type = any.javaClass.simpleName
    val edit = this.edit()
    if ("String" == type) {
        edit.putString(key, any as String)
    } else if ("Integer" == type) {
        edit.putInt(key, any as Int)
    } else if ("Boolean" == type) {
        edit.putBoolean(key, any as Boolean)
    } else if ("Float" == type) {
        edit.putFloat(key, any as Float)
    } else if ("Long" == type) {
        edit.putLong(key, any as Long)
    }
    edit.commit()

}

fun SharedPreferences.get( key: String, defaultObject: Any): Any? {
    val type = defaultObject.javaClass.simpleName

    if ("String" == type) {
        return this.getString(key, defaultObject as String)
    } else if ("Integer" == type) {
        return this.getInt(key, defaultObject as Int)
    } else if ("Boolean" == type) {
        return this.getBoolean(key, defaultObject as Boolean)
    } else if ("Float" == type) {
        return this.getFloat(key, defaultObject as Float)
    } else if ("Long" == type) {
        return this.getLong(key, defaultObject as Long)
    }
    return null
}

fun SharedPreferences.cleanAll() {
    val editor = this.edit()
    editor.clear()
    editor.commit()
}


