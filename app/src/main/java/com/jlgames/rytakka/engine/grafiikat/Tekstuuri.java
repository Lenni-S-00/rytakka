package com.jlgames.rytakka.engine.grafiikat;

import static android.opengl.GLES20.*;
import android.graphics.Bitmap;

import com.jlgames.rytakka.engine.assets.Assets;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;


/**
 * OpenGL-tekstuuria mallintava objekti, joka käyttää lähteenä
 * Android Bitmapin pikselidataa.
 * Pitää kirjaa tekstuuri-id:stä (glGenTextures-funktiota varten)
 */
public class Tekstuuri implements Renderöitävä {
    private int id;
    private int leveys;
    private int korkeus;

    public Tekstuuri(Bitmap bitmap) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            if (bitmap != null) bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] image = stream.toByteArray();
            int[] pixels_raw = new int[bitmap.getWidth() * bitmap.getHeight()];
            bitmap.getPixels(pixels_raw, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            leveys = bitmap.getWidth();
            korkeus = bitmap.getHeight();

            ByteBuffer pixels = ByteBuffer.allocateDirect(leveys * korkeus * 4);

            for (int y = 0; y < korkeus; y++) {
                for (int x = 0; x < leveys; x++) {
                    try {
                        int pixel = pixels_raw[y * leveys + x];
                        pixels.put((byte) ((pixel >> 16) & 0xFF)); //RED
                        pixels.put((byte) ((pixel >> 8) & 0xFF)); //GREEN
                        pixels.put((byte) ((pixel >> 0) & 0xFF)); //BLUE
                        pixels.put((byte) ((pixel >> 24) & 0xFF)); //ALPHA

                    } catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Texture pixel index out of bounds: " + y + " " + x);
                        e.printStackTrace();
                    }

                }
            }

            pixels.flip();
            generateTexture(leveys, korkeus, pixels);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Tekstuuri() {
        leveys = 2;
        korkeus = 2;

        int[] pixels_raw = new int[leveys * korkeus * 4];
        pixels_raw[0] = 0xFFFF00FF;
        pixels_raw[1] = 0xFF00FF00;
        pixels_raw[2] = 0xFF00FF00;
        pixels_raw[3] = 0x000000FF;
        ByteBuffer pixels = ByteBuffer.allocateDirect(leveys * korkeus * 4);

        for (int i = 0; i < leveys; i++) {
            for (int j = 0; j < korkeus; j++) {
                try {
                    int pixel = pixels_raw[i * korkeus + j];
                    pixels.put((byte) ((pixel >> 16) & 0xFF)); //RED
                    pixels.put((byte) ((pixel >> 8) & 0xFF)); //GREEN
                    pixels.put((byte) ((pixel >> 0) & 0xFF)); //BLUE
                    pixels.put((byte) ((pixel >> 24) & 0xFF)); //ALPHA
                }
                catch (ArrayIndexOutOfBoundsException aioobe) {
                    System.out.println("Texture pixel index out of bounds: " + i + " " + j);
                    aioobe.printStackTrace();
                }
            }
        }

        pixels.flip();
        generateTexture(leveys, korkeus, pixels);
    }

    private void generateTexture(int width, int height, ByteBuffer buf) {
        id = Assets.textureCount;
        glGenTextures(id, buf.asIntBuffer());
        glBindTexture(GL_TEXTURE_2D, id);
        glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, buf);
        glGenerateMipmap(GL_TEXTURE_2D);
        Assets.textureCount++;
    }

    @Override
    public void bind(int sampler) {
        if (sampler >= 0 && sampler <= 31) {
            glActiveTexture(GL_TEXTURE0 + sampler);
            glBindTexture(GL_TEXTURE_2D, id);
        }
    }
}
