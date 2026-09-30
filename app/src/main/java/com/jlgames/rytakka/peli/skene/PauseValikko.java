package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Peli;

public class PauseValikko {

    private static HUDKomponentti pauseTausta = new HUDKomponentti(0.75f, 0.75f, 0, 0, Assets.annaTekstuuri("hud_pause_tausta"));
    private static Nappi jatkaNappi = new Nappi(1/2f, 1/10f, 0, 0/10f, Assets.annaTekstuuri("valikko_nappi_jatka"));
    private static Nappi takaisinValikkoonNappi = new Nappi(1/2f, 1/10f, 0, -4/10f, Assets.annaTekstuuri("valikko_nappi_päävalikkoon"));

    public static void tarkistaNapit(float x, float y, float leveys, float korkeus) {
        if (jatkaNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.pause = false;
        }
        if (takaisinValikkoonNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.peliAloitettu = false;
            Peli.peliOhi = false;
            Peli.pause = false;
            Peli.nollaaPeli();
            Peli.skene = Peli.Skene.PÄÄVALIKKO;
        }
    }

    public static void renderöiPauseValikko(Shader shader) {
        pauseTausta.piirrä(shader);
        jatkaNappi.piirrä(shader);
        takaisinValikkoonNappi.piirrä(shader);
    }
}
