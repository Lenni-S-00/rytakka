package com.jlgames.rytakka.engine.media;

import android.media.MediaPlayer;

import com.jlgames.rytakka.engine.assets.Assets;

import java.util.Random;

public class Äänet {
    private static MediaPlayer musaToistin;
    private static MediaPlayer ääniToistin;
    private static Random random = new Random();

    /**
     * Toista edellinen syöttämällä tyhjä merkkijono.
     */
    public static void toistaMusa(String musanNimi) {
        if (!musanNimi.isEmpty()) musaToistin = Assets.annaMusa(musanNimi);
        if (musaToistin != null) {
            musaToistin.start();
        }
    }

    /**
     * Toista edellinen syöttämällä tyhjä merkkijono.
     */
    public static void toistaSFX(String äänenNimi) {
        if (!äänenNimi.isEmpty()) ääniToistin = Assets.annaÄäni(äänenNimi);
        if (ääniToistin != null) {
            if (ääniToistin.isPlaying()) ääniToistin.stop();
            ääniToistin.start();
        }
    }

    public static void toistaRandomTölkkiÄäni() {
        int äänenNumero = random.nextInt(8);
        toistaSFX("tölkki" + äänenNumero);
    }
}
