package com.zygotecnologia.zygotv.utils

object ApiKeyLibrary {

    init {
        System.loadLibrary("native-lib")
    }

    private external fun stringFromJNI(): String

    fun getApiKey() = stringFromJNI()

}