package com.zygotecnologia.zygotv.utils.secret

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
}