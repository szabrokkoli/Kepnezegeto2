package Fajlkezeles;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FajlMentes {

    public void mentes(BufferedImage kep) {

        //Létrehozzuk a fájlválasztót
        FajlBetoltes.setRoseTheme();
        JFileChooser fajlValaszto = new JFileChooser();
        fajlValaszto.setDialogTitle("Kép mentése");

        // Fájltípus szűrők hozzáadása
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG képek (*.png)", "png");
        FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter("JPG képek (*.jpg)", "jpg");
        FileNameExtensionFilter jpegFilter = new FileNameExtensionFilter("JPEG képek (*.jpeg)", "jpeg");
        fajlValaszto.addChoosableFileFilter(pngFilter);
        fajlValaszto.addChoosableFileFilter(jpgFilter);
        fajlValaszto.addChoosableFileFilter(jpegFilter);
        fajlValaszto.setFileFilter(pngFilter); // default legyen a PNG


        // Fájlválasztó megnyitása
        int valasztott = fajlValaszto.showSaveDialog(null);

        if (valasztott == JFileChooser.APPROVE_OPTION) {
            File kivalasztottFajl = fajlValaszto.getSelectedFile();
            String fajlNev = kivalasztottFajl.getAbsolutePath();

            String formatum = "png"; // default
            if (fajlValaszto.getFileFilter() == jpgFilter) {
                formatum = "jpg";
            }
            if (fajlValaszto.getFileFilter() == jpegFilter) {
                formatum = "jpeg";
            }

            // Kiterjesztés hozzáadása, ha nincs
            if (!fajlNev.toLowerCase().endsWith("." + formatum)) {
                fajlNev += "." + formatum;
                kivalasztottFajl = new File(fajlNev);
            }

            try {
                BufferedImage mentendoKep = kep;

                // Ha JPG-t vagy JPEG-et akarunk menteni, konvertálni kell
                if (formatum.equalsIgnoreCase("jpg") || formatum.equalsIgnoreCase("jpeg")) {
                    BufferedImage ujKep = new BufferedImage(kep.getWidth(), kep.getHeight(), BufferedImage.TYPE_INT_RGB);
                    ujKep.getGraphics().drawImage(kep, 0, 0, java.awt.Color.WHITE, null);
                    mentendoKep = ujKep;
                }

                boolean sikeres = ImageIO.write(mentendoKep, formatum, kivalasztottFajl);
                if (sikeres) {
                    JOptionPane.showMessageDialog(null, "Kép sikeresen elmentve: " + fajlNev + " :)");
                } else {
                    JOptionPane.showMessageDialog(null, "Nem sikerült menteni ebben a formátumban: " + formatum);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Hiba a mentés során: " + e.getMessage());
            }
        }
}
}
