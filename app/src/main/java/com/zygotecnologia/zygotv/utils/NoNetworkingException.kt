package com.zygotecnologia.zygotv.utils

import java.io.IOException

/**
 * @author allef.santos on 15/03/21
 */

object NoNetworkingException : IOException(){
    override val message: String
        get() = "Internet Exception"
}
