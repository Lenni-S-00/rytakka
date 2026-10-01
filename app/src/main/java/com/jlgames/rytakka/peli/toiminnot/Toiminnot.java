package com.jlgames.rytakka.peli.toiminnot;

import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;

// Luokka yleisimpiä toimintoja varten
public class Toiminnot {

    // Pelaajan hyökkäys
    public static void hyökkää(float klikkausX, float klikkausY, float ruudunLeveys, float ruudunKorkeus) {
        if (Peli.valittuObjekti instanceof Rakennelma) {
            Rakennelma rakennelma = (Rakennelma) Peli.valittuObjekti;
            rakennelma.hyökkää(0);
            float kohdeX = rakennelma.offsetX();
            float kohdeY = rakennelma.offsetY();
            siirräHahmoja(kohdeX, kohdeY);
            Peli.valittuObjekti = null;
        }
    }

    // Boteille oma hyökkäyslogiikka
    public static void bottiHyökkäys(Pelaaja botti, Rakennelma kohde) {

        if (kohde == null) {
            return;
        }
        kohde.hyökkää(botti.tiimi);
        float kohdeX = kohde.offsetX();
        float kohdeY = kohde.offsetY();

        siirräBotinHahmoja(botti, kohdeX, kohdeY);
    }

    // Logiikka hahmojen kimppuun hyökkäämiselle ja hyökkäyksen kohteen valinnalle
    public static void hyökkääVihollisHahmoihin(int hyökkääjä, int kohde) {
        Pelaaja h = Peli.pelaajat.get(hyökkääjä);
        Pelaaja p = Peli.pelaajat.get(kohde);
        for (Pelihahmo hahmo : h.hahmotKentällä) {
            // Paranna tätä niin, että hyökkääjä etsii lähimmän kohteen
            for (int i = 0; i < p.hahmotKentällä.size(); i++) {
                if (p.hahmotKentällä.get(i).annaHP() > 0) {
                    float kohdeX = p.hahmotKentällä.get(i).offsetX();
                    float kohdeY = p.hahmotKentällä.get(i).offsetY();
                    hahmo.asetaKohde(kohdeX, kohdeY);
                    break;
                }
            }
        }
    }

    // Pelaajan hahmojen siirto
    public static void siirräHahmoja(float x, float y) {
        for (Pelaaja p : Peli.pelaajat) {
            if (!p.botti) {
                for (Pelihahmo hahmo : p.hahmotKentällä) {
                    hahmo.asetaKohde(x, y);
                }
            }
        }
    }

    // Bottien hahmojen siirto
    private static void siirräBotinHahmoja(Pelaaja botti, float x, float y) {
        for (Pelihahmo hahmo : botti.hahmotKentällä) {
            hahmo.asetaKohde(x, y);
        }
    }

    // Saatetaan tarvita myöhemmin.
    private static float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
