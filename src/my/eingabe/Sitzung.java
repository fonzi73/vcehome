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
public class Sitzung {

    private ArrayList<LernKarte> lKs = LernKarte.getAll();
    private int aktuellerLKIndex = 0;

    /**
     * Methoden des ArrayList definiert
     *
     * @return
     */
    public ArrayList<LernKarte> getlKs() {
        return lKs;
    }

    public LernKarte getAktuelleLernKarte() {
        return lKs.get(aktuellerLKIndex);
    }

    public void setAktuelleLernKarte(LernKarte lK) {
        lK.updaten(lK);
        lKs.set(aktuellerLKIndex, lK);
    }

    public void addLernKarte(LernKarte lK) {
        lK.insert(lK);
        lKs.add(lK);
    }

    public void removeLernKarte(LernKarte lK) {
        lKs.remove(lK);
        lK.delete(lK);
        lKs = LernKarte.getAll();
    }

    // Schritt nach vorne
    public LernKarte getNextLernKarte() {
        if (aktuellerLKIndex == lKs.size() - 1) {
            aktuellerLKIndex = 0;
        } else {
            aktuellerLKIndex++;
        }
        return getAktuelleLernKarte();
    }

    // Schrit nach hinten
    public LernKarte getPrevLernKarte() {
        if (aktuellerLKIndex == 0) {
            aktuellerLKIndex = lKs.size() - 1;
        } else {
            aktuellerLKIndex--;
        }
        return getAktuelleLernKarte();
    }
}
