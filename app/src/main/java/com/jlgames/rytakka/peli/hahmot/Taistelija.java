package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Taistelija extends Pelihahmo {

    public Taistelija(int tiimi) {
        super(tiimi);
        super.hp = 1;
        super.damage = 1;
        super.nopeus = 0.02f;
        switch (tiimi) {
            case 0:
                super.tekstuuri = Assets.annaTekstuuri("taistelija_punainen");
            break;
            case 1:
                super.tekstuuri = Assets.annaTekstuuri("taistelija_sininen");
            break;
            case 2:
                super.tekstuuri = Assets.annaTekstuuri("taistelija_vihreä");
            break;
            case 3:
                super.tekstuuri = Assets.annaTekstuuri("taistelija_keltainen");
            break;
        }
    }
}
