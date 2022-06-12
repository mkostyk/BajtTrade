package main.symulacja.komparatory;

import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.produkty.Produkt;

import java.util.Comparator;

public class KomparatorProduktów implements Comparator<Produkt> {

    public int compare(Produkt pierwszyProdukt, Produkt drugiProdukt) {
        // Sortowanie po typie produktu w ofercie - rosnąco
        if (pierwszyProdukt.typID() > drugiProdukt.typID()) {
            return 1;
        } else if (pierwszyProdukt.typID() < drugiProdukt.typID()){
            return -1;
        }

        if (pierwszyProdukt.podajWytrzymałość() > drugiProdukt.podajWytrzymałość()) {
            return 1;
        } else if (drugiProdukt.podajWytrzymałość() > pierwszyProdukt.podajWytrzymałość()) {
            return -1;
        }

        // Sortowanie po poziomie produktu w ofercie - malejąco
        return Integer.compare(pierwszyProdukt.podajPoziom(), drugiProdukt.podajPoziom());
    }
}
