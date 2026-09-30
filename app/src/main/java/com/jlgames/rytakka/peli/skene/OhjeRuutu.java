package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.Teksti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Peli;

public class OhjeRuutu {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();
    private static Komponentti ohjeOtsikko = new HUDKomponentti(1/4f, 1/8f, 0, 3/10f, new Teksti("Ohjeet", 120, 36));
    private static String ohjeString1 = "Tavoitteenasi on tuhota kaikki vihollisten rakennelmat.";
    private static String ohjeString2 = "Kerää rahaa, kouluta joukkoja, hyökkää vihollisiin";
    private static String ohjeString3 = "ja puolusta omia rakennelmiasi.";
    private static Komponentti ohje1 = new HUDKomponentti(1/2f, 1/12f, 0, 1/10f, new Teksti(ohjeString1, 800, 36));
    private static Komponentti ohje2 = new HUDKomponentti(1/2f, 1/12f, 0, -1/10f, new Teksti(ohjeString2, 800, 36));
    private static Komponentti ohje3 = new HUDKomponentti(1/2f, 1/12f, 0, -3/10f, new Teksti(ohjeString3, 800, 36));
    private static Nappi takaisinNappi = new Nappi(1/2f, 1/10f, 0, -6/10f, Assets.annaTekstuuri("valikko_nappi_takaisin"));

    public static void tarkistaKosketus(float x, float y, float leveys, float korkeus) {
        if (takaisinNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.skene = Peli.Skene.PÄÄVALIKKO;
        }
    }

    public static void renderöi() {
        Assets.annaTekstuuri("valikko_tausta").bind(0);
        taustaKomponentti.piirrä(shader);
        ohjeOtsikko.piirrä(shader);
        ohje1.piirrä(shader);
        ohje2.piirrä(shader);
        ohje3.piirrä(shader);
        takaisinNappi.piirrä(shader);
    }
}
