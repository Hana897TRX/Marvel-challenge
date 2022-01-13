package com.wizeline.heroes.utils

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.wizeline.heroes.toMD5

object DataUtils {
    const val PRIVATE_KEY = ""
    const val API_KEY = ""
    val TIME_STAMP = System.currentTimeMillis().toString()

    fun getHash() = (TIME_STAMP + PRIVATE_KEY + API_KEY).toMD5()
}

object Network {
    const val OFFSET_CONFIG = 20
    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
}

object GlideUtils {
    @SuppressLint("StaticFieldLeak")
    private var glide : RequestManager? = null

    fun getInstance(context : Context) : RequestManager {
        return if(glide == null) {
            glide = Glide.with(context)
            glide!!
        } else {
            glide!!
        }
    }

    fun eraseInstance() {
        glide = null
    }
}