package com.sporsalonu;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GirisEkrani extends JFrame {

    private static final long serialVersionUID = 1L;

    public GirisEkrani() {

        setTitle("Spor Salonu Giriş Ekranı");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        getContentPane().add(panel);

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
        btnGiris.setBounds(50, 130, 100, 25);
        panel.add(btnGiris);

        JButton btnSifreSifirla = new JButton("Şifre Sıfırla");
        btnSifreSifirla.setBounds(200, 130, 100, 25);
        panel.add(btnSifreSifirla);

        JButton btnUyeOl = new JButton("Üye Ol");
        btnUyeOl.setBounds(126, 178, 100, 25);
        panel.add(btnUyeOl);

        JButton btnAdminGiris = new JButton("Admin Girişi");
        btnAdminGiris.setBounds(103, 215, 150, 25);
        panel.add(btnAdminGiris);

        // Şifre Sıfırlama
        btnSifreSifirla.addActionListener(e -> {
            String eposta = JOptionPane.showInputDialog("Lütfen e-posta adresinizi girin:");

            if (eposta != null && !eposta.trim().isEmpty()) {
                boolean basarili = KullaniciDAO.sifreSifirla(eposta);
                if (basarili) {
                    JOptionPane.showMessageDialog(null, "Şifre sıfırlama e-postası gönderildi!");
                } else {
                    JOptionPane.showMessageDialog(null, "E-posta adresi bulunamadı.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "E-posta adresi girmelisiniz.", "Uyarı", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Üye Ol Ekranı
        btnUyeOl.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Üye Ol ekranına yönlendiriliyorsunuz.");

            UyeOlEkrani uyeOlEkrani = new UyeOlEkrani();
            uyeOlEkrani.setVisible(true);
            dispose();
        });

        // Admin Girişi Ekranı
        btnAdminGiris.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Admin Giriş ekranına yönlendiriliyorsunuz.");

            AdminGirisEkrani adminGirisEkrani = new AdminGirisEkrani();
            adminGirisEkrani.setVisible(true);
            dispose();
        });

        // Kullanıcı Girişi
        btnGiris.addActionListener(e -> {
            String kullaniciAdi = txtKullaniciAdi.getText();
            String sifre = new String(txtSifre.getPassword());

            boolean girisBasarili = KullaniciDAO.kullaniciGiris(kullaniciAdi, sifre);
            if (girisBasarili) {
                JOptionPane.showMessageDialog(null, "Giriş başarılı! Üye Ana Sayfa'ya yönlendiriliyorsunuz.");
                UyeAnaSayfa uyeAnaSayfa = new UyeAnaSayfa(kullaniciAdi); // Kullanıcı adını ana sayfaya geçir
                uyeAnaSayfa.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı.", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GirisEkrani girisEkrani = new GirisEkrani();
            girisEkrani.setVisible(true);
        });
    }
}