package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;

// Jokaisella pelaajalla on linnake.
public class Linnake extends Rakennelma{

    public Linnake(int tiimi) {
        super(tiimi);
        super.hp = 300;
        super.maxHp = 300;
        super.rahanTuotto = 1;
        super.tekstuuri = Assets.annaTekstuuri("linnake");
        int kaivokset = 0;
    }
}
