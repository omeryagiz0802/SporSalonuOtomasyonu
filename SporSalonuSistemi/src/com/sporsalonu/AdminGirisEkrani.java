package com.sporsalonu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminGirisEkrani extends JFrame {

    private static final long serialVersionUID = 1L;

    public AdminGirisEkrani() {
        setTitle("Admin Giriş Ekranı");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        lblKullaniciAdi.setBounds(50, 50, 100, 25);
        panel.add(lblKullaniciAdi);

        JTextField txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setBounds(150, 50, 150, 25);
        panel.add(txtKullaniciAdi);

        JLabel lblSifre = new JLabel("Şifre:");
        lblSifre.setBounds(50, 90, 100, 25);
        panel.add(lblSifre);

        JPasswordField txtSifre = new JPasswordField();
        txtSifre.setBounds(150, 90, 150, 25);
        panel.add(txtSifre);

        JButton btnGiris = new JButton("Giriş Yap");
        btnGiris.setBounds(50, 150, 100, 25);
        panel.add(btnGiris);

        JButton btnGeriDon = new JButton("Geri Dön");
        btnGeriDon.setBounds(200, 150, 100, 25);
        panel.add(btnGeriDon);

        btnGiris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = txtKullaniciAdi.getText().trim();
                String sifre = new String(txtSifre.getPassword()).trim();

                if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun.", "Uyarı", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                boolean girisBasarili = YoneticiDAO.yoneticiGiris(kullaniciAdi, sifre);

                if (girisBasarili) {
                    JOptionPane.showMessageDialog(null, "Giriş başarılı! Admin paneline yönlendiriliyorsunuz.");
                    AdminPaneli adminPaneli = new AdminPaneli();
                    adminPaneli.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnGeriDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GirisEkrani girisEkrani = new GirisEkrani();
                girisEkrani.setVisible(true);
                dispose(); 
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminGirisEkrani adminGirisEkrani = new AdminGirisEkrani();
            adminGirisEkrani.setVisible(true);
        });
    }
}