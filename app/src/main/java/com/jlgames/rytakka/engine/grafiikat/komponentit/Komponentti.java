package com.jlgames.rytakka.engine.grafiikat.komponentit;

import android.opengl.Matrix;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;

/**
 * Komponetti on yläluokka kaikille tässä sovelluksessa piirrettäville asioille.
 * Komponentit perustuvat matriisitransformaatioihin.
 * Matriisilaskujen tulos annetaan eteenpäin shader-ohjelmalle (ks. Shader.java), joka
 * sitten määrittelee, miten komponentti piirretään.
 * (Identiteettimatriisi viittaa koko kuva-alueeseen eli jos komponentin
 * x-skaala matriisitransformaatiossa on 1/2 identiteettimatriisista, komponentin leveys on
 * puolet näytön leveydestä jne.)
 * Tämän jälkeen otetaan käyttöön (bind) tekstuuriobjekti (ks. Tekstuuri.java), joka määrittää,
 * mitä tavaraa komponentin sisään piirtyy. Lopulta varsinainen piirtäminen tapahtuu valittuun
 * modeliin/polygoniin, eli tässä sovelluksessa vain neliö (ks. NeliöModel.java)
 */
public class Komponentti {
    protected float matrixScaleX = 1f;
    protected float matrixScaleY = 1f;
    protected float matrixOffsetX = 0;
    protected float matrixOffsetY = 0;
    protected float matrixRotX = 0;
    protected float matrixRotY = 0;
    protected float matrixRotZ = 0;
    protected float[] väri = {0f, 0f, 0f, 1f};

    public void piirrä(Shader shader) {

        float[] sijaintiMatriisi = new float[16];
        Matrix.setIdentityM(sijaintiMatriisi, 0);
        Matrix.translateM(sijaintiMatriisi, 0, matrixOffsetX, matrixOffsetY, 0); // siirrä sijaintia (offset)
        Matrix.scaleM(sijaintiMatriisi, 0, matrixScaleX, matrixScaleY, 1); // muuta kokoa (scale)

        // Tässä kohdassa määritellään komponentin kääntö 3d-avaruudessa,
        // eli vähän overkill tässä sovelluksessa mutta laitoin kuitenkin :)
        float[] rotMatrix = new float[16];
        Matrix.setIdentityM(rotMatrix, 0);
        Matrix.setRotateM(rotMatrix, 0, matrixRotX, 1, 0, 0); // käännä x-akselin suhteen (rotX)
        Matrix.setRotateM(rotMatrix, 0, matrixRotY, 0, 1, 0); // käännä y-akselin suhteen (rotY)
        Matrix.setRotateM(rotMatrix, 0, matrixRotZ, 0, 0, 1); // käännä z-akselin suhteen (rotZ)
        Matrix.multiplyMM(sijaintiMatriisi, 0, sijaintiMatriisi, 0, rotMatrix, 0);
        //

        shader.setLocation(sijaintiMatriisi);
        shader.setColor(väri);
        shader.setSampler(0);
        Assets.annaNeliöModel().draw();
    }
}
