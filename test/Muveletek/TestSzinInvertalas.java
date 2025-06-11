package Muveletek;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class TestSzinInvertalas {

    @Test
    public void szinInvertalas() {
        // Létrehozunk egy 1x1-es képet egy ismert színnel: piros (255, 0, 0)
        BufferedImage bemenet = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        bemenet.setRGB(0, 0, new Color(255, 0, 0).getRGB());

        // Várható eredmény: cyan (0, 255, 255)
        Color vart = new Color(0, 255, 255);

        SzinInvertalas invertalas = new SzinInvertalas();
        BufferedImage eredmeny = invertalas.alkalmaz(bemenet);

        Color kapott = new Color(eredmeny.getRGB(0, 0));

        assertEquals(vart.getRed(), kapott.getRed(), "A piros komponens nem invertálódott helyesen");
        assertEquals(vart.getGreen(), kapott.getGreen(), "A zöld komponens nem invertálódott helyesen");
        assertEquals(vart.getBlue(), kapott.getBlue(), "A kék komponens nem invertálódott helyesen");
    }
}
