package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Muskelijäbä extends Pelihahmo {

    public Muskelijäbä(int tiimi) {
        super(tiimi);
        super.hp = 2;
        super.damage = 2;
        super.nopeus = 0.02f;
        super.tekstuuri = Assets.annaTekstuuri("Muskelijäbä");
    }
}
