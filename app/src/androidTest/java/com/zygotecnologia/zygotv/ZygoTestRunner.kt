package com.zygotecnologia.zygotv

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class ZygoTestRunner: AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?): Application {
        return super.newApplication(cl, ZygoTestApp::class.java.name, context)
    }
}