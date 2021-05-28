package com.android.painton.ui.activity

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.painton.R
import com.android.painton.databinding.ActivityMainBinding
import com.android.painton.ui.customviews.ColorPicker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        //listeners
        binding.ivUndo.setOnClickListener {
            binding.drawView.undo()
        }

        binding.ivErase.apply {
            setOnClickListener {
                when (binding.drawView.getErase()) {
                    true -> {
                        binding.paintEraseTv.text = this.resources.getString(R.string.eraser)
                        setDrawable(this, R.drawable.ic_eraser)
                        binding.drawView.setErase(false)
                    }
                    false -> {
                        binding.paintEraseTv.text = this.resources.getString(R.string.brush)
                        setDrawable(this, R.drawable.ic_brush)
                        binding.drawView.setErase(true)
                    }
                }
            }
        }
        binding.ivClear.setOnClickListener {
            binding.drawView.clear()
        }

        binding.ivColorPicker.setOnClickListener {
            when (binding.drawView.getErase()) {
                true -> {
                    binding.paintEraseTv.text = this.resources.getString(R.string.eraser)
                    setDrawable(binding.ivErase, R.drawable.ic_eraser)
                    binding.drawView.setErase(false)
                    initializePicker()
                }
                false -> {
                    initializePicker()
                }
            }
        }

        binding.switchStrokeFill.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.drawView.setStyleToFill()
                binding.paintModeTv.text = resources?.getString(R.string.mode_fill)
            } else {
                binding.drawView.setStyleToStroke()
                binding.paintModeTv.text = resources?.getString(R.string.mode_stroke)
            }
        }

        binding.strokeWidthSlider.addOnChangeListener { _, value, _ ->
            binding.drawView.setStrokeWidth(value.toInt())
        }

        binding.drawView.apply {
            viewTreeObserver.addOnGlobalLayoutListener(object :
                ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                    init(binding.drawView.measuredHeight, binding.drawView.measuredWidth)
                }
            })
        }
    }

    private fun initializePicker() {
        ColorPicker(this).apply {
            setOnFastChooseColorListener(object :
                ColorPicker.OnFastChooseColorListener {

                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    binding.drawView.setColor(color)
                }

                override fun onCancel() {
                    dismissDialog()
                }
            })
                .setColumns(5)
                .setDefaultColorButton(Color.WHITE)
                .show()
        }
    }

    private fun setDrawable(imageView: ImageView, drawableRes: Int) {
        imageView.setImageDrawable(ContextCompat.getDrawable(this, drawableRes))
    }
}