package com.android.painton.ui.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class MovingSquare {
    private val verBuffer: FloatBuffer
    private val colBuffer: FloatBuffer
    private val vt = floatArrayOf(
        -1.0f, -1.0f, 0.0f,
        1.0f, -1.0f, 0.0f,
        -1.0f, 1.0f, 0.0f,
        1.0f, 1.0f, 0.0f
    )
    private val colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 1.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f
    )

    fun draw(gl: GL10) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verBuffer)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colBuffer)
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, vt.size / 3)
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY)
    }

    init {
        val bb = ByteBuffer.allocateDirect(vt.size * 4)
        bb.order(ByteOrder.nativeOrder())
        verBuffer = bb.asFloatBuffer()
        verBuffer.put(vt)
        verBuffer.position(0)
        val cb = ByteBuffer.allocateDirect(colors.size * 4)
        cb.order(ByteOrder.nativeOrder())
        colBuffer = cb.asFloatBuffer()
        colBuffer.put(colors)
        colBuffer.position(0)
    }
}