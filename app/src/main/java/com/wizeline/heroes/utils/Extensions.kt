package com.wizeline.heroes.utils

import android.content.Context
import android.view.View
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

fun View.hide() {
    this.visibility = View.INVISIBLE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}