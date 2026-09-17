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
     * Tän vois varmaan tehdä paremmin mutta se toimii joten älä korjaa toimivaa!
     */
    public static void createAssets(Context context) {
        mSquare = new NeliöModel();

        bitmapit.put("tausta", getBitmapFromAsset(context, "kuvat/taustat/tausta.png"));
        bitmapit.put("taistelija", getBitmapFromAsset(context, "kuvat/hahmot/taistelija.png"));
        bitmapit.put("taistelija_punainen", getBitmapFromAsset(context, "kuvat/hahmot/taistelija_punainen.png"));
        bitmapit.put("taistelija_sininen", getBitmapFromAsset(context, "kuvat/hahmot/taistelija_sininen.png"));
        bitmapit.put("taistelija_vihreä", getBitmapFromAsset(context, "kuvat/hahmot/taistelija_vihreä.png"));
        bitmapit.put("taistelija_keltainen", getBitmapFromAsset(context, "kuvat/hahmot/taistelija_keltainen.png"));
        bitmapit.put("Isojäbä", getBitmapFromAsset(context, "kuvat/hahmot/Isojäbä.png"));
        bitmapit.put("Juuso", getBitmapFromAsset(context, "kuvat/hahmot/Juuso.png"));
        bitmapit.put("Juuso_lippis", getBitmapFromAsset(context, "kuvat/hahmot/Juuso-ja-lippis.png"));
        bitmapit.put("Lepakkojäbä", getBitmapFromAsset(context, "kuvat/hahmot/Lepakkojäbä.png"));
        bitmapit.put("Luujäbä", getBitmapFromAsset(context, "kuvat/hahmot/Luujäbä.png"));
        bitmapit.put("Mailajäbä", getBitmapFromAsset(context, "kuvat/hahmot/Mailajäbä.png"));
        bitmapit.put("Muskelijäbä", getBitmapFromAsset(context, "kuvat/hahmot/Muskelijäbä.png"));
        bitmapit.put("Piikkipallojäbä", getBitmapFromAsset(context, "kuvat/hahmot/Piikkipallojäbä.png"));
        bitmapit.put("Päällikköjäbä", getBitmapFromAsset(context, "kuvat/hahmot/Päällikköjäbä.png"));
        bitmapit.put("Rynnäkköjäbä", getBitmapFromAsset(context, "kuvat/hahmot/Rynnäkköjäbä.png"));
        bitmapit.put("Tikkujäbä", getBitmapFromAsset(context, "kuvat/hahmot/Tikkujäbä.png"));
        bitmapit.put("raunio", getBitmapFromAsset(context, "kuvat/rakennelmat/raunio.png"));
        bitmapit.put("linnake", getBitmapFromAsset(context, "kuvat/rakennelmat/linnake.png"));
        bitmapit.put("torni", getBitmapFromAsset(context, "kuvat/rakennelmat/torni.png"));
        bitmapit.put("hud_pohja", getBitmapFromAsset(context, "kuvat/hud/hud_pohja.png"));
        bitmapit.put("hud_raha", getBitmapFromAsset(context, "kuvat/hud/raha.png"));
        bitmapit.put("hud_nappi_tyhjä", getBitmapFromAsset(context, "kuvat/hud/nappi_tyhjä.png"));
        bitmapit.put("hud_nappi_takaisin", getBitmapFromAsset(context, "kuvat/hud/nappi_takaisin.png"));
        bitmapit.put("hud_nappi_kouluta", getBitmapFromAsset(context, "kuvat/hud/nappi_valikko_hahmot.png"));
        bitmapit.put("hud_nappi_osta_taistelija", getBitmapFromAsset(context, "kuvat/hud/nappi_osta_taistelija.png"));
        bitmapit.put("hud_nappi_osta_taistelija_harmaa", getBitmapFromAsset(context, "kuvat/hud/nappi_osta_taistelija_harmaa.png"));
        bitmapit.put("hud_nappi_päivitä_rakennelma", getBitmapFromAsset(context, "kuvat/hud/nappi_päivitä_rakennelma.png"));
        bitmapit.put("hud_nappi_hyökkää", getBitmapFromAsset(context, "kuvat/hud/nappi_hyökkää.png"));
        bitmapit.put("hud_valitun_ääriviivat", getBitmapFromAsset(context, "kuvat/hud/valitun_objektin_ääriviivat.png"));
        bitmapit.put("hud_ostopainike_tyhjä", getBitmapFromAsset(context, "kuvat/hud/painikkeet/ostopainike_tyhjä.png"));
        bitmapit.put("hud_hahmopainike", getBitmapFromAsset(context, "kuvat/hud/painikkeet/hahmopainike.png"));
        bitmapit.put("hud_splash_voitto", getBitmapFromAsset(context, "kuvat/hud/splash/splash_voitto.png"));
        bitmapit.put("hud_splash_häviö", getBitmapFromAsset(context, "kuvat/hud/splash/splash_häviö.png"));

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
        tekstuurit.put("taistelija_punainen", new Tekstuuri(bitmapit.get("taistelija_punainen")));
        tekstuurit.put("taistelija_sininen", new Tekstuuri(bitmapit.get("taistelija_sininen")));
        tekstuurit.put("taistelija_vihreä", new Tekstuuri(bitmapit.get("taistelija_vihreä")));
        tekstuurit.put("taistelija_keltainen", new Tekstuuri(bitmapit.get("taistelija_keltainen")));
        tekstuurit.put("Isojäbä", new Tekstuuri(bitmapit.get("Isojäbä")));
        tekstuurit.put("Juuso", new Tekstuuri(bitmapit.get("Juuso")));
        tekstuurit.put("Juuso_lippis", new Tekstuuri(bitmapit.get("Juuso_lippis")));
        tekstuurit.put("Lepakkojäbä", new Tekstuuri(bitmapit.get("Lepakkojäbä")));
        tekstuurit.put("Luujäbä", new Tekstuuri(bitmapit.get("Luujäbä")));
        tekstuurit.put("Mailajäbä", new Tekstuuri(bitmapit.get("Mailajäbä")));
        tekstuurit.put("Muskelijäbä", new Tekstuuri(bitmapit.get("Muskelijäbä")));
        tekstuurit.put("Piikkipallojäbä", new Tekstuuri(bitmapit.get("Piikkipallojäbä")));
        tekstuurit.put("Päällikköjäbä", new Tekstuuri(bitmapit.get("Päällikköjäbä")));
        tekstuurit.put("Rynnäkköjäbä", new Tekstuuri(bitmapit.get("Rynnäkköjäbä")));
        tekstuurit.put("Tikkujäbä", new Tekstuuri(bitmapit.get("Tikkujäbä")));
        tekstuurit.put("raunio", new Tekstuuri(bitmapit.get("raunio")));
        tekstuurit.put("linnake", new Tekstuuri(bitmapit.get("linnake")));
        tekstuurit.put("torni", new Tekstuuri(bitmapit.get("torni")));
        tekstuurit.put("hud_pohja", new Tekstuuri(bitmapit.get("hud_pohja")));
        tekstuurit.put("hud_raha", new Tekstuuri(bitmapit.get("hud_raha")));
        tekstuurit.put("hud_nappi_tyhjä", new Tekstuuri(bitmapit.get("hud_nappi_tyhjä")));
        tekstuurit.put("hud_nappi_takaisin", new Tekstuuri(bitmapit.get("hud_nappi_takaisin")));
        tekstuurit.put("hud_nappi_kouluta", new Tekstuuri(bitmapit.get("hud_nappi_kouluta")));
        tekstuurit.put("hud_nappi_osta_taistelija", new Tekstuuri(bitmapit.get("hud_nappi_osta_taistelija")));
        tekstuurit.put("hud_nappi_osta_taistelija_harmaa", new Tekstuuri(bitmapit.get("hud_nappi_osta_taistelija_harmaa")));
        tekstuurit.put("hud_nappi_päivitä_rakennelma", new Tekstuuri(bitmapit.get("hud_nappi_päivitä_rakennelma")));
        tekstuurit.put("hud_nappi_hyökkää", new Tekstuuri(bitmapit.get("hud_nappi_hyökkää")));
        tekstuurit.put("hud_valitun_ääriviivat", new Tekstuuri(bitmapit.get("hud_valitun_ääriviivat")));
        tekstuurit.put("hud_ostopainike_tyhjä", new Tekstuuri(bitmapit.get("hud_ostopainike_tyhjä")));
        tekstuurit.put("hud_hahmopainike", new Tekstuuri(bitmapit.get("hud_hahmopainike")));
        tekstuurit.put("hud_splash_voitto", new Tekstuuri(bitmapit.get("hud_splash_voitto")));
        tekstuurit.put("hud_splash_häviö", new Tekstuuri(bitmapit.get("hud_splash_häviö")));
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
