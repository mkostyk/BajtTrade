package main.symulacja.agenci.spekulanci;

import com.squareup.moshi.Json;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Średni extends Spekulant {
    @Json(name = "historia_spekulanta_sredniego")
    private final int ileDni;

    public Średni(int id, Map<String, Double> zasoby, int ileDni) {
        super(id, zasoby);
        this.ileDni = ileDni;
    }

    @Override
    protected void wystawOfertęProduktu(Produkt produkt) {
        double średniaCena = podajGiełdę().podajŚredniąCenęProduktu(ileDni, produkt);
        if (this.ileProduktów(produkt) == 0) {
            this.dodajOfertęKupna(produkt, średniaCena, 0.05);
        } else {
            this.dodajOfertęKupna(produkt, średniaCena, 0.1);
            this.dodajOfertęSprzedaży(produkt, średniaCena, 0.1);
        }
    }

    @Override
    public String toString() {
        return "sredni";
    }
}
