package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.Syöte;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;

public class PeliRuutu {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();

    public static void tarkistaKosketus(float x, float y, float leveys, float korkeus) {
        if (!Peli.peliOhi) {
            float kohdeX = Syöte.haeRuutuKoordinaatti(x, leveys);
            float kohdeY = Syöte.haeRuutuKoordinaatti(korkeus - y, korkeus);
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
