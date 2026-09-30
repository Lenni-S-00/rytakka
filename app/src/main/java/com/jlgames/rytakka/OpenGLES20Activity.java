package com.jlgames.rytakka;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.RevenueCatManager;

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



    }
}