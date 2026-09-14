package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;

public class Linnake extends Rakennelma{

    public Linnake(int tiimi) {
        super(tiimi);
        super.hp = 100;
        super.tekstuuri = Assets.annaTekstuuri("linnake");
    }
}
