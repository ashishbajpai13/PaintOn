package com.android.painton.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.painton.R
import com.android.painton.databinding.ActivitySelectionBinding

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        //on click listeners
        binding.drawShapesIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.TYPE, MainActivity.SHAPES_TYPE))
            finish()
        }
        binding.drawShapesTv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.TYPE, MainActivity.SHAPES_TYPE))
            finish()
        }

        binding.paintIv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.TYPE, MainActivity.PAINT_TYPE))
            finish()
        }
        binding.paintTv.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).putExtra(MainActivity.TYPE, MainActivity.PAINT_TYPE))
            finish()
        }
    }
}