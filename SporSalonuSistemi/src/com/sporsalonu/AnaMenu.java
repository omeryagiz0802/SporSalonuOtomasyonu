package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnaMenu extends JFrame {

    private static final long serialVersionUID = 1L;

    public AnaMenu() {
        setTitle("Ana Menü");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblMesaj = new JLabel("Hoş geldiniz, Ana Menüdesiniz!", SwingConstants.CENTER);
        lblMesaj.setBounds(50, 20, 300, 25);
        panel.add(lblMesaj);

        JButton btnUyeIslemleri = new JButton("Üye İşlemleri");
        btnUyeIslemleri.setBounds(125, 70, 150, 25);
        panel.add(btnUyeIslemleri);

        JButton btnYoneticiIslemleri = new JButton("Yönetici İşlemleri");
        btnYoneticiIslemleri.setBounds(125, 110, 150, 25);
        panel.add(btnYoneticiIslemleri);

        JButton btnCikis = new JButton("Çıkış Yap");
        btnCikis.setBounds(125, 150, 150, 25);
        panel.add(btnCikis);

        btnUyeIslemleri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Üye yönetimi ekranına yönlendiriliyorsunuz.");
                UyeYonetimi uyeYonetimi = new UyeYonetimi();
                uyeYonetimi.setVisible(true);
                dispose();
            }
        });

        btnYoneticiIslemleri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Yönetici işlemleri ekranına yönlendiriliyorsunuz.");
                AdminPaneli adminPaneli = new AdminPaneli();
                adminPaneli.setVisible(true);
                dispose();
            }
        });

        btnCikis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Çıkmak istediğinize emin misiniz?", "Çıkış", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnaMenu anaMenu = new AnaMenu();
            anaMenu.setVisible(true);
        });
    }
}
