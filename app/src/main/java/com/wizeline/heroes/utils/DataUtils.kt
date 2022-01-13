package com.wizeline.heroes.utils

import com.wizeline.heroes.toMD5

object DataUtils {
    const val privateKey = ""
    const val apikey = ""
    val ts = System.currentTimeMillis().toString()

    fun getHash() = (ts + privateKey + apikey).toMD5()
}

object Network {
    const val baseUrl = "https://gateway.marvel.com:443/v1/public/"
}