package com.jlgames.rytakka.peli.toiminnot;

import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;
import com.jlgames.rytakka.peli.skene.HUD;

public class Toiminnot {

    public static void kosketusToiminto(float x, float y, float leveys, float korkeus) {
        // Tähän jotain logiikkaa, jolla valitaan, mitä tehdään missäkin pelin vaiheessa.
        if (!Peli.peliOhi) {
            HUD.tarkistaKosketus(x, y, leveys, korkeus);
            float kohdeX = haeRuutuKoordinaatti(x, leveys);
            float kohdeY = haeRuutuKoordinaatti(korkeus - y, korkeus);
            boolean tyhjäValittu = !(kohdeY < -3 / 4f || kohdeY > 3 / 4f);
            for (Pelaaja p : Peli.pelaajat) {
                if (!p.botti) {
                    if (p.rakennelma().tarkistaKlikkaus(x, y, leveys, korkeus)) {
                        if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(p.rakennelma())) {
                            Peli.valittuObjekti = null;
                        } else {
                            Peli.valittuObjekti = p.rakennelma();
                            HUD.hudValikko = HUD.Valikot.RAKENNELMA_OMA_PÄÄVALIKKO;
                            tyhjäValittu = false;
                        }
                        break;
                    }
                    for (Pelihahmo hahmo : p.hahmot()) {
                        if (hahmo.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                            if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(hahmo)) {
                                Peli.valittuObjekti = null;
                            } else {
                                Peli.valittuObjekti = hahmo;
                            }
                            break;
                        }
                    }
                } else {
                    if (p.rakennelma().tarkistaKlikkaus(x, y, leveys, korkeus)) {
                        if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(p.rakennelma())) {
                            Peli.valittuObjekti = null;
                        } else {
                            Peli.valittuObjekti = p.rakennelma();
                            HUD.hudValikko = HUD.Valikot.RAKENNELMA_VIHOLLINEN_PÄÄVALIKKO;
                            tyhjäValittu = false;
                        }

                        break;
                    }
                }
            }
            if (tyhjäValittu) Peli.valittuObjekti = null;
        }
    }

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
