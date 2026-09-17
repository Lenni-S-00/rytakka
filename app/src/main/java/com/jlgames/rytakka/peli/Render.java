package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.peli.skene.HUD;
import com.jlgames.rytakka.peli.skene.PeliRuutu;

public class Render {

    public static void renderLoop() {
        // Tähän grafiikan renderöintisilmukka
        PeliRuutu.renderöi();
        HUD.renderöiHUD();
    }
}
