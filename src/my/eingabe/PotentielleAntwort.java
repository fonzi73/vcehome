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

    public PotentielleAntwort(int id, String antwort, boolean richtigkeit, int lernKarte_id) {
        this.id = id;
        this.antwort = antwort;
        this.richtigkeit = richtigkeit;
        this.lernKarte_id = lernKarte_id;
    }

    
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

    @Override
    public String toString() {
        return "PotentielleAntwort{" + "id=" + id + ", antwort=" + antwort + ", richtigkeit=" + richtigkeit + ", lernKarte_id=" + lernKarte_id + '}';
    }

   

     public static ArrayList<PotentielleAntwort> getAllByLernkarte(LernKarte lK) {
        ArrayList<PotentielleAntwort> pAs = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer", "Petra", "Panke");
            String sql = "SELECT * FROM potentielleantwort WHERE lernkarte_id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, lK.getId());
            rst = pst.executeQuery();
            while (rst.next()) { // rst.next bewirkt ein Stop wen keine weiteren Datensätze vorhanden sind
                PotentielleAntwort pA = new PotentielleAntwort(
                        rst.getInt("id"), 
                        rst.getString("antwort"), 
                        Boolean.parseBoolean(rst.getString("richtigkeit")), 
                        rst.getInt("lernkarte_id"));
                pAs.add(pA);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        return pAs;
    }
    public static void insert(PotentielleAntwort pA) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer", "Petra", "Panke");
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
    /*
    delete
    */
    public static void delete(PotentielleAntwort pA) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer", "Petra", "Panke");
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
    
    public static void delete(LernKarte lK) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://192.168.2.3:3306/vcetrainer", "Petra", "Panke");
            // Prepared Statement
            String sql = "DELETE FROM potentielleantwort WHERE lernkarte_id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, lK.getId()) ;
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
