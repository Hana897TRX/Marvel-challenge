package com.wizeline.heroes.utils

import com.wizeline.heroes.toMD5

object DataUtils {
    const val privateKey = "53900a3736b8bf63cacfe4a7c0247ea0d568ea1a"
    const val apikey = "a4e2eb973becf70fedfaf89e989c2d76"
    val ts = System.currentTimeMillis().toString()

    fun getHash() = (ts + privateKey + apikey).toMD5()
}