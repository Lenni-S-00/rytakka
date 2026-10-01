package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.peli.skene.HUD;
import com.jlgames.rytakka.peli.skene.KehittäjäRuutu;
import com.jlgames.rytakka.peli.skene.OhjeRuutu;
import com.jlgames.rytakka.peli.skene.PeliRuutu;
import com.jlgames.rytakka.peli.skene.Päävalikko;

public class Render {

    public static void renderLoop() {
        // Tässä grafiikan renderöintisilmukka
        switch (Peli.skene) {
            case PÄÄVALIKKO:
                Päävalikko.renderöi();
            break;
            case PELI:
                PeliRuutu.renderöi();
                HUD.renderöiHUD();
            break;
            case OHJERUUTU:
                OhjeRuutu.renderöi();
            break;
            case KEHITTÄJÄRUUTU:
                KehittäjäRuutu.renderöi();
            break;
        }
    }
}
