package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.engine.RevenueCatManager;
import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;
import com.jlgames.rytakka.engine.media.Äänet;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.hahmot.Tikkujäbä;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;
import com.jlgames.rytakka.peli.toiminnot.Toiminnot;

import java.util.ArrayList;

public class Peli {

    public static ArrayList<Pelaaja> pelaajat = new ArrayList<>(); // Varmaan aina 4 pelaajaa mut teoriassa voi olla enemmän
    public static KlikattavaObjekti valittuObjekti; // Vain 1 asia kerrallaan voi olla valittu. Toiminnot tehdään sen perusteella.
    public static boolean peliOhi = false;
    public static boolean peliAloitettu = false;
    public static boolean pause = false;
    public static int voittaja = -1; // Voittanut tiimi (-1: voittaja = world)
    public static int peliTick = 0;
    public static boolean skinitAvattu = false;
    public static boolean kauppaKlikattu = false;
    private static ArrayList<VihollisAI> vihollisAIt; // Bottien tekoälyä varten

    public static enum Skene {
        PÄÄVALIKKO,
        PELI,
        OHJERUUTU,
        KEHITTÄJÄRUUTU;
    }
    public static Skene skene = Skene.PÄÄVALIKKO;

    // Annetaan pelille revenueCatManager pelinsisäisiä ostoja varten.
    public Peli(RevenueCatManager revenueCatManager) {
    }

    public static void luoPeli() {
        // Jotain tarvittavia alkusäätöjä ennen kuin siirrytään pelisilmukkaan.
        Assets.createTextures();
        Äänet.toistaMusa("keimo_valikko");
        nollaaPeli();
    }

    public static void nollaaPeli() {
        pelaajat.clear();
        // Luodaan 1 pelaaja ja 3 bottia. Annetaan niille alkurakennelma, alkuraha ja 1 tikkujäbä
        for (int i = 0; i < 4; i++) {
            boolean botti;
            if (i == 0) botti = false;
            else botti = true;
            Pelaaja p = new Pelaaja(i, botti);
            p.lisääHahmo(new Tikkujäbä(i));
            p.lisääRaha(50);
            pelaajat.add(p);
        }
        // Lisätään boteille yksinkertainen tekoäly.
        vihollisAIt = new ArrayList<>();
        for (Pelaaja p : pelaajat) {
            if (p.botti) {
                vihollisAIt.add(new VihollisAI(p, pelaajat));
            }
        }
    }

    public static void peliLoop() {
        // Pelisilmukka
        tarkistaPelinTila();
        if (!peliOhi && peliAloitettu && !pause) {
            for (Pelaaja p : pelaajat) {
                for (Pelihahmo hahmo : new ArrayList<>(p.hahmotKentällä)) {
                    if (hahmo.annaHP() > 0) {
                        hahmo.liikuKohteeseen();
                    }
                }
            }
            if (peliTick % 5 == 0) { // Vihollisen hakusykli
                for (Pelaaja p : pelaajat) {
                    if (p.rakennelma().hyökkääjä() > -1) {
                        // Puolusta rakennelmaa hyökkäämällä vihollisen hahmoihin takaisin.
                        Toiminnot.hyökkääVihollisHahmoihin(p.rakennelma().tiimi(), p.rakennelma().hyökkääjä());
                        // Jos hyökkääjän kaikki hahmot ovat kuolleet, lopeta "puolustushyökkäys".
                        if (pelaajat.get(p.rakennelma().hyökkääjä()).hahmotKentällä.isEmpty()) {
                            p.rakennelma().hyökkää(-1);
                        }
                    }
                }
                // Kerää pois kuolleet viholliset.
                for (Pelaaja p : pelaajat) {
                    p.hahmotKentällä.removeIf(hahmo -> hahmo.annaHP() <= 0 && hahmo.annaKuolemaAjastin() <= 0);
                }
            }
            if (peliTick % 60 == 0) { // Tuotantosykli
                lisääRahat();
            }
            if (peliTick % 60 == 0) { // Vahingoitussykli
                boolean voiVahingoittaaRakennelmaa = true; // Ensin vahingoitetaan aina hahmoja, sitten rakennelmia.
                // Jos hahmon hitboxin sisällä on vihollisen hahmo, suorita hahmoille kaksintaistelu.
                for (Pelaaja p : pelaajat) {
                    for (Pelihahmo p1Hahmo : p.hahmotKentällä) {
                        for (Pelaaja hahmonTarkistusPelaaja : pelaajat) {
                            for (Pelihahmo p2Hahmo : hahmonTarkistusPelaaja.hahmotKentällä) {
                                if (!p.equals(hahmonTarkistusPelaaja)) {
                                    if (p1Hahmo.kohdeHitboxinSisällä(p2Hahmo.offsetX(), p2Hahmo.offsetY()) && p2Hahmo.tiimi() != p1Hahmo.tiimi()) {
                                        hahmojenKaksintaistelu(p1Hahmo, p2Hahmo);
                                        voiVahingoittaaRakennelmaa = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                // Jos rakennelman hitboxin sisällä on vihollisen hahmo, tee jatkuvasti vahinkoa.
                if (voiVahingoittaaRakennelmaa) {
                    for (Pelaaja p : pelaajat) {
                        Rakennelma r = p.rakennelma();
                        for (Pelaaja hahmonTarkistusPelaaja : pelaajat) {
                            for (Pelihahmo hahmo : hahmonTarkistusPelaaja.hahmotKentällä) {
                                if (r.kohdeHitboxinSisällä(hahmo.offsetX(), hahmo.offsetY()) && hahmo.tiimi() != r.tiimi()) {
                                    r.vahingoita(hahmo.annaDmg());
                                }
                            }
                        }
                    }
                }
            }
            // Vihollisen toiminto joka 400. ticki (paitsi ensimmäinen tick)
            if (peliTick % 400 == 0 && peliTick != 0){
                for (VihollisAI ai : vihollisAIt) {
                    ai.päivitä();
                }}
            peliTick++;
        }

    }

    private static void hahmojenKaksintaistelu(Pelihahmo hahmo1, Pelihahmo hahmo2) {
        if (hahmo1.annaHP() > 0 && hahmo2.annaHP() > 0) {
            hahmo1.vahingoita(hahmo2.damage);
            hahmo2.vahingoita(hahmo1.damage);
        }
    }

    // Jos pelaajan linna on olemassa (hp > 0), lisätään rahaa tuoton verran.
    private static void lisääRahat() {
        for (Pelaaja p : pelaajat) {
            if (p.rakennelma().annaHp() > 0) {
                p.lisääRaha(p.rakennelma().annaTuotto());
                for (int i = 0; i < p.rakennelma().kaivokset(); i++) {
                    p.lisääRaha(1);
                }
            }
        }
    }

    // Pelataan, kunnes vain yksi on jäljellä.
    // Voisi päivittää häviöön, kun pelaajan linna tuhotaan.
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
