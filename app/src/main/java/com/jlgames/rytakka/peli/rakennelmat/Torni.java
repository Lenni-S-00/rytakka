package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;

// Tätä ei käytetä nykyisessä versiossa.
public class Torni extends Rakennelma{

    public Torni(int tiimi) {
        super(tiimi);
        super.hp = 100;
        super.maxHp = 100;
        super.rahanTuotto = 1;
        super.tekstuuri = Assets.annaTekstuuri("torni");
    }
}
