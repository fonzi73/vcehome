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
import java.util.ArrayList;
import static my.eingabe.LernKarte2ThemenBereich.con;

/**
 *
 * @author fonzi
 */
public class LernKarte {
    private int id;
    private String frage;
    private int schwierigkeitsgrad;
    private ArrayList<ThemenBereich> tBs = null;
    private ArrayList<PotentielleAntwort> pAs = null;
    
    // Klassen zum Abfragen der Datenbank
    static Connection con = null;
    static PreparedStatement pst = null;
    static Statement st = null;
    static ResultSet rst = null;

    public LernKarte(int id, String frage, int schwierigkeitsgrad) {
        this.id = id;
        this.frage = frage;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public LernKarte(String frage, int schwierigkeitsgrad) {
        this.frage = frage;
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public int getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public int getSchwierigkeitsgrad() {
        return schwierigkeitsgrad;
    }

    public ArrayList<ThemenBereich> gettBs() {
        return tBs;
    }

    public ArrayList<PotentielleAntwort> getpAs() {
        return pAs;
    }

    public static Connection getCon() {
        return con;
    }

    public static PreparedStatement getPst() {
        return pst;
    }

    public static Statement getSt() {
        return st;
    }

    public static ResultSet getRst() {
        return rst;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setSchwierigkeitsgrad(int schwierigkeitsgrad) {
        this.schwierigkeitsgrad = schwierigkeitsgrad;
    }

    public void settBs(ArrayList<ThemenBereich> tBs) {
        this.tBs = tBs;
    }

    public void setpAs(ArrayList<PotentielleAntwort> pAs) {
        this.pAs = pAs;
    }
    
    
    
    public static void insert(LernKarte lK) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            pst = con.prepareStatement("INSERT INTO lernkarte VALUES (null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS); // ID Ausgeben
            pst.setString(1, lK.getFrage());
            pst.setInt(2, lK.getSchwierigkeitsgrad());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                //System.out.println(rst.getInt(1)); // Erste Spalte mit getInt(1) auslesen. PrimaryKey
              lK.setId(rst.getInt(1)); // Id in ArrayList übergeben
            }
            
            // Zugehörigkeit zu Themenbereichen speichern
        for (ThemenBereich tB  : lK.gettBs()) {
            LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(lK.getId(), tB.getId());
            LernKarte2ThemenBereich.insert(lK2TB);
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
}
