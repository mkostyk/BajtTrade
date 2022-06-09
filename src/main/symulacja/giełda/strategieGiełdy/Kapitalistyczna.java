package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.TreeMap;

public class Kapitalistyczna extends Giełda {
    public Kapitalistyczna(TreeMap<Produkt, Double> cenyZerowe) {
        super(cenyZerowe);
    }

    @Override
    public void posortujOferty() {
        ofertyKupnaRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
    }
}
