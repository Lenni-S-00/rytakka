package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;
import com.jlgames.rytakka.engine.media.Äänet;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.hahmot.Taistelija;
import com.jlgames.rytakka.peli.rakennelmat.Linnake;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;
import com.jlgames.rytakka.peli.rakennelmat.Torni;

import java.util.ArrayList;

public class Peli {

    public static ArrayList<Pelaaja> pelaajat = new ArrayList<>(); // Varmaan aina 4 pelaajaa mut teoriassa voi olla enemmän
    public static KlikattavaObjekti valittuObjekti; // Vain 1 asia kerrallaan voi olla valittu. Toiminnot tehdään sen perusteella.

    public static void luoPeli() {
        // Jotain tarvittavia alkusäätöjä ennen kuin siirrytään pelisilmukkaan.
        Assets.createTextures();
        Äänet.toistaMusa("keimo_valikko");
        // Luodaan 1 pelaaja ja 3 bottia. Annetaan niille alkurakennelma, alkuraha ja 1 taistelija
        for (int i = 0; i < 4; i++) {
            boolean botti;
            if (i == 0) botti = false;
            else botti = true;
            Pelaaja p = new Pelaaja(i, botti);
            p.lisääHahmo(new Taistelija(i));
            p.lisääRaha(20);
            pelaajat.add(p);
        }
    }

    public static void peliLoop() {
        // Tähän pelisilmukka
        for (Pelaaja p : pelaajat) {
            for (Pelihahmo hahmo : p.hahmotKentällä) {
                hahmo.liikuKohteeseen();
            }
        }
    }
}
