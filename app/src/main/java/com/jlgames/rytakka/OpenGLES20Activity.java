package com.jlgames.rytakka;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Looper;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.RevenueCatManager;
import com.jlgames.rytakka.peli.Peli;

// Main Activity
public class OpenGLES20Activity extends Activity {

    private GLSurfaceView gLView;
    private RevenueCatManager revenueCatManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // RevenueCatin alustus
        revenueCatManager = new RevenueCatManager(this, this);

        revenueCatManager.konfiguroi();
        revenueCatManager.lataaTuotteet();
        revenueCatManager.tarkistaSkinit();

        gLView = new GLSurfaceViewR(this);
        setContentView(gLView);
        // Assettien lataus assets-kansioista tehdään Activity-luokassa, koska konteksti vaaditaan.
        Assets.createAssets(this);

        // Säie, joka tarkistaa jatkuvasti oston statuksen sekä ostopainikkeen. Tän vois ehkä toteuttaa paremmin.
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                while (true) {
                    try {
                        Peli.skinitAvattu = revenueCatManager.onkoSkinitAvattu();
                        if (Peli.kauppaKlikattu) {
                            revenueCatManager.ostaSkinit();
                            System.out.println("Ostetaan skini...");
                            Peli.kauppaKlikattu = false;
                            Looper.loop();
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Looper.myLooper().quit();
                        throw new RuntimeException(e);
                    }
                }
            }
        }.start();
    }
}