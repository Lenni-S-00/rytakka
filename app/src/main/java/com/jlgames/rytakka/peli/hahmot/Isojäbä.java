package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Isojäbä extends Pelihahmo {

    public Isojäbä(int tiimi) {
        super(tiimi);
        super.hp = 3;
        super.damage = 1;
        super.nopeus = 0.02f;
        super.tekstuuri = Assets.annaTekstuuri("Isojäbä");
        super.hinta = 2;
    }
}
