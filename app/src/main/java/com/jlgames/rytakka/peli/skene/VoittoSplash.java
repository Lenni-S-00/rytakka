package com.jlgames.rytakka.peli.skene;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.HUDKomponentti;
import com.jlgames.rytakka.peli.Peli;

public class VoittoSplash {

    private static HUDKomponentti voittoRuutu = new HUDKomponentti(0.75f, 0.75f, 0, 0, Assets.annaTekstuuri("hud_splash_voitto"));
    private static HUDKomponentti häviöRuutu = new HUDKomponentti(0.75f, 0.75f, 0, 0, Assets.annaTekstuuri("hud_splash_häviö"));
    public static void renderöiVoittoSplash(Shader shader) {
        switch (Peli.voittaja) {
            case 0:
                voittoRuutu.piirrä(shader);
            break;
            default:
                häviöRuutu.piirrä(shader);
            break;
        }
    }
}
