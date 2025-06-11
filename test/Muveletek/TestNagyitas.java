package Muveletek;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestNagyitas {
    @Test
    void tesztNagyitasMeret() {
        //A nagyított kép mérete 200%-os nagyítás esetén valóban 200%-kal nő.

        BufferedImage eredeti = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Nagyitas n = new Nagyitas(200);
        BufferedImage ujKep = n.alkalmaz(eredeti);

        assertEquals(200, ujKep.getWidth());
        assertEquals(200, ujKep.getHeight());
    }

    @Test
    void tesztNagyitasNemNull() {
        // A nagyítás után visszaadott objektum nem null.

        BufferedImage eredeti = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        Nagyitas n = new Nagyitas(150);
        BufferedImage ujKep = n.alkalmaz(eredeti);

        assertNotNull(ujKep);
    }

}
