package main.symulacja.agenci.spekulanci.strategieSpekulantów;

import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

import java.util.Map;

public class Wypukły extends Spekulant {
    public Wypukły(int id, Map<String, Double> zasoby) {
        super(id, zasoby);
    }

    @Override
    protected void wystawOfertęProduktu(Produkt produkt) {
        if (giełda.podajDzień() < 2) {
            return;
        }

        PodsumowanieDnia[] ceny = this.podajGiełdę().podajHistorięOstatnichDni(3);
        double średniaCen = (ceny[0].podajŚredniąCenę(produkt) + ceny[2].podajŚredniąCenę(produkt))/2;
        double najnowszaCena = ceny[0].podajŚredniąCenę(produkt);

        if (średniaCen < ceny[1].podajŚredniąCenę(produkt)) {
            this.dodajOfertęKupna(produkt, najnowszaCena * 0.9);
        } else {
            this.dodajOfertęSprzedaży(produkt, najnowszaCena * 1.1);
        }

    }

    @Override
    public String toString() {
        return "wypukly";
    }
}
