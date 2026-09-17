package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.Teksti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Pelaaja;
import com.jlgames.rytakka.peli.Peli;
import com.jlgames.rytakka.peli.hahmot.Isojäbä;
import com.jlgames.rytakka.peli.hahmot.Juuso;
import com.jlgames.rytakka.peli.hahmot.Lepakkojäbä;
import com.jlgames.rytakka.peli.hahmot.Luujäbä;
import com.jlgames.rytakka.peli.hahmot.Mailajäbä;
import com.jlgames.rytakka.peli.hahmot.Muskelijäbä;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.hahmot.Piikkipallojäbä;
import com.jlgames.rytakka.peli.hahmot.Päällikköjäbä;
import com.jlgames.rytakka.peli.hahmot.Rynnäkköjäbä;
import com.jlgames.rytakka.peli.hahmot.Taistelija;
import com.jlgames.rytakka.peli.hahmot.Tikkujäbä;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;
import com.jlgames.rytakka.peli.toiminnot.Toiminnot;

public class HUD {

    private static Shader shader = new Shader();
    // Nää kannattaa varmaan laittaa useaan luokkaan sit kun näitä rupeaa tuleen enemmän.
    private static HUDKomponentti hudYläPohja = new HUDKomponentti(1, -1/8f, 0, 7/8f, Assets.annaTekstuuri("hud_pohja"));
    private static HUDKomponentti hudAlaPohja = new HUDKomponentti(1, 1/8f, 0, -7/8f, Assets.annaTekstuuri("hud_pohja"));
    private static HUDKomponentti rahaKuvakeLabel = new HUDKomponentti(1/12f, 1/12f, -6/12f, 7/8f, Assets.annaTekstuuri("hud_raha"));
    private static Teksti rahaTeksti = new Teksti("raha", 100, 42);
    private static HUDKomponentti rahaTekstiLabel = new HUDKomponentti(1/12f, 1/12f, -4/12f, 7/8f, rahaTeksti);
    private static HUDKomponentti hahmotKuvakeLabel = new HUDKomponentti(1/12f, 1/12f, -2/12f, 7/8f, Assets.annaTekstuuri("taistelija"));
    private static Teksti hahmotTeksti = new Teksti("hahmot", 100, 42);
    private static HUDKomponentti hahmotTekstiLabel = new HUDKomponentti(1/12f, 1/12f, 0, 7/8f, hahmotTeksti);
    private static Nappi koulutaHahmojaNappi = new Nappi(1/10f, 1/10f, -1/2f, -7/8f,  Assets.annaTekstuuri("hud_nappi_kouluta"));
    private static Nappi takaisinNappi = new Nappi(1/14f, 1/10f, -11/14f, -7/8f,  Assets.annaTekstuuri("hud_nappi_takaisin"));
    private static Nappi ostaTaistelijaPainike = new Nappi(1/14f, 1/10f, -8/14f, -7/8f,  Assets.annaTekstuuri("taistelija"));
    private static Nappi ostaTikkujäbäPainike = new Nappi(1/14f, 1/10f, -6/14f, -7/8f,  Assets.annaTekstuuri("Tikkujäbä"));
    private static Nappi ostaLuujäbäPainike = new Nappi(1/14f, 1/10f, -4/14f, -7/8f,  Assets.annaTekstuuri("Luujäbä"));
    private static Nappi ostaMailajäbäPainike = new Nappi(1/14f, 1/10f, -2/14f, -7/8f,  Assets.annaTekstuuri("Mailajäbä"));
    private static Nappi ostaIsojäbäPainike = new Nappi(1/14f, 1/10f, 0, -7/8f,  Assets.annaTekstuuri("Isojäbä"));
    private static Nappi ostaRynnäkköjäbäPainike = new Nappi(1/14f, 1/10f, 2/14f, -7/8f,  Assets.annaTekstuuri("Rynnäkköjäbä"));
    private static Nappi ostaLepakkojäbäPainike = new Nappi(1/14f, 1/10f, 4/14f, -7/8f,  Assets.annaTekstuuri("Lepakkojäbä"));
    private static Nappi ostaPiikkipallojäbäPainike = new Nappi(1/14f, 1/10f, 6/14f, -7/8f,  Assets.annaTekstuuri("Piikkipallojäbä"));
    private static Nappi ostaMuskelijäbäPainike = new Nappi(1/14f, 1/10f, 8/14f, -7/8f,  Assets.annaTekstuuri("Muskelijäbä"));
    private static Nappi ostaPäällikköjäbäPainike = new Nappi(1/14f, 1/10f, 10/14f, -7/8f,  Assets.annaTekstuuri("Päällikköjäbä"));
    private static Nappi ostaJuusoPainike = new Nappi(1/14f, 1/10f, 12/14f, -7/8f,  Assets.annaTekstuuri("Juuso_lippis"));
    private static HUDKomponentti ostoPainikePohja = new HUDKomponentti(1/14f, 1/10f, -1/2f, -7/8f,  Assets.annaTekstuuri("hud_ostopainike_tyhjä"));
    private static Nappi päivitäRakennelmaNappi = new Nappi(1/10f, 1/10f, -2/8f, -7/8f,  Assets.annaTekstuuri("hud_nappi_päivitä_rakennelma"));
    private static Nappi hyökkääNappi = new Nappi(1/10f, 1/10f, -1/2f, -7/8f,  Assets.annaTekstuuri("hud_nappi_hyökkää"));

    public static enum Valikot {
        RAKENNELMA_OMA_PÄÄVALIKKO,
        RAKENNELMA_OMA_HAHMOT,
        RAKENNELMA_OMA_PÄIVITYS,
        RAKENNELMA_VIHOLLINEN_PÄÄVALIKKO;
    }
    public static Valikot hudValikko = Valikot.RAKENNELMA_OMA_PÄÄVALIKKO;

