package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Nappi;
import com.jlgames.rytakka.peli.Peli;

public class VoittoSplash {

    private static HUDKomponentti voittoRuutu = new HUDKomponentti(0.75f, 0.75f, 0, 0, Assets.annaTekstuuri("hud_splash_voitto"));
    private static HUDKomponentti häviöRuutu = new HUDKomponentti(0.75f, 0.75f, 0, 0, Assets.annaTekstuuri("hud_splash_häviö"));
    private static Nappi takaisinValikkoonNappi = new Nappi(1/2f, 1/10f, 0, -4/10f, Assets.annaTekstuuri("valikko_nappi_päävalikkoon"));

    public static void tarkistaNapit(float x, float y, float leveys, float korkeus) {
        if (takaisinValikkoonNappi.tarkistaKlikkaus(x, y, leveys, korkeus)) {
            Peli.peliAloitettu = false;
            Peli.peliOhi = false;
            Peli.nollaaPeli();
            Peli.skene = Peli.Skene.PÄÄVALIKKO;
        }
    }

    public static void renderöiVoittoSplash(Shader shader) {
        switch (Peli.voittaja) {
            case 0:
                voittoRuutu.piirrä(shader);
            break;
            default:
                häviöRuutu.piirrä(shader);
            break;
        }
        takaisinValikkoonNappi.piirrä(shader);
    }
}
