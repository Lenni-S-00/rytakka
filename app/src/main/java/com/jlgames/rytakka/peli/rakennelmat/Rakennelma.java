package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;
import com.jlgames.rytakka.engine.grafiikat.komponentit.KlikattavaObjekti;

public class Rakennelma extends KlikattavaObjekti {

    int hp;
    boolean tuhottu;

    float sijX, sijY;

    Renderöitävä tekstuuri;

    public Rakennelma(int tiimi) {
        this.tuhottu = false;
        this.matrixScaleX = 0.2f;
        this.matrixScaleY = 0.2f;
        switch (tiimi) {
            case 0:
                this.sijX = -0.75f;
                this.sijY = -0.75f;
            break;
            case 1:
                this.sijX = 0.75f;
                this.sijY = -0.75f;
            break;
            case 2:
                this.sijX = -0.75f;
                this.sijY = 0.75f;
            break;
            case 3:
                this.sijX = 0.75f;
                this.sijY = 0.75f;
            break;
        }
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
        }
    }

    @Override
    public void piirrä(Shader shader) {
        if (tuhottu) Assets.annaTekstuuri("raunio").bind(0);
        else tekstuuri.bind(0);
        this.matrixOffsetX = sijX;
        this.matrixOffsetY = sijY;
        super.piirrä(shader);
    }
}
