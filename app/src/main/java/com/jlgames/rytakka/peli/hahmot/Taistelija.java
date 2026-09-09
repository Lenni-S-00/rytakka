package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Taistelija extends Pelihahmo {

    public Taistelija() {
        super.hp = 1;
        super.damage = 1;
        super.nopeus = 0.02f;
        super.tekstuuri = Assets.annaTekstuuri("taistelija");
    }
}
