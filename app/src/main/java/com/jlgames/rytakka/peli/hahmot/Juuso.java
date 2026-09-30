package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Juuso extends Pelihahmo {

    public Juuso(int tiimi) {
        super(tiimi);
        super.hp = 50;
        super.maxHP = 50;
        super.damage = 8; // Juuso heittää päärynän
        super.nopeus = 0.005f;
        super.tekstuuri = Assets.annaTekstuuri("Juuso");
        super.hinta = 30;
    }
}
