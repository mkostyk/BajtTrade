package main.symulacja.utils;

import java.util.Map;
import java.util.TreeMap;

public class BazoweInformacje {
    private int dlugosc;
    private int kara_za_brak_ubran;
    private Map<String, Double> ceny;
    private String gielda;

    public BazoweInformacje(int dlugosc, int kara_za_brak_ubran, Map<String, Double> ceny) {
        this.dlugosc = dlugosc;
        this.kara_za_brak_ubran = kara_za_brak_ubran;

        // TODO - ceny bazowe
    }

    // TODO

    public int podajDługość() {
        return dlugosc;
    }

    public int podajKaręZaBrakUbrań() {
        return kara_za_brak_ubran;
    }

    public Map<String, Double> podajCeny() {
        return ceny;
    }

    public String podajGiełdę() {
        return gielda;
    }
}
