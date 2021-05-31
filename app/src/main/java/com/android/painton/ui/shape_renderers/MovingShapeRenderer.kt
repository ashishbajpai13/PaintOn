package com.android.painton.ui.shape_renderers

import android.opengl.GLSurfaceView
import android.opengl.GLU
import com.android.painton.ui.shapes.MovingSquare
import com.android.painton.ui.shapes.MovingTriangle
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class MovingShapeRenderer : GLSurfaceView.Renderer {
    var x = 0
    var y = 0
    var movingTriangle: MovingTriangle = MovingTriangle()
    private var angle_triangle = 0.0f
    private val speed_triangle = 1.1f
    var movingSquare: MovingSquare = MovingSquare()
    private var angle_square = 0.0f
    private val speed_square = 1.1f

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
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
        GLU.gluPerspective(gl, 100f, aspect, 0.1f, 100f)
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
    }

    override fun onDrawFrame(gl: GL10) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        gl.glLoadIdentity()
        if (x == 0) {
            gl.glTranslatef(3.0f, 2.0f, -11.0f)
            gl.glRotatef(angle_triangle, 0.0f, 1.0f, 0.3f)
            x += 1
            if (x == 1) {
                gl.glTranslatef(0.3f, 1.0f, -7.0f)
                gl.glRotatef(angle_triangle, 1.0f, 1.0f, 0.3f)
                x = 0
            }
        }
        movingTriangle.draw(gl)
        angle_triangle += speed_triangle
        gl.glLoadIdentity()
        if (x == 0) {
            gl.glTranslatef(0.0f, -8.0f, -10.0f)
            gl.glRotatef(angle_square, 1.0f, 0.0f, -10f)
            x += 1
            if (x == 1) {
                gl.glTranslatef(0.0f, 13.0f, -10.0f)
                gl.glRotatef(angle_square, 1.0f, 0.0f, -10.0f)
                x = 0
            }
        }
        movingSquare.draw(gl)
        angle_square += speed_square
        gl.glLoadIdentity()
        gl.glPushMatrix()
        gl.glTranslatef(0.0f, 0.0f, -10.0f)
        gl.glRotatef(angle_square, 0.1f, 1.0f, 0.3f)
        movingSquare.draw(gl)
        angle_square += speed_square
        gl.glPopMatrix()
        gl.glLoadIdentity()
        if (x == 0) {
            gl.glTranslatef(2.0f, -5.0f, -10.0f)
            gl.glRotatef(angle_triangle, 3.1f, 1.0f, 0.5f)
            x += 1
            if (x == 1) {
                gl.glTranslatef(3.0f, -6.0f, -8.0f)
                gl.glRotatef(angle_square, 3.1f, 1.0f, 0.3f)
                x = 0
            }
        }
        movingTriangle.draw(gl)
        angle_triangle += speed_triangle
        gl.glLoadIdentity()
        if (x == 0) {
            gl.glTranslatef(0.0f, 1.0f, -6.0f)
            gl.glRotatef(angle_square, 0.0f, 1.0f, 0.1f)
            x += 1
            if (x == 1) {
                gl.glTranslatef(1.1f, 0.1f, -8.0f)
                gl.glRotatef(angle_square, 1.1f, 1.0f, 1.1f)
                x = 0
            }
        }
        movingSquare.draw(gl)
        angle_square += speed_square
    }
}