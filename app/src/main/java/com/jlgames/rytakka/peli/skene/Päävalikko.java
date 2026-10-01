package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Peli;

// Pelin aloitusnäkymänä toimii päävalikko.
public class Päävalikko {

    private static Shader shader = new Shader();
    private static Komponentti taustaKomponentti = new Komponentti();
    private static Nappi aloitaNappi = new Nappi(1/2f, 1/10f, 0, 4/10f, Assets.annaTekstuuri("valikko_nappi_aloita"));
    private static Nappi ohjeetNappi = new Nappi(1/2f, 1/10f, 0, 0, Assets.annaTekstuuri("valikko_nappi_ohjeet"));
    private static Nappi kehittäjätNappi = new Nappi(1/2f, 1/10f, 0, -4/10f, Assets.annaTekstuuri("valikko_nappi_kehittäjät"));

    public static void tarkistaKosketus(float x, float y, float leveys, float korkeus) {
        if (aloitaNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.peliAloitettu = true;
            Peli.skene = Peli.Skene.PELI;
        }
        else if (ohjeetNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.skene = Peli.Skene.OHJERUUTU;
        }
        else if (kehittäjätNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.skene = Peli.Skene.KEHITTÄJÄRUUTU;
        }
    }

    public static void renderöi() {
        Assets.annaTekstuuri("valikko_tausta").bind(0);
        taustaKomponentti.piirrä(shader);
        aloitaNappi.piirrä(shader);
        ohjeetNappi.piirrä(shader);
        kehittäjätNappi.piirrä(shader);
    }
}
