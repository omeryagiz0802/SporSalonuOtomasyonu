package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UyeAnaSayfa extends JFrame {

    private static final long serialVersionUID = 1L;

    public UyeAnaSayfa(String kullaniciAdi) {
        setTitle("Üye Ana Sayfası");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblHosgeldiniz = new JLabel("Hoş Geldiniz, " + kullaniciAdi + "!");
        lblHosgeldiniz.setBounds(200, 20, 200, 25);
        panel.add(lblHosgeldiniz);

        JButton btnProfilGoruntule = new JButton("Profil Bilgilerim");
        btnProfilGoruntule.setBounds(50, 70, 200, 30);
        panel.add(btnProfilGoruntule);

        JButton btnUyelikDurumu = new JButton("Üyelik Durumu");
        btnUyelikDurumu.setBounds(300, 70, 200, 30);
        panel.add(btnUyelikDurumu);

        JButton btnOdemeler = new JButton("Ödeme Yap");
        btnOdemeler.setBounds(50, 120, 200, 30);
        panel.add(btnOdemeler);

        JButton btnHesapGuncelle = new JButton("Hesap Güncelle");
        btnHesapGuncelle.setBounds(300, 120, 200, 30);
        panel.add(btnHesapGuncelle);

        JButton btnCikisYap = new JButton("Çıkış Yap");
        btnCikisYap.setBounds(200, 200, 200, 30);
        panel.add(btnCikisYap);

        btnProfilGoruntule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String profilBilgileri = UyeDAO.uyeBilgileriGetirByKullaniciAdi(kullaniciAdi);
                JOptionPane.showMessageDialog(null, profilBilgileri, "Profil Bilgileriniz", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnUyelikDurumu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uyelikDurumu = UyeDAO.uyelikDurumuGetir(kullaniciAdi);
                JOptionPane.showMessageDialog(null, uyelikDurumu, "Üyelik Durumunuz", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        btnOdemeler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean odemeYapildi = UyeDAO.odemeYap(kullaniciAdi);
                if (odemeYapildi) {
                    JOptionPane.showMessageDialog(null, "Ödemeniz başarıyla yapıldı!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Ödeme yapılamadı. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnHesapGuncelle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yeniAdSoyad = JOptionPane.showInputDialog("Yeni Ad Soyad:");
                String yeniTelefon = JOptionPane.showInputDialog("Yeni Telefon Numarası:");

                if (yeniAdSoyad != null && yeniTelefon != null) {
                    boolean guncellendi = UyeDAO.hesapGuncelle(kullaniciAdi, yeniAdSoyad, yeniTelefon);
                    if (guncellendi) {
                        JOptionPane.showMessageDialog(null, "Hesabınız başarıyla güncellendi!", "Başarılı", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Hesabınız güncellenemedi. Lütfen tekrar deneyin.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        btnCikisYap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new GirisEkrani().setVisible(true); 
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UyeAnaSayfa uyeAnaSayfa = new UyeAnaSayfa("ornekKullanici");
            uyeAnaSayfa.setVisible(true);
        });
    }
}