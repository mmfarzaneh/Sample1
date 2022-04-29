package com.sample.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.ads.*
import com.sample.R
import com.sample.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var mAdView: AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navHostFragment.navController

        MobileAds.initialize(
            this
        ) { }

        mAdView = findViewById(R.id.adView)
        val adRequest: AdRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
        // Change your Id
//        val configuration = RequestConfiguration.Builder()
//            .setTestDeviceIds(listOf("33BE2250B43518CCDA7DE426D04EE231")).build()
//        MobileAds.setRequestConfiguration(configuration)

        mAdView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                Log.d("mAdView", "onAdLoaded")
            }

            override fun onAdFailedToLoad(adError: LoadAdError) {
                Log.d("mAdView", "onAdFailedToLoad: $adError")
            }

            override fun onAdOpened() {
                Log.d("mAdView", "onAdOpened")
            }

            override fun onAdClicked() {
                Log.d("mAdView", "onAdClicked")
            }

            override fun onAdClosed() {
                Log.d("mAdView", "onAdClosed")
            }
        }
    }
}