package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UyeYonetimi extends JFrame {

    private static final long serialVersionUID = 1L;

    public UyeYonetimi() {
        setTitle("Üye Yönetimi");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblTitle = new JLabel("Üye Yönetimi", SwingConstants.CENTER);
        lblTitle.setBounds(200, 20, 200, 25);
        panel.add(lblTitle);

        JButton btnUyeEkle = new JButton("Üye Ekle");
        btnUyeEkle.setBounds(50, 70, 200, 30);
        panel.add(btnUyeEkle);

        JButton btnUyeListele = new JButton("Üyeleri Listele");
        btnUyeListele.setBounds(300, 70, 200, 30);
        panel.add(btnUyeListele);

        JButton btnUyeSil = new JButton("Üye Sil");
        btnUyeSil.setBounds(50, 120, 200, 30);
        panel.add(btnUyeSil);

        JButton btnUyeGuncelle = new JButton("Üye Güncelle");
        btnUyeGuncelle.setBounds(300, 120, 200, 30);
        panel.add(btnUyeGuncelle);

        // Üye Ekle Butonu
        btnUyeEkle.addActionListener((ActionEvent e) -> {
            String kullaniciAdi = JOptionPane.showInputDialog("Kullanıcı Adı:");
            String adSoyad = JOptionPane.showInputDialog("Üyenin Adı ve Soyadı:");
            String eposta = JOptionPane.showInputDialog("E-posta:");
            String sifre = JOptionPane.showInputDialog("Şifre:");
            String telefon = JOptionPane.showInputDialog("Telefon Numarası:");
            String uyelikTuru = JOptionPane.showInputDialog("Üyelik Türü (Aylık/Yıllık):");
            boolean odemeDurumu = JOptionPane.showConfirmDialog(
                    null, "Ödeme yapıldı mı?", "Ödeme Durumu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

            // Verilerin eksiksiz doldurulduğundan emin olun
            if (kullaniciAdi == null || adSoyad == null || eposta == null || sifre == null || telefon == null || uyelikTuru == null) {
                JOptionPane.showMessageDialog(null, "Tüm bilgileri eksiksiz doldurun.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            boolean basarili = UyeDAO.uyeEkle(kullaniciAdi, eposta, sifre, adSoyad, telefon, uyelikTuru, odemeDurumu);
            if (basarili) {
                JOptionPane.showMessageDialog(null, "Üye başarıyla eklendi!");
            } else {
                JOptionPane.showMessageDialog(null, "Üye eklenemedi. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Üyeleri Listele Butonu
        btnUyeListele.addActionListener(e -> {
            String uyeler = UyeDAO.uyeleriListele(); // Üyeleri metin formatında döndür
            JOptionPane.showMessageDialog(null, uyeler, "Üye Listesi", JOptionPane.INFORMATION_MESSAGE);
        });

        // Üye Sil Butonu
        btnUyeSil.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Silinecek üyenin ID'sini girin:");
            if (idStr == null || idStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                boolean basarili = UyeDAO.uyeSil(id);
                if (basarili) {
                    JOptionPane.showMessageDialog(null, "Üye başarıyla silindi!");
                } else {
                    JOptionPane.showMessageDialog(null, "Üye silinemedi. Lütfen ID'yi kontrol edin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Üye Güncelle Butonu
        btnUyeGuncelle.addActionListener(e -> {
            String idStr = JOptionPane.showInputDialog("Güncellenecek üyenin ID'sini girin:");
            if (idStr == null || idStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(idStr);
                String yeniAdSoyad = JOptionPane.showInputDialog("Yeni Ad Soyad:");
                String yeniTelefon = JOptionPane.showInputDialog("Yeni Telefon:");
                String yeniUyelikTuru = JOptionPane.showInputDialog("Yeni Üyelik Türü (Aylık/Yıllık):");
                boolean yeniOdemeDurumu = JOptionPane.showConfirmDialog(
                        null, "Ödeme yapıldı mı?", "Ödeme Durumu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

                if (yeniAdSoyad == null || yeniTelefon == null || yeniUyelikTuru == null) {
                    JOptionPane.showMessageDialog(null, "Tüm bilgileri eksiksiz doldurun.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean basarili = UyeDAO.uyeGuncelle(id, yeniAdSoyad, yeniTelefon, yeniUyelikTuru, yeniOdemeDurumu);
                if (basarili) {
                    JOptionPane.showMessageDialog(null, "Üye başarıyla güncellendi!");
                } else {
                    JOptionPane.showMessageDialog(null, "Üye güncellenemedi. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Geçerli bir ID girin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UyeYonetimi uyeYonetimi = new UyeYonetimi();
            uyeYonetimi.setVisible(true);
        });
    }
}