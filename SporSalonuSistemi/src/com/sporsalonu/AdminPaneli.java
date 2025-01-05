package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPaneli extends JFrame {

    private static final long serialVersionUID = 1L;

    public AdminPaneli() {
        setTitle("Admin Paneli");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblWelcome = new JLabel("Hoş geldiniz, Admin Panelindesiniz!", SwingConstants.CENTER);
        lblWelcome.setBounds(50, 20, 400, 25);
        panel.add(lblWelcome);

        JButton btnUyeYonetimi = new JButton("Üye Yönetimi");
        btnUyeYonetimi.setBounds(150, 70, 200, 30);
        panel.add(btnUyeYonetimi);

        JButton btnPersonelYonetimi = new JButton("Personel Yönetimi");
        btnPersonelYonetimi.setBounds(150, 120, 200, 30);
        panel.add(btnPersonelYonetimi);

        JButton btnCikis = new JButton("Çıkış Yap");
        btnCikis.setBounds(150, 170, 200, 30);
        panel.add(btnCikis);

        btnUyeYonetimi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UyeYonetimi uyeYonetimi = new UyeYonetimi();
                uyeYonetimi.setVisible(true); 
                dispose(); 
            }
        });

        btnPersonelYonetimi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonelYonetimi personelYonetimi = new PersonelYonetimi();
                personelYonetimi.setVisible(true); 
                dispose(); 
            }
        });

        btnCikis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Çıkış yapmak istediğinize emin misiniz?", "Çıkış", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); 
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminPaneli adminPaneli = new AdminPaneli();
            adminPaneli.setVisible(true);
        });
    }
}