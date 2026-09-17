package com.jlgames.rytakka.engine.grafiikat.komponentit;

import com.jlgames.rytakka.engine.assets.Assets;
import com.jlgames.rytakka.engine.grafiikat.Renderöitävä;
import com.jlgames.rytakka.engine.grafiikat.Shader;

public class HUDKomponentti extends Komponentti {

    private Renderöitävä tekstuuri;
    public HUDKomponentti(Renderöitävä tekstuuri) {
        this.tekstuuri = tekstuuri;
    }

    public HUDKomponentti(float skaalaX, float skaalaY, float offsetX, float offsetY, Renderöitävä tekstuuri) {
        super.matrixScaleX = skaalaX;
        super.matrixScaleY = skaalaY;
        super.matrixOffsetX = offsetX;
        super.matrixOffsetY = offsetY;
        this.tekstuuri = tekstuuri;
    }

    public void päivitäTekstuuri(Renderöitävä tekstuuri) {
        if (!this.tekstuuri.equals(tekstuuri)) {
            this.tekstuuri = tekstuuri;
        }
    }

    @Override
    public void piirrä(Shader shader) {
        if (tekstuuri != null) tekstuuri.bind(0);
        else Assets.annaTekstuuri("virhe").bind(0);
        super.piirrä(shader);
    }
}
