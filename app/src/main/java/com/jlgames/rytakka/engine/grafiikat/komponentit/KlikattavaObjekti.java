package com.jlgames.rytakka.engine.grafiikat.komponentit;

public abstract class KlikattavaObjekti extends Komponentti {

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
