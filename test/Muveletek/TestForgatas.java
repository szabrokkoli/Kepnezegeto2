package Muveletek;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestForgatas {

    @Test
    void tesztForgatasMeret() {
        // 90 fokos forgatásnál a szélesség és magasság felcserélődik
        BufferedImage eredeti = new BufferedImage(100, 50, BufferedImage.TYPE_INT_ARGB);
        Forgatas forgatas = new Forgatas(90);
        BufferedImage ujKep = forgatas.alkalmaz(eredeti);

        assertEquals(eredeti.getWidth(), ujKep.getHeight());
        assertEquals(eredeti.getHeight(), ujKep.getWidth());
    }

    @Test
    void tesztForgatasNemNull() {
        // A forgatás után visszaadott objektum nem null.

        BufferedImage eredeti = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Forgatas forgatas = new Forgatas(45);
        BufferedImage ujKep = forgatas.alkalmaz(eredeti);

        assertNotNull(ujKep);
    }
}
