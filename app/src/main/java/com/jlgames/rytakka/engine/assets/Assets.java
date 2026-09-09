package com.jlgames.rytakka.engine.assets;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;

import com.jlgames.rytakka.engine.grafiikat.NeliöModel;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Tekstuuri;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class Assets {
    private static NeliöModel mSquare;
    public static int textureCount = 0;
    public static final float[] vPMatrix = new float[16];
    public static final float[] projectionMatrix = new float[16];
    public static final float[] viewMatrix = new float[16];

    private static final HashMap<String, Bitmap> bitmapit = new HashMap<>();
    private static final HashMap<String, Renderöitävä> tekstuurit = new HashMap<>();
    private static final HashMap<String, MediaPlayer> musat = new HashMap<>();
    private static final HashMap<String, MediaPlayer> äänet = new HashMap<>();

    /**
     * Tän vois varmaan tehdä paremmin mutta se toimii joten älä koske!
     */
    public static void createAssets(Context context) {
        mSquare = new NeliöModel();

        bitmapit.put("tausta", getBitmapFromAsset(context, "kuvat/taustat/tausta.png"));
        bitmapit.put("taistelija", getBitmapFromAsset(context, "kuvat/hahmot/taistelija.png"));

        musat.put("keimo_valikko", getMediaFromAsset(context, "äänet/musat/keimo_valikko.mp3", true, 0.5f));

        äänet.put("ammus", getMediaFromAsset(context, "äänet/sfx/ammus.wav", false, 0.7f));
    }

    /**
     * Varmaan ei tarvis olla kahta listaa kuva-asseteille mutta menkööt näin toistaiseksi
     */
    public static void createTextures() {
        tekstuurit.put("virhe", new Tekstuuri());
        tekstuurit.put("tausta", new Tekstuuri(bitmapit.get("tausta")));
        tekstuurit.put("taistelija", new Tekstuuri(bitmapit.get("taistelija")));
    }

    public static Renderöitävä annaTekstuuri(String nimi) {
        if (tekstuurit.containsKey(nimi)) {
            return tekstuurit.get(nimi);
        }
        else {
            System.out.println("Tekstuuria ei löytynyt: " + nimi);
            return tekstuurit.get("virhe");
        }
    }

    public static Bitmap annaBitmap(String nimi) {
        return bitmapit.get(nimi);
    }
    public static MediaPlayer annaMusa(String nimi) {
        return musat.get(nimi);
    }
    public static MediaPlayer annaÄäni(String nimi) {
        return äänet.get(nimi);
    }

    private static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    private static MediaPlayer getMediaFromAsset(Context context, String filePath, boolean loop, float volume) {
        try (AssetFileDescriptor descriptor = context.getAssets().openFd(filePath)) {
            MediaPlayer player = new MediaPlayer();

            long start = descriptor.getStartOffset();
            long end = descriptor.getLength();

            player.setDataSource(descriptor.getFileDescriptor(), start, end);
            player.setLooping(loop);
            player.prepare();

            player.setVolume(volume, volume);
            return player;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static NeliöModel annaNeliöModel() {
        return mSquare;
    }
}
