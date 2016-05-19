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
import static my.eingabe.PotentielleAntwort.con;

/**
 *
 * @author fonzi
 */
public class LernKarte2ThemenBereich {

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

    public int getId() {
        return id;
    }

    public int getLernKarte_id() {
        return lernKarte_id;
    }

    public int getThemenBereich_id() {
        return themenBereich_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LernKarte2ThemenBereich{" + "id=" + id + ", lernKarte_id=" + lernKarte_id + ", themenBereich_id=" + themenBereich_id + '}';
    }

    public static ArrayList<LernKarte2ThemenBereich> getAllByLernkarte_Id(int lernkarte_id) {
        ArrayList<LernKarte2ThemenBereich> lK2TBs = new ArrayList<>();
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            String sql = "SELECT * FROM lernkarte2themenbereich WHERE lernkarte_id=" + lernkarte_id;
            st = con.createStatement();
            rst = st.executeQuery(sql);
            while (rst.next()) { // rst.next bewirkt ein Stop wen keine weiteren Datensätze vorhanden sind
                LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(rst.getInt("id"), rst.getInt("lernkarte_id"),
                        rst.getInt("themenbereich_id"));
                lK2TBs.add(lK2TB);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rst != null) {
                    rst.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return lK2TBs;
    }

    public static void insert(LernKarte2ThemenBereich lK2TB) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            pst = con.prepareStatement("INSERT INTO lernkarte2themenbereich VALUES (null, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS); // ID Ausgeben
            pst.setInt(1, lK2TB.getLernKarte_id());
            pst.setInt(2, lK2TB.getThemenBereich_id());

            pst.executeUpdate();
            rst = pst.getGeneratedKeys();
            while (rst.next()) {
                //System.out.println(rst.getInt(1)); // Erste Spalte mit getInt(1) auslesen. PrimaryKey
                lK2TB.setId(rst.getInt(1)); // Id in ArrayList übergeben
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

    public static void delete(LernKarte2ThemenBereich lK2TB) {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            // Prepared Statement
            String sql = "DELETE FROM lernkarte2themenbereich WHERE lernkarte_id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, lK2TB.getLernKarte_id());
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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            // Prepared Statement
            String sql = "DELETE FROM lernkarte2themenbereich WHERE lernkarte_id=?";
            pst = con.prepareStatement(sql);
            // Übernimmt werte aus dem GUI
            pst.setInt(1, lK.getId());
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
