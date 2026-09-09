package com.jlgames.rytakka.engine.grafiikat;

import static android.opengl.GLES20.*;
import android.opengl.GLES20;

/**
 * OpenGL-shaderia mallintava luokka.
 * Shader-objektille syötetään sijaintimatriisi, värivektori sekä tekstuuriluokka,
 * jotka se antaa GLSL-shaderohjelmalle (sijainnin käsittelee vertex shader ja
 * värin ja tekstuurin fragment shader). Niiden pohjalta shader-ohjelma piirtää ruudulle
 * määritetyn graafisen objektin haluttuun paikkaan ja halutulla tavalla.
 */
public class Shader {
    private int vertexShader, fragmentShader, shaderProgram;
    private final int attribVertices = 0, attribTexCoords = 1;

    // Shader-koodit voisi laittaa omiin tiedostoihin, mutta tässä sovelluksessa tuskin
    // tullaan käyttämään kovin monimutkaisia shadereita
    private final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 tPosition;" +
                    "varying vec2 texCoords;" +
                    "void main() {" +
                    "    texCoords = tPosition;" +
                    "    gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    private final String fragmentShaderCode =
            "uniform sampler2D sampler;" +
                    "uniform vec4 vColor;" +
                    "varying vec2 texCoords;" +
                    "void main() {" +
                    "    gl_FragColor = texture2D(sampler, texCoords);" +
                    "}";

    /**
     * Ladataan yllä määritetyt shader-koodit ja luodaan niistä GLSL-shader-ohjelma.
     */
    public Shader() {

        vertexShader = loadShaderFromString(GL_VERTEX_SHADER, vertexShaderCode);
        fragmentShader = loadShaderFromString(GL_FRAGMENT_SHADER, fragmentShaderCode);

        // create empty OpenGL ES Program
        shaderProgram = glCreateProgram();

        // add the vertex shader to program
        glAttachShader(shaderProgram, vertexShader);

        // add the fragment shader to program
        glAttachShader(shaderProgram, fragmentShader);

        glBindAttribLocation(shaderProgram, attribVertices, "vPosition");
        glBindAttribLocation(shaderProgram, attribTexCoords, "tPosition");

        // creates OpenGL ES program executables
        glLinkProgram(shaderProgram);
        glValidateProgram(shaderProgram);

    }

    protected void destroy() throws Throwable {
        glDetachShader(shaderProgram, vertexShader);
        glDetachShader(shaderProgram, fragmentShader);
        glDeleteShader(vertexShader);
        glDeleteShader(fragmentShader);
        glDeleteProgram(shaderProgram);
    }

    public void setLocation(float[] locationMatrix) {
        // get handle to shape's transformation matrix
        int vPMatrixHandle = GLES20.glGetUniformLocation(shaderProgram, "uMVPMatrix");
        // Pass the projection and view transformation to the shader
        GLES20.glUniformMatrix4fv(vPMatrixHandle, 1, false, locationMatrix, 0);
    }

    public void setColor(float[] color) {
        // get handle to fragment shader's vColor member
        int colorHandle = GLES20.glGetUniformLocation(shaderProgram, "vColor");
        // Set color for drawing the triangle
        GLES20.glUniform4fv(colorHandle, 1, color, 0);
    }

    public void setSampler(int value) {
        int location = glGetUniformLocation(shaderProgram, "sampler");
        if (location != -1) {
            glUniform1i(location, value);
        }
    }

    public void bind() {
        glUseProgram(shaderProgram);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public static int loadShaderFromString(int type, String shaderCode) {

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = glCreateShader(type);

        // add the source code to the shader and compile it
        glShaderSource(shader, shaderCode);
        glCompileShader(shader);

        return shader;
    }
}

