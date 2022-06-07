package main.symulacja.komparatory;

import main.symulacja.giełda.oferty.OfertaSpekulanta;

import java.util.Comparator;

public class KomparatorOfertSprzedażySpekulantów implements Comparator<OfertaSpekulanta> {
    @Override
    public int compare(OfertaSpekulanta pierwszaOferta, OfertaSpekulanta drugaOferta) {
        // Sortowanie po typie produktu w ofercie
        if (pierwszaOferta.typID() > drugaOferta.typID()) {
            return 1;
        } else if (pierwszaOferta.typID() < drugaOferta.typID()){
            return -1;
        }

        // Sortowanie po poziomie produktu w ofercie
        if (pierwszaOferta.podajPoziom() > drugaOferta.podajPoziom()) {
            return 1;
        } else if (pierwszaOferta.podajPoziom() < drugaOferta.podajPoziom()) {
            return -1;
        }

        // Sortowanie po cenie
        return -Double.compare(pierwszaOferta.podajCenę(), drugaOferta.podajCenę());
    }
}
