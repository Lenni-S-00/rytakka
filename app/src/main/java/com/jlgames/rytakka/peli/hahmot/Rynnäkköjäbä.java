package com.jlgames.rytakka.peli.hahmot;

import com.jlgames.rytakka.engine.assets.Assets;

public class Rynnäkköjäbä extends Pelihahmo {

    public Rynnäkköjäbä(int tiimi) {
        super(tiimi);
        super.hp = 2;
        super.damage = 2;
        super.nopeus = 0.02f;
        super.tekstuuri = Assets.annaTekstuuri("Rynnäkköjäbä");
    }
}
