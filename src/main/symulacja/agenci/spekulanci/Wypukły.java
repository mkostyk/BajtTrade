package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

import java.util.Map;

public class Wypukły extends Spekulant {

    public Wypukły(int id, Map<String, Double> zasoby) {
        super(id, zasoby);
    }

    @Override
    public void wystawOfertęProduktu(Produkt produkt) {
        if (giełda.podajDzień() < 3) {
            return;
        }

        PodsumowanieDnia[] ceny = this.podajGiełdę().podajHistorięOstatnichDni(3);
        double średniaCen = (ceny[0].podajŚredniąCenę(produkt) + ceny[2].podajŚredniąCenę(produkt))/2;
        double najnowszaCena = ceny[0].podajŚredniąCenę(produkt);

        if (średniaCen < ceny[1].podajŚredniąCenę(produkt)) {
            this.dodajOfertęKupna(produkt, najnowszaCena, 0.1);
        } else {
            this.dodajOfertęSprzedaży(produkt, najnowszaCena, 0.1);
        }

    }
}
