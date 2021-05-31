package com.android.painton.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.android.painton.databinding.ActivitySplashScreenBinding
import com.android.painton.ui.shape_renderers.MovingShapeRenderer

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        binding.glSurfaceView.setRenderer(MovingShapeRenderer())
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SelectionActivity::class.java))
            finish()
        }, 5000)
    }

    override fun onResume() {
        super.onResume()
        binding.glSurfaceView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.glSurfaceView.onPause()
    }
}