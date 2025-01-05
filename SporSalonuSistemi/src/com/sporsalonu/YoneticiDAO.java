package com.sporsalonu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class YoneticiDAO {

    public static boolean personelEkle(String adSoyad, String kullaniciAdi, String sifre, String rol) {
        String sql = "INSERT INTO personeller (ad_soyad, kullanici_adi, sifre, rol) VALUES (?, ?, ?, ?)";

        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, adSoyad);
            ifade.setString(2, kullaniciAdi);
            ifade.setString(3, sifre);
            ifade.setString(4, rol);
            ifade.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Personel ekleme hatası: " + e.getMessage());
            return false;
        }
    }

    public static String personelleriListele() {
        String sql = "SELECT * FROM personeller";
        StringBuilder sonuc = new StringBuilder("Personel Listesi:\n");
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql);
             ResultSet rs = ifade.executeQuery()) {

            while (rs.next()) {
                sonuc.append("ID: ").append(rs.getInt("id"))
                      .append(", Ad Soyad: ").append(rs.getString("ad_soyad"))
                      .append(", Kullanıcı Adı: ").append(rs.getString("kullanici_adi"))
                      .append(", Rol: ").append(rs.getString("rol"))
                      .append("\n");
            }
        } catch (SQLException e) {
            sonuc = new StringBuilder("Personel listeleme hatası: " + e.getMessage());
        }
        return sonuc.toString();
    }

    public static boolean personelSil(int id) {
        String sql = "DELETE FROM personeller WHERE id = ?";
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setInt(1, id);
            return ifade.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Personel silme hatası: " + e.getMessage());
            return false;
        }
    }

    public static boolean personelGuncelle(int id, String adSoyad, String kullaniciAdi, String sifre, String rol) {
        String sql = "UPDATE personeller SET ad_soyad = ?, kullanici_adi = ?, sifre = ?, rol = ? WHERE id = ?";
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setString(1, adSoyad);
            ifade.setString(2, kullaniciAdi);
            ifade.setString(3, sifre);
            ifade.setString(4, rol);
            ifade.setInt(5, id);

            return ifade.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Personel güncelleme hatası: " + e.getMessage());
            return false;
        }
    }

    // Belirli Bir Personelin Bilgilerini Getirme (Opsiyonel)
    public static String personelBilgileriGetir(int id) {
        String sql = "SELECT * FROM personeller WHERE id = ?";
        StringBuilder sonuc = new StringBuilder();
        try (Connection baglanti = VeritabaniYardimcisi.baglan();
             PreparedStatement ifade = baglanti.prepareStatement(sql)) {

            ifade.setInt(1, id);
            ResultSet rs = ifade.executeQuery();
            if (rs.next()) {
                sonuc.append("Ad Soyad: ").append(rs.getString("ad_soyad"))
                      .append("\nKullanıcı Adı: ").append(rs.getString("kullanici_adi"))
                      .append("\nRol: ").append(rs.getString("rol"));
            } else {
                sonuc.append("Personel bulunamadı.");
            }
        } catch (SQLException e) {
            sonuc = new StringBuilder("Personel bilgileri getirme hatası: " + e.getMessage());
        }
        return sonuc.toString();
    }

	public static boolean yoneticiGiris(String kullaniciAdi, String sifre) {
		// TODO Auto-generated method stub
		return false;
	}
}