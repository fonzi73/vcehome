/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.eingabe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static my.eingabe.LernKarte.con;
import static my.eingabe.LernKarte2ThemenBereich.con;

/**
 *
 * @author fonzi
 */
public class PotentielleAntwort {

    private int id;
    private String antwort;
    private boolean richtigkeit;
    private int lernKarte_id;

    // Klassen zum Abfragen der Datenbank
    static Connection con = null;
    static PreparedStatement pst = null;
    static Statement st = null;
    static ResultSet rst = null;

    public PotentielleAntwort(String antwort, boolean richtigkeit, int lernKarte_id) {
        this.antwort = antwort;
        this.richtigkeit = richtigkeit;
        this.lernKarte_id = lernKarte_id;
    }

    public PotentielleAntwort(String antwort, boolean richtigkeit) {
        this.antwort = antwort;
        this.richtigkeit = richtigkeit;
    }

    public void setLernKarte_id(int lernKarte_id) {
        this.lernKarte_id = lernKarte_id;
    }

    
    public int getId() {
        return id;
    }

    public String getAntwort() {
        return antwort;
    }

    public boolean isRichtigkeit() {
        return richtigkeit;
    }

    public int getLernKarte_id() {
        return lernKarte_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void insert(PotentielleAntwort pA) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            pst = con.prepareStatement("INSERT INTO potentielleantwort VALUES (null, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS); // ID Ausgeben
            pst.setString(1, String.valueOf(pA.isRichtigkeit())); // db erwartet String in ENUM-Feld
            pst.setString(2, pA.getAntwort());
            pst.setInt(3, pA.getLernKarte_id());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                //System.out.println(rst.getInt(1)); // Erste Spalte mit getInt(1) auslesen. PrimaryKey
                pA.setId(rst.getInt(1)); // Id in ArrayList übergeben
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            // ex.printStackTrace(); // Java Meldung
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        
    }
    
    public static void delete(PotentielleAntwort pA) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            // Prepared Statement
            String sql = "DELETE FROM potentielleantwort WHERE lernkarte_id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, pA.getLernKarte_id()) ;
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); // Output Meldung wenn Fehler
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pst != null) {
                    pst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
