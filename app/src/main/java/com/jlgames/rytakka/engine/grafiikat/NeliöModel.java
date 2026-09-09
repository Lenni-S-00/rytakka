package com.jlgames.rytakka.engine.grafiikat;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Neliötä mallintava OpenGL-graafinen objekti,
 * joka luodaan neljästä verteksistä, kahdesta kolmiosta ja
 * tekstuurikoordinaateista. Kaikki grafiikat on tarkoitus piirtää
 * NeliöModel-objektien muodossa vapaasti tekstuureja käyttämällä
 */
public class NeliöModel {

    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;
    private FloatBuffer texCoordBuffer;

    private int positionHandle;
    private int colorHandle;
    private int textureHandle;
    private int texCoordHandle;
    private final int vertexCount = squareCoords.length / COORDS_PER_VERTEX;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex



    // Use to access and set the view transformation
    private int vPMatrixHandle;



    // Teoriassa kaikilla vertekseillä on kolmiulotteiset koordinaatit,
    // vaikka ne piirretään 2D-tasoon ja Z on aina 0.
    static final int COORDS_PER_VERTEX = 3;
    static float[] squareCoords = { // verteksikoordinaatit
            -1f,  1f, 0f,   // ylävasen 0
            -1f, -1f, 0f,   // alavasen 1
            1f, -1f, 0f,   // alaoikea 2
            1f,  1f, 0f     // yläoikea 3
    };

    private short[] drawOrder = { // verteksien piirtojärjestys
            0, 1, 2, // kolmio 1: ylävasen, alavasen, alaoikea
            0, 2, 3  // kolmio 2: ylävasen, alaoikea, yläoikea
    };

    float[] texture = { // tekstuurikoordinaatit (näitä muuttamalla tekstuuria voi kääntää ja peilata ym.)
            0, 0,
            0, 1,
            1, 1,
            1, 0
    };

    public NeliöModel() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 4 bytes per float)
                squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);
        GLES20.glGenBuffers(0, bb.asIntBuffer());

        // initialize byte buffer for the draw list
        ByteBuffer dlb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);
        GLES20.glGenBuffers(1, dlb.asIntBuffer());

        ByteBuffer tcb = ByteBuffer.allocateDirect(
                // (# of coordinate values * 2 bytes per short)
                texture.length * 4);
        tcb.order(ByteOrder.nativeOrder());
        texCoordBuffer = tcb.asFloatBuffer();
        texCoordBuffer.put(texture);
        texCoordBuffer.position(0);
        GLES20.glGenBuffers(2, tcb.asIntBuffer());
    }

    public void draw() {
        // Add program to OpenGL ES environment
        //GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        //int positionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        //int texCoordHandle = GLES20.glGetAttribLocation(mProgram, "tPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(0);
        GLES20.glEnableVertexAttribArray(1);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(0, COORDS_PER_VERTEX, GLES20.GL_FLOAT, false, vertexStride, vertexBuffer);

        GLES20.glVertexAttribPointer(1, 2, GLES20.GL_FLOAT, false, 0, texCoordBuffer);


        //textureHandle = GLES20.glGetUniformLocation(mProgram, "sampler");

        //GLES20.glUniform1i(textureHandle, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length, GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(0);
        GLES20.glDisableVertexAttribArray(1);
    }
}

