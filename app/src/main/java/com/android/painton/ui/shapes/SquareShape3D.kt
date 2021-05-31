package com.android.painton.ui.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class SquareShape3D {

    private val verBuffer: FloatBuffer
    private val num = 6
    private var colors = listOf(
        floatArrayOf(1.0f, 0.0f, 0.0f, 1.0f),
        floatArrayOf(0.0f, 1.0f, 0.0f, 1.0f),
        floatArrayOf(1.0f, 0.0f, 1.0f, 1.0f),
        floatArrayOf(0.0f, 0.0f, 1.0f, 1.0f),
        floatArrayOf(1.0f, 0.5f, 0.0f, 1.0f),
        floatArrayOf(1.0f, 1.0f, 0.0f, 1.0f)
    )
    private val ver = floatArrayOf( // FRONT
        -1.0f, -1.0f, 1.0f,  // left-bottom-front
        1.0f, -1.0f, 1.0f,  //  right-bottom-front
        -1.0f, 1.0f, 1.0f,  // left-top-front
        1.0f, 1.0f, 1.0f,  // right-top-front
        // BACK
        1.0f, -1.0f, -1.0f,  // right-bottom-back
        -1.0f, -1.0f, -1.0f,  // left-bottom-back
        1.0f, 1.0f, -1.0f,  // right-top-back
        -1.0f, 1.0f, -1.0f,  // left-top-back
        // LEFT
        -1.0f, -1.0f, -1.0f,  // left-bottom-back
        -1.0f, -1.0f, 1.0f,  // left-bottom-front
        -1.0f, 1.0f, -1.0f,  // left-top-back
        -1.0f, 1.0f, 1.0f,  // left-top-front
        // RIGHT
        1.0f, -1.0f, 1.0f,  // right-bottom-front
        1.0f, -1.0f, -1.0f,  // right-bottom-back
        1.0f, 1.0f, 1.0f,  // right-top-front
        1.0f, 1.0f, -1.0f,  // right-top-back
        // TOP
        -1.0f, 1.0f, 1.0f,  //left-top-front
        1.0f, 1.0f, 1.0f,  // right-top-front
        -1.0f, 1.0f, -1.0f,  //left-top-back
        1.0f, 1.0f, -1.0f,  // right-top-back
        // BOTTOM
        -1.0f, -1.0f, -1.0f,  //left-bottom-back
        1.0f, -1.0f, -1.0f,  //right-bottom-back
        -1.0f, -1.0f, 1.0f,  //left-bottom-front
        1.0f, -1.0f, 1.0f // right-bottom-front
    )

    fun draw(gl: GL10) {
        gl.glFrontFace(GL10.GL_CCW)
        gl.glEnable(GL10.GL_CULL_FACE)
        gl.glCullFace(GL10.GL_BACK)
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verBuffer)
        for (i in 0 until num) {
            gl.glColor4f(
                colors[i][0],
                colors[i][1],
                colors[i][2],
                colors[i][3]
            )
            gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, i * 4, 4)
        }
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisable(GL10.GL_CULL_FACE)
    }

    fun setColor(arrOfColors: List<FloatArray>?) {
        arrOfColors?.let {
            if (!colors.first().contentEquals(arrOfColors.firstOrNull())) {
                colors = arrOfColors
            }
        }
    }

    init {
        val bb = ByteBuffer.allocateDirect(ver.size * 4)
        bb.order(ByteOrder.nativeOrder())
        verBuffer = bb.asFloatBuffer()
        verBuffer.put(ver)
        verBuffer.position(0)
    }
}
