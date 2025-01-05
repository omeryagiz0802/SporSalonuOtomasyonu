package com.sporsalonu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class VeritabaniYardimcisi {

    private static final String URL = "jdbc:mysql://localhost:3306/spor_salon"; 
    private static final String KULLANICI_ADI = "root"; 
    private static final String SIFRE = "345361";

    public static Connection baglan() {
        Connection baglanti = null;
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");

            baglanti = DriverManager.getConnection(URL, KULLANICI_ADI, SIFRE);
            System.out.println("Veritabanına başarıyla bağlanıldı.");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver bulunamadı: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
        return baglanti;
    }
}