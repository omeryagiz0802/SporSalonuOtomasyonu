package com.sporsalonu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KullaniciDAO {

    public static boolean kullaniciGiris(String kullaniciAdi, String sifre) {
        String sql = "SELECT * FROM uyeler WHERE kullanici_adi = ? AND sifre = ?";

        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, kullaniciAdi);
            ifade.setString(2, sifre);
            ResultSet sonuc = ifade.executeQuery();

            return sonuc.next(); 
        } catch (SQLException e) {
            System.out.println("Giriş hatası: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Bilinmeyen bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

    public static boolean sifreSifirla(String eposta) {
        String sql = "SELECT * FROM uyeler WHERE eposta = ?";

        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, eposta);
            ResultSet sonuc = ifade.executeQuery();

            if (sonuc.next()) {
                String konu = "Şifre Sıfırlama Talebi";
                String mesaj = "Merhaba,\n\nŞifrenizi sıfırlamak için aşağıdaki bağlantıyı kullanabilirsiniz:\n" +
                               "https://www.sporsalonu.com/sifre-sifirla?email=" + eposta;

                EpostaGonderici.epostaGonder(eposta, konu, mesaj);
                return true;
            } else {
                System.out.println("E-posta adresi bulunamadı.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Şifre sıfırlama hatası: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Bilinmeyen bir hata oluştu: " + e.getMessage());
            return false;
        }
    }

	public static boolean kullaniciKaydet(String adSoyad, String sifre, String adSoyad2, String eposta) {
		// TODO Auto-generated method stub
		return false;
	}
}