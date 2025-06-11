package Muveletek;

import Fajlkezeles.FajlBetoltes;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tulajdonsagok {
    private FajlBetoltes betolto;
    private int index;

    public Tulajdonsagok(FajlBetoltes betolto, int index) {
        this.betolto = betolto;
        this.index = index;
    }

    public void alkalmaz() {
        // Útvonal

        Path utvonal = Paths.get(betolto.getKepek_utvonal().get(index));
        File file = utvonal.toFile();


        // Méret

        BufferedImage kep = betolto.getKepek().get(index);
        int szelesseg = kep.getWidth();
        int magassag = kep.getHeight();


        // Név

        String nev = file.getName();
        long meretKB = file.length() / 1024;
        String utvonalStr = file.getAbsolutePath();


        // Utolsó módosítás dátuma

        long lastModified = file.lastModified();
        String datum = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(lastModified));


        //Felugró ablak

        JFrame ablak = new JFrame("Kép tulajdonságai");

        Path path = Paths.get("images", "app_icon.png");
        ImageIcon icon = new ImageIcon(path.toString());
        ablak.setIconImage(icon.getImage());

        ablak.setSize(500, 300);
        ablak.setLocationRelativeTo(null);
        ablak.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ablak.getContentPane().setBackground(new Color(255, 200, 221));
        ablak.setLayout(new BorderLayout());

        // Keret

        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBackground(new Color(255, 200, 221));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(10, 10, 10, 10), // Ez a külső margó az ablak széléhez
                        BorderFactory.createTitledBorder(
                                BorderFactory.createLineBorder(new Color(226, 115, 150)),
                                "Tulajdonságok",
                                TitledBorder.CENTER,
                                TitledBorder.TOP,
                                new Font("Serif", Font.BOLD, 20)
                        )
                )
        ));

        panel.add(formatLabel("Név:", nev));
        panel.add(formatLabel("Felbontás:", szelesseg + " x " + magassag));
        panel.add(formatLabel("Méret:", meretKB + " kB"));
        panel.add(formatLabel("Útvonal:", utvonalStr));
        panel.add(formatLabel("Módosítás dátuma:", datum));

        ablak.add(panel, BorderLayout.CENTER);
        ablak.setVisible(true);
    }


    // Szöveg formázása

    private JPanel formatLabel(String cim, String ertek) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p.setBackground(new Color(255, 200, 221));

        JLabel cimLabel = new JLabel(cim + " ");
        cimLabel.setFont(new Font("Serif", Font.BOLD, 14));
        cimLabel.setForeground(new Color(226, 115, 150));

        JLabel ertekLabel = new JLabel(ertek);
        ertekLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        ertekLabel.setForeground(Color.BLACK);

        p.add(cimLabel);
        p.add(ertekLabel);

        return p;
    }
}