    public static void tarkistaKosketus(float x, float y, float leveys, float korkeus) {
        // Tähän kaikki HUD-valikoiden ja nappien toiminnot.
        switch (hudValikko) {
            case RAKENNELMA_OMA_PÄÄVALIKKO:
                if (koulutaHahmojaNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    hudValikko = Valikot.RAKENNELMA_OMA_HAHMOT;
                }
                else if (päivitäRakennelmaNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    hudValikko = Valikot.RAKENNELMA_OMA_PÄIVITYS;
                }
            break;
            case RAKENNELMA_OMA_HAHMOT:
                if (takaisinNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    hudValikko = Valikot.RAKENNELMA_OMA_PÄÄVALIKKO;
                }
                else if (ostaTaistelijaPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Taistelija(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaTikkujäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Tikkujäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaLuujäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Luujäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaMailajäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Mailajäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaIsojäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Isojäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaRynnäkköjäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Rynnäkköjäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaLepakkojäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Lepakkojäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaPiikkipallojäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Piikkipallojäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaMuskelijäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Muskelijäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaPäällikköjäbäPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Päällikköjäbä(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
                else if (ostaJuusoPainike.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Pelihahmo h = new Juuso(0);
                    if (Peli.pelaajat.get(0).raha() >= h.annaHinta()) { // Hardkoodattu. Mietitään tiimien toimintaa paremmin.
                        Peli.pelaajat.get(0).lisääRaha(-h.annaHinta());
                        Peli.pelaajat.get(0).lisääHahmo(h);
                    }
                }
            break;
            case RAKENNELMA_OMA_PÄIVITYS:
                if (takaisinNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    hudValikko = Valikot.RAKENNELMA_OMA_PÄÄVALIKKO;
                }
            break;
            case RAKENNELMA_VIHOLLINEN_PÄÄVALIKKO:
                if (hyökkääNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
                    Toiminnot.hyökkää(x, y, leveys, korkeus);
                }
            break;
        }
    }

    public static void renderöiHUD() {
        try {
            shader.bind();

            // Renderöi näytön yläosan HUD
            hudYläPohja.piirrä(shader);
            rahaKuvakeLabel.piirrä(shader);
            rahaTeksti.päivitäTeksti("" + Peli.pelaajat.get(0).raha());
            rahaTekstiLabel.piirrä(shader);
            hahmotKuvakeLabel.piirrä(shader);
            hahmotTeksti.päivitäTeksti("" + Peli.pelaajat.get(0).hahmotKentällä.size());
            hahmotTekstiLabel.piirrä(shader);

            // Renderöi näytön alaosan HUD
            if (Peli.valittuObjekti != null) {
                hudAlaPohja.piirrä(shader);
                if (Peli.valittuObjekti instanceof Rakennelma) {
                    switch (hudValikko) {
                        case RAKENNELMA_OMA_PÄÄVALIKKO:
                            koulutaHahmojaNappi.piirrä(shader);
                            päivitäRakennelmaNappi.piirrä(shader);
                        break;
                        case RAKENNELMA_OMA_HAHMOT:
                            takaisinNappi.piirrä(shader);
                            // Renderöi Taistelijan ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaTaistelijaPainike.offsetX(), ostaTaistelijaPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaTaistelijaPainike.piirrä(shader);
                            // Renderöi Tikkujäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaTikkujäbäPainike.offsetX(), ostaTikkujäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaTikkujäbäPainike.piirrä(shader);
                            // Renderöi Luujäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaLuujäbäPainike.offsetX(), ostaLuujäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaLuujäbäPainike.piirrä(shader);
                            // Renderöi Mailajäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaMailajäbäPainike.offsetX(), ostaMailajäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaMailajäbäPainike.piirrä(shader);
                            // Renderöi Isojäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaIsojäbäPainike.offsetX(), ostaIsojäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaIsojäbäPainike.piirrä(shader);
                            // Renderöi Rynnäkköjäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaRynnäkköjäbäPainike.offsetX(), ostaRynnäkköjäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaRynnäkköjäbäPainike.piirrä(shader);
                            // Renderöi Lepakkojäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaLepakkojäbäPainike.offsetX(), ostaLepakkojäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaLepakkojäbäPainike.piirrä(shader);
                            // Renderöi Piikkipallojäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaPiikkipallojäbäPainike.offsetX(), ostaPiikkipallojäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaPiikkipallojäbäPainike.piirrä(shader);
                            // Renderöi Muskelijäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaMuskelijäbäPainike.offsetX(), ostaMuskelijäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaMuskelijäbäPainike.piirrä(shader);
                            // Renderöi Päällikköjäbän ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaPäällikköjäbäPainike.offsetX(), ostaPäällikköjäbäPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaPäällikköjäbäPainike.piirrä(shader);
                            // Renderöi Juuson ostopainike ja pohjatekstuuri
                            ostoPainikePohja.päivitäSijainti(ostaJuusoPainike.offsetX(), ostaJuusoPainike.offsetY());
                            ostoPainikePohja.piirrä(shader);
                            ostaJuusoPainike.piirrä(shader);
                        break;
                        case RAKENNELMA_OMA_PÄIVITYS:
                            takaisinNappi.piirrä(shader);
                        break;
                        case RAKENNELMA_VIHOLLINEN_PÄÄVALIKKO:
                            hyökkääNappi.piirrä(shader);
                        break;
                    }
                }
            }
            if (Peli.peliOhi) {
                VoittoSplash.renderöiVoittoSplash(shader);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
