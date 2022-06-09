package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.TreeMap;

public class Socjalistyczna extends Giełda {
    public Socjalistyczna(TreeMap<Produkt, Double> cenyZerowe) {
        super(cenyZerowe);
    }

    @Override
    public void posortujOferty() {
        ofertyKupnaRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
    }
}
