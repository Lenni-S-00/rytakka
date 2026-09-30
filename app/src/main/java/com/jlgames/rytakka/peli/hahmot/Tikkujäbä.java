package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Tikkujäbä extends Pelihahmo {

    public Tikkujäbä(int tiimi) {
        super(tiimi);
        super.hp = 10;
        super.maxHP = 10;
        super.damage = 2;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Tikkujäbä");
        super.hinta = 5;
    }
}
