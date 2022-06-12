package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Średni extends Spekulant {
    private final int ileDni;

    public Średni(int id, Map<String, Double> zasoby, int ileDni) {
        super(id, zasoby);
        this.ileDni = ileDni;
    }


    @Override
    public void wystawOfertęProduktu(Produkt produkt) {
        double średniaCena = podajGiełdę().podajŚredniąCenęProduktu(ileDni, produkt);
        if (this.ileProduktów(produkt) == 0) {
            this.dodajOfertęKupna(produkt, średniaCena, 0.05);
        } else {
            this.dodajOfertęKupna(produkt, średniaCena, 0.1);
            this.dodajOfertęSprzedaży(produkt, średniaCena, 0.1);
        }
    }
}
