package com.jlgames.rytakka.peli.skene;

import android.opengl.GLES20;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;

public class PeliRuutu {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();

    public static void renderöi() {
        try {
            shader.bind();
            Assets.annaTekstuuri("tausta").bind(0);
            taustaKomponentti.piirrä(shader);

            for (Pelaaja p : Peli.pelaajat) {
                p.rakennelma().piirrä(shader);
                for (Pelihahmo hahmo : p.hahmotKentällä) {
                    hahmo.piirrä(shader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
