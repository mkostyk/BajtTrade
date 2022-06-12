package main.symulacja.utils;

import java.util.Map;

public class BazoweInformacje {
    private final int dlugosc;
    private final int kara_za_brak_ubran;
    private Map<String, Double> ceny;
    private String gielda;

    public BazoweInformacje(int dlugosc, int kara_za_brak_ubran, Map<String, Double> ceny, String gielda) {
        this.dlugosc = dlugosc;
        this.kara_za_brak_ubran = kara_za_brak_ubran;
        this.ceny = ceny;
        this.gielda = gielda;
    }

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
