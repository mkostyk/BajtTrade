package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;

public class Średni extends Spekulant {
    private final int ileDni;

    public Średni(int ileDni) {
        this.ileDni = ileDni;
    }

    @Override
    public void wystawOferty() {
        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            double średniaCena = podajGiełdę().podajŚredniąCenęProduktu(ileDni, produkt);
            if (this.ileProduktów(produkt) == 0) {
                this.dodajOfertęKupna(produkt, średniaCena, 0.05);
            } else {
                this.dodajOfertęKupna(produkt, średniaCena, 0.1);
                this.dodajOfertęSprzedaży(produkt, średniaCena, 0.1);
            }
        }
    }
}
