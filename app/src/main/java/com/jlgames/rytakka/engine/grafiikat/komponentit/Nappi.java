package com.jlgames.rytakka.engine.grafiikat.komponentit;

import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;

public class Nappi extends HUDKomponentti {
    public Nappi(Renderöitävä tekstuuri) {
        super(tekstuuri);
    }

    public Nappi(float skaalaX, float skaalaY, float offsetX, float offsetY, Renderöitävä tekstuuri) {
        super(skaalaX, skaalaY, offsetX, offsetY, tekstuuri);
    }

    public boolean tarkistaKlikkaus(float kosketusX, float kosketusY, float leveys, float korkeus) {
        float kohdeX = haeRuutuKoordinaatti(kosketusX, leveys);
        float kohdeY = haeRuutuKoordinaatti(korkeus-kosketusY, korkeus);
        if (
            kohdeX > matrixOffsetX - matrixScaleX/2f &&
            kohdeX < matrixOffsetX + matrixScaleX/2f &&
            kohdeY > matrixOffsetY - matrixScaleY/2f &&
            kohdeY < matrixOffsetY + matrixScaleY/2f
        ) {
            return true;
        }
        else return false;
    }

    private float haeRuutuKoordinaatti(float kosketusKoordinaatti, float ruudunKoko) {
        return -1f + (kosketusKoordinaatti/ruudunKoko)*2f;
    }
}
