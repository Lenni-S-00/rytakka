package com.jlgames.rytakka.peli;

import android.util.Log;

import com.jlgames.rytakka.peli.hahmot.Luujäbä;
import com.jlgames.rytakka.peli.hahmot.Mailajäbä;
import com.jlgames.rytakka.peli.hahmot.Tikkujäbä;
import com.jlgames.rytakka.peli.toiminnot.Toiminnot;

import java.util.ArrayList;
import java.util.Random;

public class VihollisAI {
    // Tässä luokassa on logiikka vihollisille.
    private final Pelaaja botti;
    private final ArrayList<Pelaaja> pelaajat;
    private boolean hyökkäysKäynnissä = false;
    private Pelaaja hyökkäyksenKohde;
    private Random random = new Random();

    public VihollisAI(Pelaaja botti, ArrayList<Pelaaja> pelaajat) {

        this.botti = botti;
        this.pelaajat = pelaajat;
    }

    // Julkinen metodi päätöksen kutsumiseen, vähän turha
    public void päivitä() {
        teePäätös();
    }

    // Yksinkertainen päätöslogiikka bottien toiminnoille
    private void teePäätös() {
        if (botti.rakennelma().annaHp() <= 0) {
            return;
        }

        boolean puolustaa = botti.rakennelma().hyökkääjä() > -1;

        if (botti.hahmotKentällä.size() < 5) {
            lisääSatunnainenHahmo();

            // Jos botin kimppuun hyökätään, uusi hahmo puolustaa.
            if (puolustaa) {
                // Puolustus hoidetaan peliLoopissa.
            }
            // Jos hyökkäys on käynnissä, uusi hahmo liittyy hyökkäykseen.
            else if (hyökkäysKäynnissä && hyökkäyksenKohde != null) {
                Toiminnot.bottiHyökkäys(botti, hyökkäyksenKohde.rakennelma());
            }

            return;
        }
        // Tässä vaiheessa on 5 hahmoa.
        // Jos puolustetaan, ei aloiteta uutta hyökkäystä.
        if (puolustaa) {
            return;
        }

        // Jos hyökkäys on käynnissä, jatketaan sitä kunnes kohde on tuhottu.
        if (hyökkäysKäynnissä) {
            if (hyökkäyksenKohde != null && hyökkäyksenKohde.rakennelma().annaHp() > 0) {
                return;
            }
            hyökkäysKäynnissä = false;
            hyökkäyksenKohde = null;
        }

        // Jos 5 hahmoa ja puolustus ei ole käynnissä, aloitetaan uusi hyökkäys.
        Pelaaja kohde = valitseKohde();

        if (kohde != null) {
            hyökkäysKäynnissä = true;
            hyökkäyksenKohde = kohde;

            Toiminnot.bottiHyökkäys(
                    botti,
                    kohde.rakennelma()
            );
        }
    }

    // Valitaan tässä kohtaa kolmesta hahmosta satunnaisesti yksi.
    private void lisääSatunnainenHahmo() {
        int a = random.nextInt(3);

        switch (a) {
            case 0:
                botti.lisääHahmo(new Tikkujäbä(botti.tiimi));
                break;
            case 1:
                botti.lisääHahmo(new Mailajäbä(botti.tiimi));
                break;
            case 2:
                botti.lisääHahmo(new Luujäbä(botti.tiimi));
                break;
        }
    }
    // Valitaan hyökkäyksen kohde satunnaisesti.
    private Pelaaja valitseKohde() {

        ArrayList<Pelaaja> kohteet = new ArrayList<>();
        for (Pelaaja p : pelaajat) {
            if (p != botti && p.rakennelma().annaHp() > 0) {
                kohteet.add(p);
            }
        }
        if (kohteet.isEmpty()) {
            return null;
        }
        int kohde = random.nextInt(kohteet.size());
        return kohteet.get(kohde);
    }
}
