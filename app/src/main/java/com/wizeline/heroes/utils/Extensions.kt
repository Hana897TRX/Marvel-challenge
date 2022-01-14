package com.wizeline.heroes

import android.content.Context
import android.widget.Toast
import java.math.BigInteger
import java.security.MessageDigest

fun String.toMD5(): String {
    val digest = MessageDigest.getInstance("MD5")
    return BigInteger(1, digest.digest(toByteArray())).toString(16).padStart(32, '0')
}

fun makeToast(context : Context, text : String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
