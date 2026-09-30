package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Päällikköjäbä extends Pelihahmo {

    public Päällikköjäbä(int tiimi) {
        super(tiimi);
        super.hp = 30;
        super.maxHP = 30;
        super.damage = 3;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Päällikköjäbä");
        super.hinta = 15;
    }
}
