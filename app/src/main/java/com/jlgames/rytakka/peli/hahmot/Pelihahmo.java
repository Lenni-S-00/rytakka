package com.jlgames.rytakka.peli.hahmot;

import android.opengl.Matrix;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;

import java.util.Random;

// Kaikki hahmot perivät Pelihahmon.
public abstract class Pelihahmo extends KlikattavaObjekti {

    int hp;
    int maxHP;
    public int damage;
    float nopeus;
    int hinta;
    float sijX, sijY;
    float kohdeX, kohdeY;
    float alkuSijX, alkuSijY;
    Renderöitävä tekstuuri;
    int tiimi; // Mille tiimille hahmo kuuluu.
    int kuolemaAjastin = 0; // Kuolema-animaatiota varten.
    private Random random = new Random();

    public Pelihahmo(int tiimi) {
        this.tiimi = tiimi;
        this.hinta = 5;
        this.matrixScaleX = 0.08f;
        this.matrixScaleY = 0.08f;
        float randomSpawnSijaintiHajontaX = random.nextFloat()/5f;
        float randomSpawnSijaintiHajontaY = random.nextFloat()/5f;
        switch (tiimi) {
            case 0:
                this.alkuSijX = -0.7f + randomSpawnSijaintiHajontaX;
                this.alkuSijY = -0.6f + randomSpawnSijaintiHajontaY;
            break;
            case 1:
                this.alkuSijX = 0.7f + randomSpawnSijaintiHajontaX;
                this.alkuSijY = -0.6f + randomSpawnSijaintiHajontaY;
            break;
            case 2:
                this.alkuSijX = -0.7f + randomSpawnSijaintiHajontaX;
                this.alkuSijY = 0.6f + randomSpawnSijaintiHajontaY;
            break;
            case 3:
                this.alkuSijX = 0.7f + randomSpawnSijaintiHajontaX;
                this.alkuSijY = 0.6f + randomSpawnSijaintiHajontaY;
            break;
        }
        this.sijX = alkuSijX;
        this.sijY = alkuSijY;
        this.kohdeX = alkuSijX;
        this.kohdeY = alkuSijY;
        this.matrixOffsetX = alkuSijX;
        this.matrixOffsetY = alkuSijY;
    }

    public int tiimi() {
        return tiimi;
    }

    public int annaHP() {
        return hp;
    }

    public int annaDmg() {
        return damage;
    }

    public int annaHinta() {
        return hinta;
    }

    public void asetaKohde(float x, float y) {
        this.kohdeX = x;
        this.kohdeY = y;
    }
    public int annaKuolemaAjastin() {
        return kuolemaAjastin;
    }

    public void vahingoita(int damage) {
        this.hp -= damage;
        if (this.hp <= 0) {
            this.hp = 0;
            this.kuolemaAjastin = 100;
        }
    }

    /**
     * Kutsu tätä funktiota joka framessa, jossa hahmoa halutaan liikuttaa.
     * Hahmo liikkuu yhden askeleen (nopeuden verran) valittuun kohteeseen.
     */
    public void liikuKohteeseen() {
        float etäisyysX = Math.abs(kohdeX-sijX);
        float etäisyysY = Math.abs(kohdeY-sijY);
        float hypotenuusa = (float)Math.sqrt(Math.pow(etäisyysX, 2) + Math.pow(etäisyysY, 2));
        // Hahmo on jo kohteessa.
        if (hypotenuusa == 0) {
            return;
        }
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
     * Hahmolle valitaan tiimin mukainen väri shaderilla piirrettäväksi.
     * @param shader shader-ohjelma (nykyisellään käytetään vain vakiota)
     */
    @Override
    public void piirrä(Shader shader) {
        tekstuuri.bind(0);
        this.matrixOffsetX = sijX;
        this.matrixOffsetY = sijY;
        if (this.hp <= 0) this.matrixRotZ = 90;
        if (this.kuolemaAjastin > 0) this.kuolemaAjastin--;
        switch (tiimi) {
            case 0:
                super.piirräVäri(shader, new float[]{0.5f, 0, 0, 0});
            break;
            case 1:
                super.piirräVäri(shader, new float[]{0f, 0, 0.5f, 0});
            break;
            case 2:
                super.piirräVäri(shader, new float[]{0, 0.5f, 0, 0});
            break;
            case 3:
                super.piirräVäri(shader, new float[]{0.5f, 0.5f, 0, 0});
            break;
            default:
                super.piirrä(shader);
            break;
        }
        // Piirrä hp-palkki
        Assets.annaTekstuuri("hud_palkki_punainen").bind(0);
        float[] sijaintiMatriisiPalkkiPunainen = new float[16];
        Matrix.setIdentityM(sijaintiMatriisiPalkkiPunainen, 0);
        Matrix.translateM(sijaintiMatriisiPalkkiPunainen, 0, this.matrixOffsetX, this.matrixOffsetY -this.matrixScaleY, 0);
        Matrix.scaleM(sijaintiMatriisiPalkkiPunainen, 0, this.matrixScaleX, this.matrixScaleY/16f, 1);
        shader.setLocation(sijaintiMatriisiPalkkiPunainen);
        Assets.annaNeliöModel().draw();

        Assets.annaTekstuuri("hud_palkki_vihreä").bind(0);
        float hpSkaala = 0;
        if (hp > 0) hpSkaala = ((float) hp / maxHP);
        float[] sijaintiMatriisiPalkkiVihreä = new float[16];
        Matrix.setIdentityM(sijaintiMatriisiPalkkiVihreä, 0);
        Matrix.translateM(sijaintiMatriisiPalkkiVihreä, 0, this.matrixOffsetX + this.matrixScaleX*hpSkaala - this.matrixScaleX, this.matrixOffsetY -this.matrixScaleY, 0);
        Matrix.scaleM(sijaintiMatriisiPalkkiVihreä, 0, this.matrixScaleX*hpSkaala, this.matrixScaleY/16f, 1);
        shader.setLocation(sijaintiMatriisiPalkkiVihreä);
        Assets.annaNeliöModel().draw();
    }
}
