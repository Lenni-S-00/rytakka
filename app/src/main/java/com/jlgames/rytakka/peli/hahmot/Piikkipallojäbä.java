package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Piikkipallojäbä extends Pelihahmo {

    public Piikkipallojäbä(int tiimi) {
        super(tiimi);
        super.hp = 20;
        super.maxHP = 20;
        super.damage = 4;
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Piikkipallojäbä");
        super.hinta = 15;
    }
}
