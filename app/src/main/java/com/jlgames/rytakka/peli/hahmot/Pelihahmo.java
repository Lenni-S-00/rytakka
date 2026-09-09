package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.Komponentti;

public abstract class Pelihahmo extends Komponentti {

    int hp;
    int damage;
    float nopeus;
    float sijX, sijY;
    float kohdeX, kohdeY;
    Renderöitävä tekstuuri;

    public Pelihahmo() {
        this.sijX = 0;
        this.sijY = 0;
        this.kohdeX = 0;
        this.kohdeY = 0;
        this.matrixScaleX = 0.08f;
        this.matrixScaleY = 0.08f;
    }

    public void asetaKohde(float x, float y) {
        this.kohdeX = x;
        this.kohdeY = y;
    }

    /**
     * Kutsu tätä funktiota joka framessa, jossa hahmoa halutaan liikuttaa.
     * Hahmo liikkuu yhden askeleen (nopeuden verran) valittuun kohteeseen.
     */
    public void liikuKohteeseen() {
        float etäisyysX = Math.abs(kohdeX-sijX);
        float etäisyysY = Math.abs(kohdeY-sijY);
        float hypotenuusa = (float)Math.sqrt(Math.pow(etäisyysX, 2) + Math.pow(etäisyysY, 2));
        if (sijX < kohdeX) sijX += (etäisyysX/hypotenuusa) * nopeus;
        else if (sijX > kohdeX) sijX -= (etäisyysX/hypotenuusa) * nopeus;;
        if (sijY < kohdeY) sijY += (etäisyysY/hypotenuusa) * nopeus;
        else if (sijY > kohdeY) sijY -= (etäisyysY/hypotenuusa) * nopeus;
        // Vältä ettei hahmo jää "stutteroimaan" lähelle kohdetta vaan lukitse kohteeseen.
        if (Math.abs(sijX-kohdeX) <= nopeus) sijX = kohdeX;
        if (Math.abs(sijY-kohdeY) <= nopeus) sijY = kohdeY;
    }

    /**
     * Piirtofunktio muuten sama kuin Komponentti-luokassa, mutta hahmolle valitaan sen oma tekstuuri
     * sekä hahmon renderöinnin sijainti päivitetään joka framessa liikesijaintiin.
     * @param shader shader-ohjelma (nykyisellään käytetään vain vakiota)
     */
    @Override
    public void piirrä(Shader shader) {
        tekstuuri.bind(0);
        this.matrixOffsetX = sijX;
        this.matrixOffsetY = sijY;
        super.piirrä(shader);
    }
}
