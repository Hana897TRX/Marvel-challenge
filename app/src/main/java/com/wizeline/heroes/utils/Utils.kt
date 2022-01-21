package com.wizeline.heroes.utils

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.wizeline.heroes.toMD5

object DataUtils {
    const val PRIVATE_KEY = "53900a3736b8bf63cacfe4a7c0247ea0d568ea1a"
    const val API_KEY = "a4e2eb973becf70fedfaf89e989c2d76"
    val TIME_STAMP = System.currentTimeMillis().toString()

    fun getHash() = (TIME_STAMP + PRIVATE_KEY + API_KEY).toMD5()
}

object Network {
    const val OFFSET_CONFIG = 20
    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
}

object GlideUtils {
    @SuppressLint("StaticFieldLeak")
    private var GLIDE_INSTANCE : RequestManager? = null

    fun getInstance(context : Context) : RequestManager {
        return if(GLIDE_INSTANCE == null) {
            GLIDE_INSTANCE = Glide.with(context)
            GLIDE_INSTANCE!!
        } else {
            GLIDE_INSTANCE!!
        }
    }

    fun eraseInstance() {
        GLIDE_INSTANCE = null
    }
}