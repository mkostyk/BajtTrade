package main.symulacja.strategieRobotników.strategieKariery;

import main.symulacja.utils.PodsumowanieDnia;

import static main.Main.*;

public class Rewolucjonista extends StrategiaKariery {
    @Override
    public boolean czyZmienia() {
        return (robotnik.podajDzień() % 7 == 0);
    }

    @Override
    public Zawody podajNowyZawód() {
        int dni = Math.max(1, robotnik.podajID() % 17);
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(dni);
        int[] oferty = new int[ILE_PRODUKTÓW];

        for (PodsumowanieDnia dzień: dane) {
            for (TypyProduktów typ: TypyProduktów.values()) {
                int id = typ.ordinal();
                oferty[id] += dzień.podajLiczbęOfertSprzedaży(typ);
            }
        }

        int maksimumSprzedaży = 0;
        int nowyProduktID = 0;

        for (TypyProduktów typ: TypyProduktów.values()) {
            int id = typ.ordinal();
            if (oferty[id] >= maksimumSprzedaży) {
                maksimumSprzedaży = oferty[id];
                nowyProduktID = id;
            }
        }

        return Zawody.values()[nowyProduktID];
    }

    @Override
    public String toString() {
        return "rewolucjonista";
    }
}
