package com.sporsalonu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UyeDAO {

    public static String uyeBilgileriGetirByKullaniciAdi(String kullaniciAdi) {
        String sql = "SELECT * FROM uyeler WHERE kullanici_adi = ?";
        StringBuilder sonuc = new StringBuilder();
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, kullaniciAdi);
            ResultSet rs = ifade.executeQuery();

            if (rs.next()) {
                sonuc.append("Ad Soyad: ").append(rs.getString("ad_soyad"))
                      .append("\nTelefon: ").append(rs.getString("telefon"))
                      .append("\nÜyelik Türü: ").append(rs.getString("uyelik_turu"))
                      .append("\nÖdeme Durumu: ").append(rs.getBoolean("odeme_durumu") ? "Ödendi" : "Ödenmedi");
            } else {
                sonuc.append("Kullanıcı bulunamadı.");
            }
        } catch (SQLException e) {
            sonuc = new StringBuilder("Bilgi getirme hatası: " + e.getMessage());
        }
        return sonuc.toString();
    }

    public static String uyelikDurumuGetir(String kullaniciAdi) {
        String sql = "SELECT uyelik_turu, odeme_durumu FROM uyeler WHERE kullanici_adi = ?";
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, kullaniciAdi);
            ResultSet rs = ifade.executeQuery();

            if (rs.next()) {
                String uyelikTuru = rs.getString("uyelik_turu");
                boolean odemeDurumu = rs.getBoolean("odeme_durumu");
                return "Üyelik Türü: " + uyelikTuru + "\nÖdeme Durumu: " + (odemeDurumu ? "Ödendi" : "Ödenmedi");
            }
        } catch (SQLException e) {
            return "Üyelik durumu getirme hatası: " + e.getMessage();
        }
        return "Üyelik durumu bulunamadı.";
    }

    public static boolean odemeYap(String kullaniciAdi) {
        String sql = "UPDATE uyeler SET odeme_durumu = true WHERE kullanici_adi = ?";
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, kullaniciAdi);
            return ifade.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Ödeme yapma hatası: " + e.getMessage());
            return false;
        }
    }

    // Hesap Güncelleme İşlevi
    public static boolean hesapGuncelle(String kullaniciAdi, String yeniAdSoyad, String yeniTelefon) {
        String sql = "UPDATE uyeler SET ad_soyad = ?, telefon = ? WHERE kullanici_adi = ?";
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, yeniAdSoyad);
            ifade.setString(2, yeniTelefon);
            ifade.setString(3, kullaniciAdi);

            return ifade.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Hesap güncelleme hatası: " + e.getMessage());
            return false;
        }
    }

	public static boolean uyeGuncelle(int id, String yeniAdSoyad, String yeniTelefon, String yeniUyelikTuru,
			boolean yeniOdemeDurumu) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean uyeSil(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public static String uyeleriListele() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean uyeEkle(String kullaniciAdi, String eposta, String sifre, String adSoyad, String telefon,
			String uyelikTuru, boolean odemeDurumu) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isUniqueEposta(String eposta) {
		// TODO Auto-generated method stub
		return false;
	}

	public static boolean isUniqueKullaniciAdi(String kullaniciAdi) {
		// TODO Auto-generated method stub
		return false;
	}
}