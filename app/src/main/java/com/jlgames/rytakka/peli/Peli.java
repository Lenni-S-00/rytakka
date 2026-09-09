package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.media.Äänet;
import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.hahmot.Taistelija;

import java.util.ArrayList;

public class Peli {

    // Vaihdetaan ehkä HashMappiin tai keksitään joku järkevä keino referoida yksittäisiin hahmoihin.
    public static ArrayList<Pelihahmo> hahmotKentällä = new ArrayList<>();

    public static void luoPeli() {
        // Jotain tarvittavia alkusäätöjä ennen kuin siirrytään pelisilmukkaan.
        Assets.createTextures();
        Äänet.toistaMusa("keimo_valikko");
        // Lisätään kentälle testihahmo
        hahmotKentällä.add(new Taistelija());
    }

    public static void peliLoop() {
        // Tähän pelisilmukka
        for (Pelihahmo hahmo : hahmotKentällä) {
            hahmo.liikuKohteeseen();
        }
    }
}
