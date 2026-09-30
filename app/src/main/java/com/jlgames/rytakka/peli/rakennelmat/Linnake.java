package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;

public class Linnake extends Rakennelma{

    public Linnake(int tiimi) {
        super(tiimi);
        super.hp = 1000;
        super.maxHp = 1000;
        super.rahanTuotto = 3;
        super.tekstuuri = Assets.annaTekstuuri("linnake");
        int kaivokset = 0;
    }
}
