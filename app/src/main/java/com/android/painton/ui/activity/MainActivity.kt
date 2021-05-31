package com.android.painton.ui.activity

import android.app.Dialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.android.painton.R
import com.android.painton.databinding.ActivityMainBinding
import com.android.painton.ui.`interface`.DialogCallback
import com.android.painton.ui.customviews.ColorPicker
import com.android.painton.ui.shape_renderers.ShapeRenderer3D
import com.google.android.material.textview.MaterialTextView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var type: String? = null

    companion object {
        const val TYPE = "type"
        const val SHAPES_TYPE = "shapes_type"
        const val PAINT_TYPE = "draw_type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent?.apply {
            when (getStringExtra(TYPE)) {
                SHAPES_TYPE -> {
                    initShapeType()
                    type = SHAPES_TYPE
                }
                PAINT_TYPE -> {
                    initPaintType()
                    type = PAINT_TYPE
                }
            }
        }
    }

    private fun initShapeType() {
        binding.shapesTypeContainer.visibility = View.VISIBLE
        binding.paintTypeContainer.visibility = View.GONE
        binding.glSurfaceView.setRenderer(ShapeRenderer3D())
        binding.drawSquareButton.setOnClickListener {
            showColorDialog(6, object : DialogCallback {
                override fun onClick(floatArray: List<FloatArray>) {
                    binding.glSurfaceView.apply {
                        ShapeRenderer3D(ShapeRenderer3D.SQUARE, floatArray)
                    }
                }
            })
        }
        binding.drawTriangleButton.setOnClickListener {
            showColorDialog(3, object : DialogCallback {
                override fun onClick(floatArray: List<FloatArray>) {
                    binding.glSurfaceView.apply {
                        ShapeRenderer3D(ShapeRenderer3D.TRIANGLE, floatArray)
                    }
                }
            })
        }
    }

    private fun showColorDialog(size: Int, dialogCallback: DialogCallback) {
        var floatArray = listOf<FloatArray>()
        Dialog(this).apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
            setContentView(R.layout.custom_dialog_view)
            val pickerText1 = findViewById<MaterialTextView>(R.id.pickerText1).let {
                if (size >= 1) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            val pickerText2 = findViewById<MaterialTextView>(R.id.pickerText2).let {
                if (size >= 2) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            val pickerText3 = findViewById<MaterialTextView>(R.id.pickerText3).let {
                if (size >= 3) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            val pickerText4 = findViewById<MaterialTextView>(R.id.pickerText4).let {
                if (size >= 4) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            val pickerText5 = findViewById<MaterialTextView>(R.id.pickerText5).let {
                if (size >= 5) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            val pickerText6 = findViewById<MaterialTextView>(R.id.pickerText6).let {
                if (size >= 6) {
                    it.visibility = View.VISIBLE
                }
                it.tag = getRgbFromHex("#FFFFFF")
                it.setOnClickListener { _ ->
                    initializePicker(it, object : ColorPicker.OnChooseColorListener {
                        override fun onChooseColor(position: Int, color: Int) {
                            floatArray = floatArray + getRgbFromHex(color)
                        }

                        override fun onCancel() {
                        }
                    })
                }
            }
            findViewById<TextView>(R.id.okTv).setOnClickListener {
                dialogCallback.onClick(floatArray = floatArray)
                dismiss()
            }
            findViewById<TextView>(R.id.cancelTv).setOnClickListener {
                dismiss()
            }
            show()
        }
    }

    private fun getRgbFromHex(hex: String): FloatArray {
        val initColor = Color.parseColor(hex)
        val r = Color.red(initColor)
        val g = Color.green(initColor)
        val b = Color.blue(initColor)
        return floatArrayOf(r.toFloat(), g.toFloat(), b.toFloat(), 1.0f)
    }

    private fun getRgbFromHex(initColor: Int): FloatArray {
        val r = Color.red(initColor)
        val g = Color.green(initColor)
        val b = Color.blue(initColor)
        return floatArrayOf(r.toFloat(), g.toFloat(), b.toFloat(), 1.0f)
    }

    private fun initPaintType() {
        binding.paintTypeContainer.visibility = View.VISIBLE
        binding.shapesTypeContainer.visibility = View.GONE
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

    private fun initializePicker(
        textView: MaterialTextView? = null,
        callback: ColorPicker.OnChooseColorListener? = null
    ) {
        ColorPicker(this).apply {
            setOnFastChooseColorListener(object :
                ColorPicker.OnFastChooseColorListener {
                override fun setOnFastChooseColorListener(position: Int, color: Int) {
                    if (textView != null) {
                        textView.apply {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                backgroundTintList = ColorStateList.valueOf(color)
                            }
                            text = String.format("#%06X", 0xFFFFFF and color)
                            callback?.onChooseColor(position, color)
                        }
                    } else
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

    override fun onPause() {
        super.onPause()
        if (type == SHAPES_TYPE) {
            binding.glSurfaceView.onPause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (type == SHAPES_TYPE) {
            binding.glSurfaceView.onResume()
        }
    }
}