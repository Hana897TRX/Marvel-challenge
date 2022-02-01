package com.wizeline.heroes.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.wizeline.heroes.BuildConfig
import com.wizeline.heroes.R
import com.wizeline.heroes.ui.home.MainActivity
import com.wizeline.heroes.utils.makeToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }

        val firebaseConfig : FirebaseRemoteConfig = Firebase.remoteConfig
        firebaseConfig.setConfigSettingsAsync(configSettings)

        Firebase.remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                val appBlocked = Firebase.remoteConfig.getBoolean("isAppBlocked")
                if(!appBlocked) {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }
                else {
                    makeToast(this@SplashScreenActivity, getString(R.string.appBlocked))
                }
            }
        }
    }
}