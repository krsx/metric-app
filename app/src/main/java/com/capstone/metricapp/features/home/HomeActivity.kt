package com.capstone.metricapp.features.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.capstone.metricapp.R
import com.capstone.metricapp.core.utils.FabMenuState
import com.capstone.metricapp.databinding.ActivityHomeBinding
import com.capstone.metricapp.features.settings.SettingsActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private var fabMenuState: FabMenuState = FabMenuState.COLLAPSED

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabMenu.setOnClickListener {
            onFabMenuClick()
        }

        binding.ivSetting.setOnClickListener {
            val intentToSetting = Intent(this, SettingsActivity::class.java)
            startActivity(intentToSetting)
        }
    }

    private fun onFabMenuClick() {
        fabMenuState = if (fabMenuState == FabMenuState.COLLAPSED){
            FabMenuState.EXPANDED
        }else{
            FabMenuState.COLLAPSED
        }
        Log.e("TEST", fabMenuState.toString())
        setVisibility(fabMenuState == FabMenuState.EXPANDED)
        setAnimation(fabMenuState == FabMenuState.EXPANDED)
        setClickable(fabMenuState == FabMenuState.EXPANDED)
    }

    private fun setAnimation(isClicked: Boolean) {
        val rotateOpen: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_rotate_open)
        }
        val rotateClose: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_rotate_close)
        }
        val fromBottom: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_from_bottom)
        }
        val toBottom: Animation by lazy {
            AnimationUtils.loadAnimation(this, R.anim.anim_to_bottom)
        }

        if (isClicked) {
            binding.fabAdd.startAnimation(fromBottom)
            binding.tvAddKeypoints.startAnimation(fromBottom)
            binding.fabScan.startAnimation(fromBottom)
            binding.tvScanQr.startAnimation(fromBottom)
            binding.fabMenu.startAnimation(rotateOpen)
        } else {
            binding.fabAdd.startAnimation(toBottom)
            binding.tvAddKeypoints.startAnimation(toBottom)
            binding.fabScan.startAnimation(toBottom)
            binding.tvScanQr.startAnimation(toBottom)
            binding.fabMenu.startAnimation(rotateClose)
        }
    }

    private fun setVisibility(isClicked: Boolean) {
        if (isClicked) {
            binding.fabScan.visibility = View.VISIBLE
            binding.tvScanQr.visibility = View.VISIBLE
            binding.fabAdd.visibility = View.VISIBLE
            binding.tvAddKeypoints.visibility = View.VISIBLE
        } else {
            binding.fabScan.visibility = View.INVISIBLE
            binding.tvScanQr.visibility = View.INVISIBLE
            binding.fabAdd.visibility = View.INVISIBLE
            binding.tvAddKeypoints.visibility = View.INVISIBLE
        }
    }

    private fun setClickable(isClicked: Boolean) {
        if (isClicked) {
            binding.fabAdd.isClickable = true
            binding.fabScan.isClickable = true
        } else {
            binding.fabAdd.isClickable = false
            binding.fabScan.isClickable = false
        }
    }


}