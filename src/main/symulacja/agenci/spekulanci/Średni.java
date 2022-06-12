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
    public void wystawOferty() {
        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                Produkt produkt = new Produkt(typ, poziom);
                double średniaCena = podajGiełdę().podajŚredniąCenęProduktu(ileDni, typ, poziom);
                if (this.ileProduktów(produkt) == 0) {
                    this.dodajOfertęKupna(typ, poziom, średniaCena, 0.05);
                } else {
                    this.dodajOfertęKupna(typ, poziom, średniaCena, 0.1);
                    this.dodajOfertęSprzedaży(typ, poziom, średniaCena, 0.1);
                }
            }
        }
    }
}
