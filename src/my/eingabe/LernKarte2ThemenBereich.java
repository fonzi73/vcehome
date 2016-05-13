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

/**
 *
 * @author fonzi
 */
public class LernKarte2ThemenBereich implements Saveable {

    private int id;
    private int lernKarte_id;
    private int themenBereich_id;

    // Klassen zum Abfragen der Datenbank
    static Connection con = null;
    static PreparedStatement pst = null;
    static Statement st = null;
    static ResultSet rst = null;

    public LernKarte2ThemenBereich(int lernKarte_id, int themenBereich_id) {
        this.lernKarte_id = lernKarte_id;
        this.themenBereich_id = themenBereich_id;
    }

    public LernKarte2ThemenBereich(int id, int lernKarte_id, int themenBereich_id) {
        this.id = id;
        this.lernKarte_id = lernKarte_id;
        this.themenBereich_id = themenBereich_id;
    }

    @Override // Verbindungsaufbau mit insert
    public void insert() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            pst = con.prepareStatement("INSERT INTO lernkarte2themenbereich VALUES (null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS); // ID Ausgeben
            pst.setInt(1, lernKarte_id);
            pst.setInt(2, themenBereich_id);

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                //System.out.println(rst.getInt(1)); // Erste Spalte mit getInt(1) auslesen. PrimaryKey
                id = rst.getInt(1); // Id in ArrayList übergeben
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

    @Override
    public void update() {
       try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            // Prepared Statement
            String sql = "UPDATE lernkarte2themenbereich SET lernkarte_id=?, themenbereich_id=? WHERE id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, lernKarte_id);
            pst.setInt(2, themenBereich_id);
            pst.setInt(3, id);
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

    @Override
    public void delete(int id) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            // Prepared Statement
            String sql = "DELETE FROM lernkarte2themenbereich WHERE id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, id);
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
