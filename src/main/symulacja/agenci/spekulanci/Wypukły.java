package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;
import main.symulacja.utils.PodsumowanieDnia;

public class Wypukły extends Spekulant {
    public Wypukły(Giełda giełda) {
        super(giełda);
    }

    @Override
    public void wystawOferty() {
        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                // TODO - mniej niż 3 dni
                PodsumowanieDnia[] ceny = this.podajGiełdę().podajHistorięOstatnichDni(3);
                double średniaCen = (ceny[0].podajŚredniąCenę(typ, poziom) + ceny[2].podajŚredniąCenę(typ, poziom))/2;
                double najnowszaCena = ceny[0].podajŚredniąCenę(typ, poziom);

                if (średniaCen < ceny[1].podajŚredniąCenę(typ, poziom)) {
                    this.dodajOfertęKupna(typ, poziom, najnowszaCena, 0.1);
                } else {
                    this.dodajOfertęSprzedaży(typ, poziom, najnowszaCena, 0.1);
                }
            }
        }

    }
}
