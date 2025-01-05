package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PersonelYonetimi extends JFrame {

    private static final long serialVersionUID = 1L;

    public PersonelYonetimi() {
        setTitle("Personel Yönetimi");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblTitle = new JLabel("Personel Yönetimi", SwingConstants.CENTER);
        lblTitle.setBounds(200, 20, 200, 25);
        panel.add(lblTitle);

        JButton btnPersonelEkle = new JButton("Personel Ekle");
        btnPersonelEkle.setBounds(50, 70, 200, 30);
        panel.add(btnPersonelEkle);

        JButton btnPersonelListele = new JButton("Personelleri Listele");
        btnPersonelListele.setBounds(300, 70, 200, 30);
        panel.add(btnPersonelListele);

        JButton btnPersonelSil = new JButton("Personel Sil");
        btnPersonelSil.setBounds(50, 120, 200, 30);
        panel.add(btnPersonelSil);

        JButton btnPersonelGuncelle = new JButton("Personel Güncelle");
        btnPersonelGuncelle.setBounds(300, 120, 200, 30);
        panel.add(btnPersonelGuncelle);

        btnPersonelEkle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adSoyad = JOptionPane.showInputDialog("Personelin Adı ve Soyadı:");
                String kullaniciAdi = JOptionPane.showInputDialog("Kullanıcı Adı:");
                String sifre = JOptionPane.showInputDialog("Şifre:");
                String rol = JOptionPane.showInputDialog("Rol (admin/personel):");

                if (adSoyad != null && kullaniciAdi != null && sifre != null && rol != null) {
                    boolean basarili = YoneticiDAO.personelEkle(adSoyad, kullaniciAdi, sifre, rol);
                    if (basarili) {
                        JOptionPane.showMessageDialog(null, "Personel başarıyla eklendi!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Personel eklenemedi. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnPersonelListele.addActionListener(e -> {
            String personeller = YoneticiDAO.personelleriListele();
            JOptionPane.showMessageDialog(null, personeller, "Personel Listesi", JOptionPane.INFORMATION_MESSAGE);
        });

        btnPersonelSil.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Silinecek personelin ID'sini girin:");
            try {
                int id = Integer.parseInt(idStr);
                boolean basarili = YoneticiDAO.personelSil(id);
                if (basarili) {
                    JOptionPane.showMessageDialog(null, "Personel başarıyla silindi!");
                } else {
                    JOptionPane.showMessageDialog(null, "Personel silinemedi. Lütfen ID'yi kontrol edin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        btnPersonelGuncelle.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Güncellenecek personelin ID'sini girin:");
            try {
                int id = Integer.parseInt(idStr);
                String yeniAdSoyad = JOptionPane.showInputDialog("Yeni Ad Soyad:");
                String yeniKullaniciAdi = JOptionPane.showInputDialog("Yeni Kullanıcı Adı:");
                String yeniSifre = JOptionPane.showInputDialog("Yeni Şifre:");
                String yeniRol = JOptionPane.showInputDialog("Yeni Rol (admin/personel):");

                if (yeniAdSoyad != null && yeniKullaniciAdi != null && yeniSifre != null && yeniRol != null) {
                    boolean basarili = YoneticiDAO.personelGuncelle(id, yeniAdSoyad, yeniKullaniciAdi, yeniSifre, yeniRol);
                    if (basarili) {
                        JOptionPane.showMessageDialog(null, "Personel başarıyla güncellendi!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Personel güncellenemedi. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PersonelYonetimi personelYonetimi = new PersonelYonetimi();
            personelYonetimi.setVisible(true);
        });
    }
}