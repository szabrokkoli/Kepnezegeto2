package Muveletek;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class TestFeketeFeher {

    @Test
    public void feketeFeherAlkalmazas() {
        // Létrehozunk egy 2x1-es színes képet: egyik pixel piros, másik zöld
        BufferedImage bemenet = new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB);
        bemenet.setRGB(0, 0, new Color(255, 0, 0).getRGB()); // piros
        bemenet.setRGB(1, 0, new Color(0, 255, 0).getRGB()); // zöld

        FeketeFeher ff = new FeketeFeher();
        BufferedImage eredmeny = ff.alkalmaz(bemenet);

        // Ellenőrizzük, hogy a két pixel értékei azonosak R=G=B, vagyis szürkeárnyalatosak
        Color pixel1 = new Color(eredmeny.getRGB(0, 0));
        Color pixel2 = new Color(eredmeny.getRGB(1, 0));

        assertEquals(pixel1.getRed(), pixel1.getGreen(), "Pixel 1 nem szürke");
        assertEquals(pixel1.getGreen(), pixel1.getBlue(), "Pixel 1 nem szürke");

        assertEquals(pixel2.getRed(), pixel2.getGreen(), "Pixel 2 nem szürke");
        assertEquals(pixel2.getGreen(), pixel2.getBlue(), "Pixel 2 nem szürke");
    }
}
