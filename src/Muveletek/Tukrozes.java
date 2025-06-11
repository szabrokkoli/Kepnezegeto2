package Muveletek;

import java.awt.image.BufferedImage;

public class Tukrozes implements Muveletek {
    public boolean vizszintes = true; // Alapértelmezett vízszintes tükrözés

    @Override
    public BufferedImage alkalmaz(BufferedImage eredeti) {
        int width = eredeti.getWidth();
        int height = eredeti.getHeight();

        BufferedImage ujKep = new BufferedImage(width, height, eredeti.getType());

        if (vizszintes) {
            // Vízszintes tükrözés
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    ujKep.setRGB(width - i - 1, j, eredeti.getRGB(i, j));
                }
            }
        } else {
            // Függőleges tükrözés
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    ujKep.setRGB(i, height - j - 1, eredeti.getRGB(i, j));
                }
            }
        }

        return ujKep;
    }
}