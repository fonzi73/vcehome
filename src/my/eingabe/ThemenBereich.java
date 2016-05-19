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
import java.util.logging.Level;
import java.util.logging.Logger;
import static my.eingabe.PotentielleAntwort.con;
import static my.eingabe.PotentielleAntwort.rst;

/**
 *
 * @author fonzi
 */
public class ThemenBereich {

    private int id;
    private String bezeichnung;

    // Klassen zum Abfragen der Datenbank
    static Connection con = null;
    static PreparedStatement pst = null;
    static Statement st = null;
    static ResultSet rst = null;

    public int getId() {
        return id;
    }

    public ThemenBereich(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public static ThemenBereich getById(int id) {
        ThemenBereich tB = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vcetrainer", "root", "");
            String sql = "SELCT * FROM themenbereich WHERE id=?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeQuery();
            rst = pst.getResultSet();
            while (rst.next()) {
                tB = new ThemenBereich(id, rst.getString("bezeichnung"));
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
        return tB;
    }

}
