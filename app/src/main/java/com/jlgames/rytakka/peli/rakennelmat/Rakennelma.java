package com.jlgames.rytakka.peli.rakennelmat;

import android.opengl.Matrix;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;
import com.jlgames.rytakka.peli.Peli;

public class Rakennelma extends KlikattavaObjekti {

    int hp;
    int maxHp;
    boolean tuhottu;

    float sijX, sijY;

    Renderöitävä tekstuuri;
    int tiimi; // Mille tiimille rakennelma kuuluu.
    int rahanTuotto = 0; // Kuinka paljon rahaa rakennus tuottaa joka syklissä.
    int hyökkääjä = -1; // Kenen tiimi hyökkäsi rakennukseen viimeksi.
    private int efektiAjastin = 0; // Damage-efektiä varten.

    public Rakennelma(int tiimi) {
        this.tuhottu = false;
        this.tiimi = tiimi;
        this.matrixScaleX = 0.15f;
        this.matrixScaleY = 0.15f;
        switch (tiimi) {
            case 0:
                this.sijX = -0.75f;
                this.sijY = -0.6f;
            break;
            case 1:
                this.sijX = 0.75f;
                this.sijY = -0.6f;
            break;
            case 2:
                this.sijX = -0.75f;
                this.sijY = 0.6f;
            break;
            case 3:
                this.sijX = 0.75f;
                this.sijY = 0.6f;
            break;
        }
        this.matrixOffsetX = sijX;
        this.matrixOffsetY = sijY;
    }

    public int tiimi() {
        return tiimi;
    }

    public int annaHp() {
        return hp;
    }
    public int annaTuotto() {
        return rahanTuotto;
    }
    public int hyökkääjä() {
        return hyökkääjä;
    }
    public void hyökkää(int tiimi) {
        this.hyökkääjä = tiimi;
    }

    public void vahingoita(int dmg) {
        if (!tuhottu) {
            hp -= dmg;
            if (hp <= 0) {
                hp = 0;
                tuhottu = true;
            }
            else {
                efektiAjastin = 16;
            }
        }
    }

    @Override
    public void piirrä(Shader shader) {
        shader.bind();
        if (tuhottu) Assets.annaTekstuuri("raunio").bind(0);
        else tekstuuri.bind(0);
        this.matrixOffsetX = sijX;
        this.matrixOffsetY = sijY;
        float[] shaderVäri;
        if (efektiAjastin > 0) {
            shaderVäri = new float[]{0.75f, 0.75f, 0.75f, 1};
            efektiAjastin--;
        }
        else {
            shaderVäri = new float[]{0, 0, 0, 0};
        }

        super.piirräVäri(shader, shaderVäri);

        // Piirrä hp-palkki
        Assets.annaTekstuuri("hud_palkki_punainen").bind(0);
        float[] sijaintiMatriisiPalkkiPunainen = new float[16];
        Matrix.setIdentityM(sijaintiMatriisiPalkkiPunainen, 0);
        Matrix.translateM(sijaintiMatriisiPalkkiPunainen, 0, this.matrixOffsetX, this.matrixOffsetY -this.matrixScaleY, 0);
        Matrix.scaleM(sijaintiMatriisiPalkkiPunainen, 0, this.matrixScaleX, this.matrixScaleY/8f, 1);
        shader.setLocation(sijaintiMatriisiPalkkiPunainen);
        Assets.annaNeliöModel().draw();

        Assets.annaTekstuuri("hud_palkki_vihreä").bind(0);
        float hpSkaala = 0;
        if (hp > 0) hpSkaala = ((float) hp / maxHp);
        float[] sijaintiMatriisiPalkkiVihreä = new float[16];
        Matrix.setIdentityM(sijaintiMatriisiPalkkiVihreä, 0);
        Matrix.translateM(sijaintiMatriisiPalkkiVihreä, 0, this.matrixOffsetX + this.matrixScaleX*hpSkaala - this.matrixScaleX, this.matrixOffsetY -this.matrixScaleY, 0);
        Matrix.scaleM(sijaintiMatriisiPalkkiVihreä, 0, this.matrixScaleX*hpSkaala, this.matrixScaleY/8f, 1);
        shader.setLocation(sijaintiMatriisiPalkkiVihreä);
        Assets.annaNeliöModel().draw();

        // Piirrä ääriviivat, jos valittu
        if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(this)) {
            Assets.annaTekstuuri("hud_valitun_ääriviivat").bind(0);
            Assets.annaNeliöModel().draw();
        }
    }
}
