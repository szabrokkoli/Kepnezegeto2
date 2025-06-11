package Muveletek;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestKicsinyites {

    @Test
    void testKicsinyitesMeret() {
        // A kicsinyített kép mérete 50%-os kicsinyítés esetén valóban 50%-kal csökken.

        BufferedImage eredeti = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Kicsinyites k = new Kicsinyites(50);
        BufferedImage ujKep = k.alkalmaz(eredeti);

        assertEquals(50, ujKep.getWidth());
        assertEquals(50, ujKep.getHeight());
    }

    @Test
    void testKicsinyitesNemNull() {
        // A kicsinyítés után visszaadott objektum nem null.

        BufferedImage eredeti = new BufferedImage(200, 150, BufferedImage.TYPE_INT_RGB);
        Kicsinyites k = new Kicsinyites(25);

        BufferedImage ujKep = k.alkalmaz(eredeti);

        assertNotNull(ujKep);
    }

}
