package com.android.painton.ui.customviews

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatDialog


class CustomDialog(context: Context, layout: View) : AppCompatDialog(context) {

    private var view: View? = null

    init {
        view = layout
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view?.let {
            setContentView(it)
        }
    }
}