package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;

import java.util.TreeMap;

public class Zrównoważona extends Giełda {
    public Zrównoważona(TreeMap<Produkt, Double> cenyZerowe) {
        super(cenyZerowe);
    }
    // TODO
}
