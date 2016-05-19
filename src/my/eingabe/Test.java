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
//        LernKarte2ThemenBereich lK2TB = new LernKarte2ThemenBereich(7, 0);
//        //LernKarte2ThemenBereich.insert(lK2TB);
//        LernKarte2ThemenBereich.delete(lK2TB);

//        PotentielleAntwort pa = new PotentielleAntwort("", true, 8);
//        PotentielleAntwort.delete(pa);
//        // Teste PotentielleAntwort.insert()
//        PotentielleAntwort pA = new PotentielleAntwort("Raum 5.2", true, 1);
//        PotentielleAntwort.insert(pA);
//        
//        // Teste Lernkarte ohne Arrays
//        LernKarte lK = new LernKarte("Wie spät?", 1);
//        // Teste Lernkarte mit Themenbereich ArrayList
//        ArrayList<ThemenBereich> tBs =  new ArrayList<>();
//        tBs.add(new ThemenBereich(1, "Java advanced"));
//        tBs.add(new ThemenBereich(2, "kennste nich?"));
//        lK.settBs(tBs);
//      
//        
//        // zusätzlich Testen Lernkarte mit POtentiellenAntwort ArraysList
        //       ArrayList<PotentielleAntwort> pAs =  new ArrayList<>();
//        pAs.add(new PotentielleAntwort("Raum 5.2", true));
//        pAs.add(new PotentielleAntwort("Gebäude" , false));
//        lK.setpAs(pAs);
//        // Methode Testen
//        LernKarte.insert(lK);
        // Delete from LernKarte2ThemenBereich
//        LernKarte lk = new LernKarte(1, "test", 0);
////        LernKarte.delete(lk);
////        System.out.println(LernKarte2ThemenBereich.getAllByLernkarte_Id(1).toString());
//
//
//           LernKarte lernkarte = new LernKarte(1, "was jjjjj geht?", 10);
//           ArrayList<ThemenBereich> themen = new ArrayList<>();
//           themen.add(new ThemenBereich(1, "Java basics"));
//           themen.add(new ThemenBereich(4, "Flow Control"));
//           lernkarte.settBs(themen);
//           ArrayList<PotentielleAntwort> pAs = new ArrayList<>();
//           pAs.add(new PotentielleAntwort("7777", true, 1));
//           pAs.add(new PotentielleAntwort("4444", false, 1));
//           lernkarte.setpAs(pAs);
//           lernkarte.updaten(lernkarte);
//        // ArrayList befehle Testen
//        Sitzung s = new Sitzung();
//        System.out.println(s.getAktuelleLernKarte());
//        System.out.println(s.getNextLernKarte());
//        System.out.println(s.getPrevLernKarte());
//        System.out.println(s.getPrevLernKarte());
        Sitzung s = new Sitzung();
        ArrayList<LernKarte> lernkarten = new ArrayList<>();
        LernKarte lernkarte = new LernKarte(5, "", 1);

//        ArrayList<ThemenBereich> themen = new ArrayList<>();
//        themen.add(new ThemenBereich(1, "Java basics"));
//        themen.add(new ThemenBereich(2, "Flow Control"));
//        lernkarte.settBs(themen);
//        
//        ArrayList<PotentielleAntwort> pAs = new ArrayList<>();
//        pAs.add(new PotentielleAntwort("666", true, 4));
//        pAs.add(new PotentielleAntwort("333", false, 4));
//        lernkarte.setpAs(pAs);
        
        //s.addLernKarte(lernkarte);
        s.removeLernKarte(lernkarte);
        System.out.println(s.getAktuelleLernKarte());
        System.out.println(s.getPrevLernKarte());
        

    }
}
