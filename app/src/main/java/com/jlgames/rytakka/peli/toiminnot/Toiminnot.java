package com.jlgames.rytakka.peli.toiminnot;

import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;

public class Toiminnot {

    public static void kosketusToiminto(float x, float y, float leveys, float korkeus) {
        // Tähän jotain logiikkaa, jolla valitaan, mitä tehdään missäkin pelin vaiheessa.
        float kohdeX = haeRuutuKoordinaatti(x, leveys);
        float kohdeY = haeRuutuKoordinaatti(korkeus-y, korkeus);
        for (Pelaaja p : Peli.pelaajat) {
            if (!p.botti) {
                if (p.rakennelma().tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Peli.valittuObjekti = p.rakennelma();
                    System.out.println("valittu: " + p.rakennelma() + ", hp: " + p.rakennelma().annaHp());
                    break;
                }
                for (Pelihahmo hahmo : p.hahmot()) {
                    if (hahmo.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                        Peli.valittuObjekti = hahmo;
                        System.out.println("valittu: " + hahmo);
                        break;
                    }
                }
            }
            else {
                if (p.rakennelma().tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    siirräHahmoja(kohdeX, kohdeY);
                    Peli.valittuObjekti = p.rakennelma();
                    p.rakennelma().vahingoita(1);
                    System.out.println("vihollisrakennelman hp: " + p.rakennelma().annaHp());
                    break;
                }
            }
        }
    }

    public static void siirräHahmoja(float x, float y) {
        for (Pelaaja p : Peli.pelaajat) {
            if (!p.botti) {
                for (Pelihahmo hahmo : p.hahmotKentällä) {
                    hahmo.asetaKohde(x, y);
                }
            }
        }
    }

    private static float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
