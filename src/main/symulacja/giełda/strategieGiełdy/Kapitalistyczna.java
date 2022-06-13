package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Kapitalistyczna extends Giełda {
    public Kapitalistyczna(Map<Produkt, Double> cenyZerowe, int karaZaUbrania) {
        super(cenyZerowe, karaZaUbrania);
    }

    @Override
    public void posortujOferty() {
        ofertyKupnaRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
        ofertySprzedażyRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
    }

    @Override
    public String toString() {
        return "kapitalistyczna";
    }
}
