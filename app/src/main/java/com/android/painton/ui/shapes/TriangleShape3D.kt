package com.android.painton.ui.shapes

import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.opengles.GL10

class TriangleShape3D {
    private val verBuffer: FloatBuffer
    private val inBuffer: ByteBuffer
    private val colBuffer: FloatBuffer
    private val ver = floatArrayOf(
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        0.0f, 1.0f, 0.0f
    )
    private var colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 1.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f
    )
    private val ind = byteArrayOf(
        2, 4, 3,
        1, 4, 2,
        0, 4, 1,
        4, 0, 3
    )

    fun setColor(arrOfColors: FloatArray?) {
        arrOfColors?.let {
            if (!colors.contentEquals(arrOfColors)) {
                colors = arrOfColors
            }
        }
    }

    fun draw(gl: GL10) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, verBuffer)
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
        gl.glColorPointer(4, GL10.GL_FLOAT, 0, colBuffer)
        gl.glDrawElements(GL10.GL_TRIANGLES, ind.size, GL10.GL_UNSIGNED_BYTE, inBuffer)
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY)
    }

    init {
        val vbb = ByteBuffer.allocateDirect(ver.size * 4)
        vbb.order(ByteOrder.nativeOrder())
        verBuffer = vbb.asFloatBuffer()
        verBuffer.put(ver)
        verBuffer.position(0)
        inBuffer = ByteBuffer.allocateDirect(ind.size)
        inBuffer.put(ind)
        inBuffer.position(0)
        val cb = ByteBuffer.allocateDirect(colors.size * 4)
        cb.order(ByteOrder.nativeOrder())
        colBuffer = cb.asFloatBuffer()
        colBuffer.put(colors)
        colBuffer.position(0)
    }
}