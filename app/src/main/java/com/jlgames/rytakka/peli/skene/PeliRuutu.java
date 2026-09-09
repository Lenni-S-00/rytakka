package com.jlgames.rytakka.peli.skene;

import android.opengl.GLES20;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;

public class PeliRuutu {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();

    public static void renderöi() {
        try {
            shader.bind();
            GLES20.glBindTexture(0, 0);
            Assets.annaTekstuuri("tausta").bind(0);
            taustaKomponentti.piirrä(shader);

            for (Pelihahmo hahmo : Peli.hahmotKentällä) {
                hahmo.piirrä(shader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
