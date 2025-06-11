package GUI;

import Fajlkezeles.FajlBetoltes;
import Fajlkezeles.FajlMentes;
import Muveletek.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FoAblak extends JFrame {
    // Gombok és elemek deklarálása
    private JLabel kepLabel;
    private JButton betoltesGomb;
    private JButton mentesGomb;
    private JButton eloreGomb;
    private JButton hatraGomb;
    private JButton kicsinyitesGomb, nagyitasGomb, forgatasGomb, tukrozesGomb, tulajdonsagokGomb, feketeFeherGomb, szinInvertalasGomb;

    private FajlBetoltes betolto;
    private int aktualisIndex = 0; // Aktuális kép indexe
    private BufferedImage eredetiKep = null;
    private boolean feketeFeherAktiv = false;


    public FoAblak() {
        // Alap tulajdonságok beállítása

        setTitle("Képnézegető 2");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        setLayout(new BorderLayout());


        // Program ikonja
        Path path = Paths.get("images", "app_icon.png");
        ImageIcon icon = new ImageIcon(path.toString());
        setIconImage(icon.getImage());


        // Kezdő képernyő

        kepLabel = new JLabel("Nincs kép betöltve :(", SwingConstants.CENTER);
        kepLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(kepLabel, BorderLayout.CENTER);
        kepLabel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                frissitKepet(true);
            }
        });


        // Alsó navigációs panel: gombPanel

        JPanel gombPanel = new JPanel();
        betoltesGomb = new JButton("Betöltés");
        // Előre gomb
        eloreGomb = new JButton(">");
        mentesGomb = new JButton("Mentés");
        tulajdonsagokGomb = new JButton("Tulajdonságok");


        hatraGomb = new JButton("<");
        gombPanel.add(hatraGomb);
        gombPanel.add(betoltesGomb);
        gombPanel.add(mentesGomb);
        gombPanel.add(tulajdonsagokGomb);
        gombPanel.add(eloreGomb);
        add(gombPanel, BorderLayout.SOUTH);


        // Felső műveleti gombsor: muveletPanel

        JPanel muveletPanel = new JPanel();
        kicsinyitesGomb = new JButton("Kicsinyítés");
        nagyitasGomb = new JButton("Nagyítás");
        forgatasGomb = new JButton("Forgatás");
        tukrozesGomb = new JButton("Tükrözés");
        feketeFeherGomb = new JButton("Fekete-Fehér");
        szinInvertalasGomb = new JButton("Invertálás");

        JPopupMenu tukrozesMenu = new JPopupMenu();
        JMenuItem vizszintesItem = new JMenuItem("Vízszintes");
        JMenuItem fuggolegesItem = new JMenuItem("Függőleges");

        muveletPanel.add(kicsinyitesGomb);
        muveletPanel.add(nagyitasGomb);
        muveletPanel.add(forgatasGomb);
        muveletPanel.add(tukrozesGomb);
        muveletPanel.add(feketeFeherGomb);
        muveletPanel.add(szinInvertalasGomb);
        add(muveletPanel, BorderLayout.NORTH);


        // Képbetöltő osztály meghívása

        betolto = new FajlBetoltes();


        // Képbetöltő gomb

        betoltesGomb.addActionListener(e -> {
            betolto.betolt();
            aktualisIndex = 0;
            frissitKepet(true);
        });

        mentesGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage aktualisKep = betolto.getKepek().get(aktualisIndex);
                FajlMentes mento = new FajlMentes();
                mento.mentes(aktualisKep);
            }
        });

        // Előre gomb

        eloreGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                aktualisIndex = (aktualisIndex + 1) % betolto.getKepek().size();
                frissitKepet(true);
            }
        });


        // Hátra gomb

        hatraGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                aktualisIndex = (aktualisIndex - 1 + betolto.getKepek().size()) % betolto.getKepek().size();
                frissitKepet(true);
            }
        });


        // Művelet gombok

        // Kicsinyítés gomb

        kicsinyitesGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage eredeti = betolto.getKepek().get(aktualisIndex);
                Kicsinyites k = new Kicsinyites(80);
                BufferedImage modositott = k.alkalmaz(eredeti);
                betolto.getKepek().set(aktualisIndex, modositott);
                frissitKepet(false);
            }
        });


        // Nagyítás gomb

        nagyitasGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage eredeti = betolto.getKepek().get(aktualisIndex);
                Nagyitas n = new Nagyitas(120);
                BufferedImage modositott = n.alkalmaz(eredeti);
                betolto.getKepek().set(aktualisIndex, modositott);
                frissitKepet(false);
            }
        });


        // Forgatás gomb

        forgatasGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage eredeti = betolto.getKepek().get(aktualisIndex);
                Forgatas f = new Forgatas(90); // 90 fokos forgatás például
                BufferedImage modositott = f.alkalmaz(eredeti);
                betolto.getKepek().set(aktualisIndex, modositott); // A frissített kép betöltése
                frissitKepet(true); // Frissítjük a GUI-t
            }
        });

        //fekete fehér gomb
        feketeFeherGomb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (betolto != null && !betolto.getKepek().isEmpty()) {
                    if (!feketeFeherAktiv) {
                        // Elmentjük az eredeti képet
                        eredetiKep = deepCopy(betolto.getKepek().get(aktualisIndex));
                        // Alkalmazzuk a fekete-fehér szűrőt
                        FeketeFeher ff = new FeketeFeher();
                        BufferedImage modositott = ff.alkalmaz(eredetiKep);
                        betolto.getKepek().set(aktualisIndex, modositott);
                        kepLabel.setIcon(new ImageIcon(modositott));
                        feketeFeherAktiv = true;
                    } else {
                        // Visszaállítjuk az eredeti képet
                        betolto.getKepek().set(aktualisIndex, deepCopy(eredetiKep));
                        kepLabel.setIcon(new ImageIcon(eredetiKep));
                        feketeFeherAktiv = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nincs betöltött kép.");
                }
            }
        });


        //inverálás
        szinInvertalasGomb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (betolto != null && !betolto.getKepek().isEmpty()) {
                    BufferedImage aktualisKep = betolto.getKepek().get(aktualisIndex);
                    SzinInvertalas invertalas = new SzinInvertalas();
                    BufferedImage modositott = invertalas.alkalmaz(aktualisKep);
                    betolto.getKepek().set(aktualisIndex, modositott);
                    kepLabel.setIcon(new ImageIcon(modositott));
                } else {
                    JOptionPane.showMessageDialog(null, "Nincs betöltött kép.");
                }
            }
        });


        // Tükrözés gomb

        tukrozesMenu.setBackground(new Color(255, 200, 221));
        tukrozesMenu.setBorder(BorderFactory.createLineBorder(new Color(226, 115, 150), 2));


        // Legördülő menü

        tukrozesGomb.addActionListener(e -> {
            tukrozesMenu.show(tukrozesGomb, 0, tukrozesGomb.getHeight());
        });


        // Legördülő menü pontjai

        vizszintesItem.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage eredeti = betolto.getKepek().get(aktualisIndex);
                Tukrozes t = new Tukrozes();
                BufferedImage modositott = t.alkalmaz(eredeti);
                betolto.getKepek().set(aktualisIndex, modositott);
                frissitKepet(true);
            }
        });

        fuggolegesItem.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                BufferedImage eredeti = betolto.getKepek().get(aktualisIndex);
                Tukrozes t = new Tukrozes();
                t.vizszintes = false;
                BufferedImage modositott = t.alkalmaz(eredeti);
                betolto.getKepek().set(aktualisIndex, modositott);
                frissitKepet(true);
            }
        });

        // Menüelemek hozzáadása
        tukrozesMenu.add(vizszintesItem);
        tukrozesMenu.add(fuggolegesItem);


        tukrozesGomb.addActionListener(e -> {
            tukrozesMenu.show(tukrozesGomb, 0, tukrozesGomb.getHeight());
        });


        // Tulajdonságok gomb

        tulajdonsagokGomb.addActionListener(e -> {
            if (!betolto.getKepek().isEmpty()) {
                Tulajdonsagok tulajdonsagok = new Tulajdonsagok(betolto, aktualisIndex);
                tulajdonsagok.alkalmaz();
            }
        });


        // Háttérszín beállítása

        getContentPane().setBackground(new Color(255, 200, 221));
        gombPanel.setBackground(new Color(255, 200, 221));
        muveletPanel.setBackground(new Color(255, 200, 221));

        // Gombok stílusának beállítása

        gombokStilusa(tulajdonsagokGomb);
        gombokStilusa(tukrozesGomb);
        gombokStilusa(forgatasGomb);
        gombokStilusa(nagyitasGomb);
        gombokStilusa(kicsinyitesGomb);
        gombokStilusa(eloreGomb);
        gombokStilusa(hatraGomb);
        gombokStilusa(betoltesGomb);
        gombokStilusa(mentesGomb);
        gombokStilusa(feketeFeherGomb);
        gombokStilusa(szinInvertalasGomb);


        setVisible(true);
    }


    // Kép frissítése

    private void frissitKepet(boolean skalazas) {
        if (betolto.getKepek().isEmpty()) {
            kepLabel.setText("Nincs kép betöltve :(");
            kepLabel.setIcon(null);
            return;
        }

        BufferedImage kep = betolto.getKepek().get(aktualisIndex);
        Image megjelenitendo = kep;

        if (skalazas) {
            int labelSzel = kepLabel.getWidth();
            int labelMag = kepLabel.getHeight();

            double scale = Math.min((double) labelSzel / kep.getWidth(), (double) labelMag / kep.getHeight());
            int ujSzel = (int) (kep.getWidth() * scale);
            int ujMag = (int) (kep.getHeight() * scale);

            megjelenitendo = kep.getScaledInstance(ujSzel, ujMag, Image.SCALE_SMOOTH);
        }

        kepLabel.setText(null);
        kepLabel.setIcon(new ImageIcon(megjelenitendo));
    }

    public void gombokStilusa(JButton gomb){
        gomb.setBackground(new Color(226, 115, 150));
        gomb.setForeground(Color.WHITE);
        gomb.setFocusPainted(false);
        gomb.setBorderPainted(false);
        gomb.setFont(new Font("Arial", Font.BOLD, 18));

        // Hover szín beállítása
        gomb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                gomb.setBackground(new Color(255, 135, 185)); // Világosabb szín, amikor rávisszük az egeret
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gomb.setBackground(new Color(226, 115, 150)); // Eredeti szín, ha elhagyjuk
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gomb.setBackground(new Color(200, 85, 120)); // Sötétebb rózsaszín kattintáskor
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                gomb.setBackground(new Color(226, 115, 150)); // Visszaáll a normál színre, ha felengeded
            }
        });
    }
    private BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }


}