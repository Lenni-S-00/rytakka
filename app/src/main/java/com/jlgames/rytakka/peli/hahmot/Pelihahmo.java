package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;

public abstract class Pelihahmo extends KlikattavaObjekti {

    int hp;
    int maxHP;
    int damage;
    float nopeus;
    float sijX, sijY;
    float kohdeX, kohdeY;
    float alkuSijX, alkuSijY;
    Renderöitävä tekstuuri;
    int tiimi; // Mille tiimille hahmo kuuluu.

    public Pelihahmo(int tiimi) {
        this.tiimi = 0;
        this.matrixScaleX = 0.08f;
        this.matrixScaleY = 0.08f;
        switch (tiimi) {
            case 0:
                this.alkuSijX = -0.7f;
                this.alkuSijY = -0.75f;
            break;
            case 1:
                this.alkuSijX = 0.7f;
                this.alkuSijY = -0.75f;
            break;
            case 2:
                this.alkuSijX = -0.7f;
                this.alkuSijY = 0.75f;
            break;
            case 3:
                this.alkuSijX = 0.7f;
                this.alkuSijY = 0.75f;
            break;
        }
        this.sijX = alkuSijX;
        this.sijY = alkuSijY;
        this.kohdeX = alkuSijX;
        this.kohdeY = alkuSijY;
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
