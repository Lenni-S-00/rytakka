package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Rynnäkköjäbä extends Pelihahmo {

    public Rynnäkköjäbä(int tiimi) {
        super(tiimi);
        super.hp = 20;
        super.maxHP = 20;
        super.damage = 4;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Rynnäkköjäbä");
        super.hinta = 15;
    }
}
