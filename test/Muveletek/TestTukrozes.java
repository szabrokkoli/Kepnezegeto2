package Muveletek;

import org.junit.jupiter.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class TestTukrozes {
    @Test
    public void testVizszintesTukrozes() {
        // Egy 2x1 méretű képet sikeresen tükröz vízszintesen.

        BufferedImage eredeti = new BufferedImage(2, 1, BufferedImage.TYPE_INT_RGB);
        eredeti.setRGB(0, 0, Color.RED.getRGB());   // bal oldali pixel
        eredeti.setRGB(1, 0, Color.BLUE.getRGB());  // jobb oldali pixel
        Tukrozes tukrozes = new Tukrozes();
        BufferedImage tukrozott = tukrozes.alkalmaz(eredeti);

        assertEquals(Color.BLUE.getRGB(), tukrozott.getRGB(0, 0));
        assertEquals(Color.RED.getRGB(), tukrozott.getRGB(1, 0));
    }

    @Test
    public void testFuggolegesTukrozes() {
        // Egy 1x2 méretű képet sikeresen tükröz függőlegesen.

        BufferedImage eredeti = new BufferedImage(1, 2, BufferedImage.TYPE_INT_RGB);
        eredeti.setRGB(0, 0, Color.GREEN.getRGB());   // felső pixel
        eredeti.setRGB(0, 1, Color.YELLOW.getRGB());  // alsó pixel
        Tukrozes tukrozes = new Tukrozes();
        tukrozes.vizszintes = false;
        BufferedImage tukrozott = tukrozes.alkalmaz(eredeti);

        assertEquals(Color.YELLOW.getRGB(), tukrozott.getRGB(0, 0));
        assertEquals(Color.GREEN.getRGB(), tukrozott.getRGB(0, 1));
    }
}

