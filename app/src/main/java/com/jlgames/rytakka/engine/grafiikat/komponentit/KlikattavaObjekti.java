package com.jlgames.rytakka.engine.grafiikat.komponentit;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.peli.Peli;

// Luokka klikattavia komponentteja varten
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

    @Override
    public void piirrä(Shader shader) {
        super.piirrä(shader);
        if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(this)) {
            Assets.annaTekstuuri("hud_valitun_ääriviivat").bind(0);
            Assets.annaNeliöModel().draw();
        }
    }
}
