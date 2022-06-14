package main.symulacja.agenci.spekulanci.strategieSpekulantów;

import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Średni extends Spekulant {
    private final int ileDni;

    public Średni(int id, Map<String, Double> zasoby, int ileDni) {
        super(id, zasoby);
        this.ileDni = ileDni;
    }

    public int podajParametr() {
        return ileDni;
    }

    @Override
    protected void wystawOfertęProduktu(Produkt produkt) {
        double średniaCena = podajGiełdę().podajŚredniąCenęProduktu(ileDni, produkt);
        if (this.ileProduktów(produkt) == 0) {
            this.dodajOfertęKupna(produkt, średniaCena * 0.95);
        } else {
            this.dodajOfertęKupna(produkt, średniaCena * 0.9);
            this.dodajOfertęSprzedaży(produkt, średniaCena * 1.1);
        }
    }

    @Override
    public String toString() {
        return "sredni";
    }
}
