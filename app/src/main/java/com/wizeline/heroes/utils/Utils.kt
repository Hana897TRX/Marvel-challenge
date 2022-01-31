package com.wizeline.heroes.utils

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

object DataUtils {
    const val PRIVATE_KEY = ""
    const val API_KEY = ""
    val TIME_STAMP = System.currentTimeMillis().toString()

    fun getHash() = (TIME_STAMP + PRIVATE_KEY + API_KEY).toMD5()
}

object ConstVals {
    const val EMPTY_VALUE = ""
}

object Network {
    const val OFFSET_CONFIG = 20
    const val BASE_URL = "https://gateway.marvel.com:443/v1/public/"
    const val QUERY_CHARACTER_ID = "characterId"
    const val QUERY_TS = "ts"
    const val QUERY_API_KEY = "apikey"
    const val QUERY_HASH = "hash"
    const val QUERY_OFFSET = "offset"
    const val NAME_STARTS_WITH = "nameStartsWith"
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