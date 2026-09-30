package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Lepakkojäbä extends Pelihahmo {

    public Lepakkojäbä(int tiimi) {
        super(tiimi);
        super.hp = 25;
        super.maxHP = 25;
        super.damage = 5;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Lepakkojäbä");
        super.hinta = 18;
    }
}
