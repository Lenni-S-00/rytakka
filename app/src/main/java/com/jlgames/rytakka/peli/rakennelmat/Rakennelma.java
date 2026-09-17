package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;
import com.jlgames.rytakka.peli.Peli;

public class Rakennelma extends KlikattavaObjekti {

    int hp;
    boolean tuhottu;

    float sijX, sijY;

    Renderöitävä tekstuuri;
    int tiimi; // Mille tiimille rakennelma kuuluu.
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
    }

    public int tiimi() {
        return tiimi;
    }

    public int annaHp() {
        return hp;
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

        if (Peli.valittuObjekti != null && Peli.valittuObjekti.equals(this)) {
            Assets.annaTekstuuri("hud_valitun_ääriviivat").bind(0);
            Assets.annaNeliöModel().draw();
        }
    }
}
