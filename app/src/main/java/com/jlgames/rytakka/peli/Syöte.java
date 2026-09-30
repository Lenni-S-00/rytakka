package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.skene.HUD;
import com.jlgames.rytakka.peli.skene.KehittäjäRuutu;
import com.jlgames.rytakka.peli.skene.OhjeRuutu;
import com.jlgames.rytakka.peli.skene.PeliRuutu;
import com.jlgames.rytakka.peli.skene.Päävalikko;

public class Syöte {

    public static void kosketusToiminto(float x, float y, float leveys, float korkeus) {
        // Tähän jotain logiikkaa, jolla valitaan, mitä tehdään missäkin pelin vaiheessa.
        switch (Peli.skene) {
            case PÄÄVALIKKO:
                Päävalikko.tarkistaKosketus(x, y, leveys, korkeus);
            break;
            case PELI:
                HUD.tarkistaKosketus(x, y, leveys, korkeus);
                PeliRuutu.tarkistaKosketus(x, y, leveys, korkeus);
            break;
            case OHJERUUTU:
                OhjeRuutu.tarkistaKosketus(x, y, leveys, korkeus);
            break;
            case KEHITTÄJÄRUUTU:
                KehittäjäRuutu.tarkistaKosketus(x, y, leveys, korkeus);
            break;
        }
    }

    public static float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
