package com.android.painton.ui.shape_renderers

import android.opengl.GLSurfaceView
import android.opengl.GLU
import com.android.painton.ui.shapes.SquareShape3D
import com.android.painton.ui.shapes.TriangleShape3D
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class ShapeRenderer3D(type: String? = null, arrOfColors: List<FloatArray>? = null) : GLSurfaceView.Renderer {

    companion object {
        const val SQUARE = "square"
        const val TRIANGLE = "triangle"
        var shapeType: String? = null
        var colors: List<FloatArray>? = listOf()
    }

    init {
        shapeType = type
        colors = arrOfColors
    }

    private var shapes_triangle_3D: TriangleShape3D = TriangleShape3D()

    private var shapes_square_3D: SquareShape3D = SquareShape3D()
    private var angle_square_3D = 0.0f
    private val speed_square_3D = -0.4f
    private var angle_triangle_3D = 0.0f
    private val speed_triangle_3D = -0.4f

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl.glClearColor(255.0f, 255.0f, 255.0f, 1.0f)
        gl.glClearDepthf(1.0f)
        gl.glEnable(GL10.GL_DEPTH_TEST)
        gl.glDepthFunc(GL10.GL_LEQUAL)
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST)
        gl.glShadeModel(GL10.GL_SMOOTH)
        gl.glDisable(GL10.GL_DITHER)
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        var height = height
        if (height == 0) height = 1
        val aspect = width.toFloat() / height
        gl.glViewport(0, 0, width, height)
        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        GLU.gluPerspective(gl, 50f, aspect, 0.1f, 100f)
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
    }

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        when(shapeType) {
            TRIANGLE -> {
                gl.glLoadIdentity()
                gl.glTranslatef(0.0f, 1.0f, -10.0f)
                gl.glRotatef(angle_triangle_3D, 0.0f, 1.0f, 0.3f)
                val arr = (colors?.get(0) ?: floatArrayOf()) + (colors?.get(1) ?: floatArrayOf()) + (colors?.get(2) ?: floatArrayOf())
//                shapes_triangle_3D.setColor(arr)
                shapes_triangle_3D.draw(gl)
                angle_triangle_3D += speed_triangle_3D
            }
            SQUARE -> {
                gl.glLoadIdentity()
                gl.glTranslatef(0.0f, 1.0f, -10.0f)
                gl.glRotatef(angle_square_3D, 0.15f, 1.0f, 0.3f)
//                shapes_square_3D.setColor(colors)
                shapes_square_3D.draw(gl)
                angle_square_3D += speed_square_3D
            }
        }
    }
}