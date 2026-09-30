package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Luujäbä extends Pelihahmo {

    public Luujäbä(int tiimi) {
        super(tiimi);
        super.hp = 15;
        super.maxHP = 15;
        super.damage = 3;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Luujäbä");
        super.hinta = 10;
    }
}
