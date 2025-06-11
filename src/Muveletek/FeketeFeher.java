package Muveletek;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class FeketeFeher implements Muveletek {

    @Override
    public BufferedImage alkalmaz(BufferedImage eredeti) {
        int szelesseg = eredeti.getWidth();
        int magassag = eredeti.getHeight();
        BufferedImage feketeFeher = new BufferedImage(szelesseg, magassag, BufferedImage.TYPE_BYTE_GRAY);

        for (int x = 0; x < szelesseg; x++) {
            for (int y = 0; y < magassag; y++) {
                Color szin = new Color(eredeti.getRGB(x, y));
                int atlag = (szin.getRed() + szin.getGreen() + szin.getBlue()) / 3;
                Color szurke = new Color(atlag, atlag, atlag);
                feketeFeher.setRGB(x, y, szurke.getRGB());
            }
        }

        return feketeFeher;
    }
}
