package com.zygotecnologia.zygotv.secret

object Keys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun apiKey(): String
}