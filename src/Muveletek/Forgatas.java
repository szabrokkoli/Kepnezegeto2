package Muveletek;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;

public class Forgatas implements Muveletek {
    private int mertek; // A forgatás mértéke

    public Forgatas(int mertek) {
        this.mertek = mertek;
    }

    @Override
    public BufferedImage alkalmaz(BufferedImage kep) {

        // Lekéri az eredeti kép méretét
        int szel = kep.getWidth();
        int mag = kep.getHeight();
        double szog = Math.toRadians(mertek % 360);

        // Az új kép szélessége és magassága
        double ujSzel = Math.abs(szel * Math.cos(szog)) + Math.abs(mag * Math.sin(szog));
        double ujMag = Math.abs(szel * Math.sin(szog)) + Math.abs(mag * Math.cos(szog));

        // Kép létrehozása
        BufferedImage ujKep = new BufferedImage((int) ujSzel, (int) ujMag, kep.getType());
        Graphics2D g2d = ujKep.createGraphics();

        // Anti-aliasing és interpoláció beállítása
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Forgatás középpontja
        AffineTransform at = new AffineTransform();
        at.translate(ujSzel / 2.0, ujMag / 2.0);  // Középre pozicionáljuk
        at.rotate(szog);  // Elforgatjuk
        at.translate(-szel / 2.0, -mag / 2.0);  // Visszatoljuk az eredeti középpontba

        // Kép kirajzolása az új transzformációval
        g2d.drawImage(kep, at, null);
        g2d.dispose();

        // Visszatérünk a forgatott képpel
        return ujKep;
    }
}