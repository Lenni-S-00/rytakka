package com.jlgames.rytakka.peli.rakennelmat;

import com.jlgames.rytakka.engine.assets.Assets;

public class Torni extends Rakennelma{

    public Torni(int tiimi) {
        super(tiimi);
        super.hp = 10;
        super.tekstuuri = Assets.annaTekstuuri("torni");
    }
}
