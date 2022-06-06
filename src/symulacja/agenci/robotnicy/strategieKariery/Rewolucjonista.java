package symulacja.agenci.robotnicy.strategieKariery;

import symulacja.Symulacja;
import symulacja.utils.PodsumowanieDnia;

import static symulacja.Symulacja.ILE_PRODUKTÓW;

public class Rewolucjonista extends StrategiaKariery {
    @Override
    public boolean czyZmienia() {
        return (robotnik.podajDzień() % 7 == 0);
    }

    @Override
    public Symulacja.Zawody podajNowyZawód() {
        int dni = robotnik.podajID() % 17;
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(dni);
        int[] oferty = new int[ILE_PRODUKTÓW];
        // TODO dni = 0????

        for (PodsumowanieDnia dzień: dane) {
            for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
                int id = Symulacja.ID_PRODUKTU.get(produkt);
                oferty[id] += dzień.podajLiczbęOfertSprzedaży(produkt);
            }
        }

        int maksimumSprzedaży = 0;
        int nowyProduktID = 0;

        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            int id = Symulacja.ID_PRODUKTU.get(produkt);
            if (oferty[id] > maksimumSprzedaży) {
                maksimumSprzedaży = oferty[id];
                nowyProduktID = id;
            }
        }

        return Symulacja.Zawody.values()[nowyProduktID];
    }
}