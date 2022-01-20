package com.wizeline.heroes.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wizeline.heroes.R
import com.wizeline.heroes.utils.GlideUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //requestCharacters()
    }

    private fun requestCharacters() {
        //val hash = (ts + privateKey + apikey).toMD5()
        /*NetworkClient().getServices().characters(ts, apikey, hash)
            .enqueue(object : retrofit2.Callback<Characters> {
                override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                    //TODO()
                }

                override fun onFailure(call: Call<Characters>, t: Throwable) {
                    //TODO()
                }
            })
         */
    }

    override fun onDestroy() {
        super.onDestroy()
        GlideUtils.eraseInstance()
    }
}
