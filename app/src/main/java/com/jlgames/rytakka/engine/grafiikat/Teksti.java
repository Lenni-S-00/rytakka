package com.jlgames.rytakka.engine.grafiikat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import static android.opengl.GLES20.*;

import com.jlgames.rytakka.engine.assets.Assets;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

/**
 * Sama periaate kuin Tekstuuri-objektissa, mutta bitmapin sijaan piirretään kanvakselle
 * tekstiä ja luodaan sen pikselidatasta GL-tekstuuriobjekti.
 * Tekstialueen leveys ja korkeus (eli kuinka paljon tekstiä ja montako riviä)
 * määritellään tässä luokassa. Tekstipohjaista tekstuuria käyttävien komponenttien
 * koko (matriisitransformaatio) määritellään komponenteissa itsessään.
 */
public class Teksti implements Renderöitävä {
    private int id;
    private int leveys;
    private int korkeus;
    private String teksti;
    private IntBuffer textureBuffer;
    private int tekstinLeveys;
    private int tekstinKorkeus;

    public Teksti(String teksti, int leveys, int korkeus) {
        Bitmap bitmap = Bitmap.createBitmap(leveys, korkeus, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);
        this.tekstinLeveys = leveys;
        this.tekstinKorkeus = korkeus;
        this.teksti = teksti;

        Drawable background = new BitmapDrawable();
        background.setBounds(0, 0, leveys, korkeus);
        background.draw(canvas); // draw the background to our bitmap

        Paint textPaint = new Paint();
        textPaint.setTextSize(32);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xFF, 0x00, 0x00, 0x00); // Musta teksti
        canvas.drawColor(Color.argb(0, 255, 255, 255)); // Läpinäkyvä kanvas (ei haluta isoa laatikkoa tekstin taakse)
        canvas.drawText(teksti, 0, korkeus/1.5f, textPaint);

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
                }
                catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                    System.out.println("Texture pixel index out of bounds: " + y + " " + x);
                    e.printStackTrace();
                }

            }
        }

        pixels.flip();
        generateTexture(leveys, korkeus, pixels);

        //Clean up
        bitmap.recycle();
    }

    /**
     * Teksti päivitetään ainoastaan, jos se muuttuu.
     * Aina kun teksti päivitetään, poistetaan nykyinen GL-tekstuuriobjekti,
     * piirretään kanvakselle teksti ja luodaan pikselidatasta uusi GL-tekstuuriobjekti.
     * Tähän olisi varmasti tehokkaampikin menetelmä, mutta tämä toimii, sillä
     * pelissä ei ole paljoa nopeasti vaihtuvia tekstejä
     * @param teksti uusi teksti
     */
    public void päivitäTeksti(String teksti) {
        if (!this.teksti.equals(teksti)) {
            this.teksti = teksti;
            glDeleteTextures(id, textureBuffer);
            Bitmap bitmap = Bitmap.createBitmap(tekstinLeveys, tekstinKorkeus, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(bitmap);
            bitmap.eraseColor(0);

            Drawable background = new BitmapDrawable();
            background.setBounds(0, 0, tekstinLeveys, tekstinKorkeus);
            background.draw(canvas);

            Paint textPaint = new Paint();
            textPaint.setTextSize(32);
            textPaint.setAntiAlias(true);
            textPaint.setARGB(0xFF, 0x00, 0x00, 0x00);
            canvas.drawColor(Color.argb(0, 255, 255, 255));
            canvas.drawText(teksti, 0, tekstinKorkeus / 1.5f, textPaint);

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
                        pixels.put((byte) (pixel & 0xFF)); //BLUE
                        pixels.put((byte) ((pixel >> 24) & 0xFF)); //ALPHA
                    }
                    catch (ArrayIndexOutOfBoundsException | NullPointerException e) {
                        System.out.println("Texture pixel index out of bounds: " + y + " " + x);
                        e.printStackTrace();
                    }

                }
            }

            pixels.flip();
            this.textureBuffer = pixels.asIntBuffer();
            glGenTextures(id, pixels.asIntBuffer());
            glBindTexture(GL_TEXTURE_2D, id);
            glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, leveys, korkeus, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
            glGenerateMipmap(GL_TEXTURE_2D);

            bitmap.recycle();
        }
    }

    private void generateTexture(int width, int height, ByteBuffer buf) {
        id = Assets.textureCount;
        this.textureBuffer = buf.asIntBuffer();
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

