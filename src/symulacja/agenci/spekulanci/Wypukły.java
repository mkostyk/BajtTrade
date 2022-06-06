package symulacja.agenci.spekulanci;

import symulacja.Symulacja;
import symulacja.utils.PodsumowanieDnia;

public class Wypukły extends Spekulant {
    @Override
    public void wystawOferty() {
        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
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
}
