package com.shequn.liming.shiji_newproject.utils

import com.google.gson.Gson

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import org.json.JSONTokener

import java.lang.reflect.Array

object JsonUtils {

    fun toJson(obj: Any?): String? {
        val gson = Gson()
        return gson.toJson(obj)
    }

    fun <T> fromJson(str: String, type: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(str, type)
    }

    fun map2Json(data: Map<*, *>): JSONObject {
        val `object` = JSONObject()

        for ((key1, value) in data) {
            val key = key1 as String ?: throw NullPointerException("key == null")
            try {
                `object`.put(key, wrap(value))
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }

        return `object`
    }

    fun collection2Json(data: Collection<*>?): JSONArray {
        val jsonArray = JSONArray()
        if (data != null) {
            for (aData in data) {
                jsonArray.put(wrap(aData))
            }
        }
        return jsonArray
    }

    @Throws(JSONException::class)
    fun object2Json(data: Any): JSONArray {
        if (!data.javaClass.isArray) {
            throw JSONException("Not a primitive data: " + data.javaClass)
        }
        val length = Array.getLength(data)
        val jsonArray = JSONArray()
        for (i in 0..length - 1) {
            jsonArray.put(wrap(Array.get(data, i)))
        }

        return jsonArray
    }

    private fun wrap(o: Any?): Any? {
        if (o == null) {
            return null
        }
        if (o is JSONArray || o is JSONObject) {
            return o
        }
        try {
            if (o is Collection<*>) {
                return collection2Json(o as Collection<*>?)
            } else if (o.javaClass.isArray) {
                return object2Json(o)
            }
            if (o is Map<*, *>) {
                return map2Json(o as Map<*, *>)
            }

            if (o is Boolean || o is Byte || o is Char || o is Double || o is Float || o is Int || o is Long
                    || o is Short || o is String) {
                return o
            }
            if (o.javaClass.`package`.name.startsWith("java.")) {
                return o.toString()
            }
        } catch (ignored: Exception) {
        }

        return null
    }

    fun string2JSONObject(json: String): JSONObject? {
        var jsonObject: JSONObject? = null
        try {
            val jsonParser = JSONTokener(json)
            jsonObject = jsonParser.nextValue() as JSONObject
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return jsonObject
    }
}