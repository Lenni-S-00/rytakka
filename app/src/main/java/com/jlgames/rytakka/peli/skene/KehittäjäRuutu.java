package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.Teksti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Peli;

public class KehittäjäRuutu {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();
    private static Komponentti kehittäjätOtsikko = new HUDKomponentti(1/4f, 1/8f, 0, 3/10f, new Teksti("Kehittäjät", 160, 36));
    private static String kehittäjätString1 = "Joonatan Taurio";
    private static String kehittäjätString2 = "Lenni Sigfridsson";
    private static Komponentti kehittäjä1 = new HUDKomponentti(1/2f, 1/12f, 0, 0/10f, new Teksti(kehittäjätString1, 400, 36));
    private static Komponentti kehittäjä2 = new HUDKomponentti(1/2f, 1/12f, 0, -2/10f, new Teksti(kehittäjätString2, 400, 36));
    private static Nappi takaisinNappi = new Nappi(1/2f, 1/10f, 0, -6/10f, Assets.annaTekstuuri("valikko_nappi_takaisin"));

    public static void tarkistaKosketus(float x, float y, float leveys, float korkeus) {
        if (takaisinNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.skene = Peli.Skene.PÄÄVALIKKO;
        }
    }

    public static void renderöi() {
        Assets.annaTekstuuri("valikko_tausta").bind(0);
        taustaKomponentti.piirrä(shader);
        kehittäjätOtsikko.piirrä(shader);
        kehittäjä1.piirrä(shader);
        kehittäjä2.piirrä(shader);
        takaisinNappi.piirrä(shader);
    }
}
