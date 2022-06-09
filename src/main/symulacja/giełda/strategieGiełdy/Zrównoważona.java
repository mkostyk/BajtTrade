package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.symulacja.komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.TreeMap;

public class Zrównoważona extends Giełda {
    public Zrównoważona(TreeMap<Produkt, Double> cenyZerowe) {
        super(cenyZerowe);
    }

    @Override
    public void posortujOferty() {
        if (this.podajDzień() % 2 == 0) {
            ofertyKupnaRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
        } else {
            ofertyKupnaRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
        }
    }
}
