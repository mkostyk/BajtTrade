package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.Zawody.*;

public class Górnik extends ŚcieżkaKariery {
    public Górnik(int poziom) {
        this.poziom = poziom;
    }

    public Zawody podajZawód() {
        return GORNIK;
    }
}
