package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.hahmot.Taistelija;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;
import com.jlgames.rytakka.peli.rakennelmat.Torni;

import java.util.ArrayList;

public class Pelaaja {

    private Rakennelma rakennelma; // pelaajan alkurakennelma, jota voi päivittää.
    public ArrayList<Pelihahmo> hahmotKentällä = new ArrayList<>(); // Vaihdetaan ehkä HashMappiin tai keksitään joku järkevä keino referoida yksittäisiin hahmoihin.
    private int raha;
    public boolean botti;
    private int tiimi;
    public Pelaaja(int tiimi, boolean botti) {
        this.tiimi = tiimi;
        this.botti = botti;
        this.raha = 0;
        this.rakennelma = new Torni(tiimi);
        this.hahmotKentällä.clear();
    }

    public Rakennelma rakennelma() {
        return rakennelma;
    }

    public ArrayList<Pelihahmo> hahmot() {
        return hahmotKentällä;
    }

    public int raha() {
        return raha;
    }

    public void lisääHahmo(Pelihahmo hahmo) {
        this.hahmotKentällä.add(hahmo);
    }

    public void lisääRaha(int määrä) {
        this.raha += määrä;
    }

    public void päivitäRakennelma(Rakennelma rakennelma) {
        this.rakennelma = rakennelma;
    }
}
