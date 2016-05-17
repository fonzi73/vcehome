/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.eingabe;

import java.util.ArrayList;

/**
 *
 * @author fonzi
 */
public class Test {
    public static void main(String[] args) {
        // Teste LernKarte2ThemenBereich.insert()
        //LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(1, 1);
        //LernKarte2ThemenBereich.insert(lK2TB);
        
        // Teste Lernkarte ohne Arrays
        LernKarte lK = new LernKarte("Wo bin ich?", 1);
        // Teste Lernkarte mit Themenbereich ArrayList
        ArrayList<ThemenBereich> tBs =  new ArrayList<>();
        tBs.add(new ThemenBereich(1, "Java basics"));
        tBs.add(new ThemenBereich(2, "blabla"));
        lK.settBs(tBs);
        // Methode Testen
        LernKarte.insert(lK);
        
    }
}
