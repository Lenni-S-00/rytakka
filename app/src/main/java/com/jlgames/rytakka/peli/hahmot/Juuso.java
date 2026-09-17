package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Juuso extends Pelihahmo {

    public Juuso(int tiimi) {
        super(tiimi);
        super.hp = 3;
        super.damage = 999; // Juuso heittää päärynän
        super.nopeus = 0.02f;
        super.tekstuuri = Assets.annaTekstuuri("Juuso");
        super.hinta = 20;
    }
}
