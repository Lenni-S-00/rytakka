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
    public static boolean peliOhi = false;
    public static int voittaja = -1; // Voittanut tiimi (-1: voittaja = world)
    public static int peliTick = 0;

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
            p.lisääRaha(50);
            pelaajat.add(p);
        }
    }

    public static void peliLoop() {
        // Tähän pelisilmukka
        tarkistaPelinTila();
        if (!peliOhi) {
            for (Pelaaja p : pelaajat) {
                for (Pelihahmo hahmo : p.hahmotKentällä) {
                    hahmo.liikuKohteeseen();
                }
            }

            // Jos rakennelman hitboxin sisällä on vihollisen taistelija, tee jatkuvasti vahinkoa.
            for (Pelaaja p : pelaajat) {
                Rakennelma r = p.rakennelma();
                for (Pelaaja hahmonTarkistusPelaaja : pelaajat) {
                    for (Pelihahmo hahmo : hahmonTarkistusPelaaja.hahmotKentällä) {
                        if (r.kohdeHitboxinSisällä(hahmo.offsetX(), hahmo.offsetY()) && hahmo.tiimi() != r.tiimi()) {
                            if (peliTick % 60 == 0) {
                                r.vahingoita(hahmo.annaDmg());
                            }
                        }
                    }
                }
            }
            peliTick++;
        }
    }

    private static void tarkistaPelinTila() {
        ArrayList<Integer> hävinneetPelaajat = new ArrayList<>();
        for (Pelaaja p : pelaajat) {
            if (p.rakennelma().annaHp() <= 0) hävinneetPelaajat.add(p.rakennelma().tiimi());
        }
        if (hävinneetPelaajat.size() >= pelaajat.size()-1) {
            peliOhi = true;
            if (!hävinneetPelaajat.contains(0)) voittaja = 0;
            else if (!hävinneetPelaajat.contains(1)) voittaja = 1;
            else if (!hävinneetPelaajat.contains(2)) voittaja = 2;
            else if (!hävinneetPelaajat.contains(3)) voittaja = 3;
        }
    }
}
