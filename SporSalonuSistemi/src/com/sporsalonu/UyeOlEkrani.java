package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class UyeOlEkrani extends JFrame {

    private static final long serialVersionUID = 1L;

    public UyeOlEkrani() {
        setTitle("Üye Ol");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        // Kullanıcı Adı Alanı
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setBounds(50, 50, 100, 25);
        panel.add(lblKullaniciAdi);

        JTextField txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(150, 50, 150, 25);
        panel.add(txtKullaniciAdi);

        // E-posta Alanı
        JLabel lblEposta = new JLabel("E-posta:");
        lblEposta.setBounds(50, 90, 100, 25);
        panel.add(lblEposta);

        JTextField txtEposta = new JTextField();
        txtEposta.setBounds(150, 90, 150, 25);
        panel.add(txtEposta);

        // Şifre Alanı
        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(50, 130, 100, 25);
        panel.add(lblSifre);

        JPasswordField txtSifre = new JPasswordField();
        txtSifre.setBounds(150, 130, 150, 25);
        panel.add(txtSifre);

        // Ad Soyad Alanı
        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        lblAdSoyad.setBounds(50, 170, 100, 25);
        panel.add(lblAdSoyad);

        JTextField txtAdSoyad = new JTextField();
        txtAdSoyad.setBounds(150, 170, 150, 25);
        panel.add(txtAdSoyad);

        // Telefon Alanı
        JLabel lblTelefon = new JLabel("Telefon:");
        lblTelefon.setBounds(50, 210, 100, 25);
        panel.add(lblTelefon);

        JTextField txtTelefon = new JTextField();
        txtTelefon.setBounds(150, 210, 150, 25);
        panel.add(txtTelefon);

        // Üyelik Türü Alanı
        JLabel lblUyelikTuru = new JLabel("Üyelik Türü:");
        lblUyelikTuru.setBounds(50, 250, 100, 25);
        panel.add(lblUyelikTuru);

        JComboBox<String> cmbUyelikTuru = new JComboBox<>(new String[]{"Aylık", "Yıllık"});
        cmbUyelikTuru.setBounds(150, 250, 150, 25);
        panel.add(cmbUyelikTuru);

        // Kaydet Butonu
        JButton btnKaydet = new JButton("Kaydet");
        btnKaydet.setBounds(150, 300, 100, 25);
        panel.add(btnKaydet);

        // Kaydet Butonu İşlemleri
        btnKaydet.addActionListener((ActionEvent e) -> {
            // Form Alanlarından Veri Alınması
            String kullaniciAdi = txtKullaniciAdi.getText().trim();
            String eposta = txtEposta.getText().trim();
            String sifre = new String(txtSifre.getPassword()).trim();
            String adSoyad = txtAdSoyad.getText().trim();
            String telefon = txtTelefon.getText().trim();
            String uyelikTuru = (String) cmbUyelikTuru.getSelectedItem();
            boolean odemeDurumu = false; // Varsayılan false

            // Boş Alan Kontrolü
            if (kullaniciAdi.isEmpty() || eposta.isEmpty() || sifre.isEmpty() || adSoyad.isEmpty() || telefon.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // E-posta Format Kontrolü
            if (!eposta.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                JOptionPane.showMessageDialog(null, "Geçerli bir e-posta adresi girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kullanıcı Adı ve E-posta Benzersizliği Kontrolü
            if (!UyeDAO.isUniqueKullaniciAdi(kullaniciAdi) || !UyeDAO.isUniqueEposta(eposta)) {
                JOptionPane.showMessageDialog(null, "Kullanıcı adı veya e-posta zaten mevcut.", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Veritabanına Kayıt
            boolean basarili = UyeDAO.uyeEkle(kullaniciAdi, eposta, sifre, adSoyad, telefon, uyelikTuru, odemeDurumu);
            if (basarili) {
                JOptionPane.showMessageDialog(null, "Üyelik başarıyla oluşturuldu! Ödeme durumu: Bekleniyor.");
                dispose(); // Ekranı kapat
            } else {
                JOptionPane.showMessageDialog(null, "Üyelik oluşturulamadı. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UyeOlEkrani uyeOlEkrani = new UyeOlEkrani();
            uyeOlEkrani.setVisible(true);
        });
    }
}