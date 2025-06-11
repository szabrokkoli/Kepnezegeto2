package Muveletek;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class SzinInvertalas implements Muveletek {

    @Override
    public BufferedImage alkalmaz(BufferedImage eredeti) {
        int szelesseg = eredeti.getWidth();
        int magassag = eredeti.getHeight();
        BufferedImage invertalt = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color szin = new Color(eredeti.getRGB(x, y));
                int r = 255 - szin.getRed();
                int g = 255 - szin.getGreen();
                int b = 255 - szin.getBlue();
                Color ujSzin = new Color(r, g, b);
                invertalt.setRGB(x, y, ujSzin.getRGB());
            }
        }

        return invertalt;
    }
}
