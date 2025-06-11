package Muveletek;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Kicsinyites implements Muveletek {
    private int mertek;

    public Kicsinyites(int mertek){
        this.mertek = mertek;
    }

    @Override
    public BufferedImage alkalmaz(BufferedImage eredeti) {
        // Új méretek kiszámítása

        int ujSzelesseg = eredeti.getWidth() * mertek / 100;
        int ujMagassag = eredeti.getHeight() * mertek / 100;


        // Létrehozza az új képet

        BufferedImage ujKep = new BufferedImage(ujSzelesseg, ujMagassag, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = ujKep.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g2d.drawImage(eredeti, 0, 0, ujSzelesseg, ujMagassag, null);
        g2d.dispose();

        return ujKep;
    }
}