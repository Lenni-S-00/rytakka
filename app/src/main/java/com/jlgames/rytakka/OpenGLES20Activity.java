package com.jlgames.rytakka;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.jlgames.rytakka.engine.assets.Assets;

public class OpenGLES20Activity extends Activity {

    private GLSurfaceView gLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gLView = new GLSurfaceViewR(this);
        setContentView(gLView);
        // Assettien lataus assets-kansioista tehdään Activity-luokassa, koska konteksti vaaditaan.
        Assets.createAssets(this);
    }
}