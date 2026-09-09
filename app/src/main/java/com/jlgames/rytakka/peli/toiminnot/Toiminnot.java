package com.jlgames.rytakka.peli.toiminnot;

import com.jlgames.rytakka.peli.Peli;

public class Toiminnot {

    public static void kosketusToiminto(float x, float y, float leveys, float korkeus) {
        // Tähän jotain logiikkaa, jolla valitaan, mitä tehdään missäkin pelin vaiheessa.
        float kohdeX = haeRuutuKoordinaatti(x, leveys);
        float kohdeY = haeRuutuKoordinaatti(korkeus-y, korkeus);
        siirräHahmoja(kohdeX, kohdeY);
    }

    public static void siirräHahmoja(float x, float y) {
        // Joku järkevä toteutus sille, miten hahmot valitaan
        Peli.hahmotKentällä.get(0).asetaKohde(x, y);
    }

    private static float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
