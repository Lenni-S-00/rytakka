package com.jlgames.rytakka.peli;

import com.jlgames.rytakka.peli.hahmot.Pelihahmo;
import com.jlgames.rytakka.peli.rakennelmat.Linnake;
import com.jlgames.rytakka.peli.rakennelmat.Rakennelma;

import java.util.ArrayList;

public class Pelaaja {

    private Rakennelma rakennelma; // pelaajan alkurakennelma, jota voi päivittää.
    public ArrayList<Pelihahmo> hahmotKentällä = new ArrayList<>(); // Vaihdetaan ehkä HashMappiin tai keksitään joku järkevä keino referoida yksittäisiin hahmoihin.
    private int raha;
    public boolean botti; // Onko pelaaja botti
    public int tiimi;
    public Pelaaja(int tiimi, boolean botti) {
        this.tiimi = tiimi;
        this.botti = botti;
        this.raha = 0;
        this.rakennelma = new Linnake(tiimi);
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

    // Voitaneen käyttää tulevaisuudessa.
    public void päivitäRakennelma(Rakennelma rakennelma) {
        this.rakennelma = rakennelma;
    }
}
