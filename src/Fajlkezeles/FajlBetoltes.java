package Fajlkezeles;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.plaf.ColorUIResource;

public class FajlBetoltes {
    private List<String> kepek_nev = new ArrayList<>();
    private List<String> kepek_utvonal = new ArrayList<>();
    private List<BufferedImage> kepek = new ArrayList<>();
    String[] tamogatott_fajltipusok = {"jpg", "jpeg", "png"};

    public void betolt() {
        // Létrehozza és megnyitja a fájlválasztó ablakot

        setRoseTheme();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int valasz = fileChooser.showOpenDialog(null);


        // Ha OK gombra nyomunk

        if (valasz == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for (File file : files) {

                //Fájlok adatainak betöltése a listákba

                kepek_nev.add(file.getName());
                kepek_utvonal.add(file.getAbsolutePath());
                String kiterjesztes = getKiterjesztes(file);


                // Kiterjesztés ellenőrzése

                boolean ervenyesKep = false;
                for (String ext : tamogatott_fajltipusok) {
                    if (ext.equalsIgnoreCase(kiterjesztes)) {
                        ervenyesKep = true;
                        break;
                    }
                }

                // Ha érvényes, akkor betölti a kepek listába

                if (ervenyesKep) {
                    try {
                        kepek.add(ImageIO.read(file));
                        System.out.println("Kép betöltve: " + file.getName());
                    } catch (IOException e) {
                        String errorMsg = "Hiba a kép betöltésekor: " + file.getName() + " - " + e.getMessage();
                        System.out.println(errorMsg);
                        JOptionPane.showMessageDialog(null, errorMsg, "Betöltési hiba", JOptionPane.ERROR_MESSAGE);
                    }
                }

                // Ha nem akkor kiírja, hogy nem támogatott
                else {
                    String errorMsg = "Nem támogatott képformátum: " + kiterjesztes;
                    System.out.println(errorMsg);
                    JOptionPane.showMessageDialog(null, errorMsg, "Nem támogatott formátum", JOptionPane.WARNING_MESSAGE);
                }


            }
        }
    }

    // Kiterjesztés lekérése

    public String getKiterjesztes(File file) {
        String nev = file.getName();
        int pontIndex = nev.lastIndexOf('.');
        if (pontIndex > 0 && pontIndex < nev.length() - 1) {
            return nev.substring(pontIndex + 1);
        }
        return "";
    }

    public List<BufferedImage> getKepek() {
        return kepek;
    }

    public List<String> getKepek_nev() {
        return kepek_nev;
    }

    public List<String> getKepek_utvonal() {
        return kepek_utvonal;
    }

    public static void setRoseTheme() {
        ColorUIResource lightPink = new ColorUIResource(255, 200, 221);    // világos rózsaszín
        ColorUIResource pink = new ColorUIResource(226, 115, 150);         // élénkebb rózsaszín
        ColorUIResource white = new ColorUIResource(Color.WHITE);

        UIManager.put("Panel.background", white);
        UIManager.put("Label.foreground", pink);
        UIManager.put("Button.foreground", pink);
    }
}
