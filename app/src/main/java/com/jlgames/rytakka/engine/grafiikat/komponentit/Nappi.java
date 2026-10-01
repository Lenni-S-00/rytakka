package com.jlgames.rytakka.engine.grafiikat.komponentit;

import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;

public class Nappi extends HUDKomponentti {
    // Yksinkertaisempi konstruktori, ei käytetä tällä hetkellä.
    public Nappi(Renderöitävä tekstuuri) {
        super(tekstuuri);
    }

    // Varsinainen konstruktori
    public Nappi(float skaalaX, float skaalaY, float offsetX, float offsetY, Renderöitävä tekstuuri) {
        super(skaalaX, skaalaY, offsetX, offsetY, tekstuuri);
    }

    public boolean tarkistaKlikkaus(float kosketusX, float kosketusY, float leveys, float korkeus) {
        float kohdeX = haeRuutuKoordinaatti(kosketusX, leveys);
        float kohdeY = haeRuutuKoordinaatti(korkeus-kosketusY, korkeus);
        if (
            kohdeX > matrixOffsetX - matrixScaleX &&
            kohdeX < matrixOffsetX + matrixScaleX &&
            kohdeY > matrixOffsetY - matrixScaleY &&
            kohdeY < matrixOffsetY + matrixScaleY
        ) {
            return true;
        }
        else return false;
    }

    private float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
