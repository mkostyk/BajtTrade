package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.Zawody.*;

public class Rolnik extends ŚcieżkaKariery {
    public Rolnik(int poziom) {
        this.poziom = poziom;
    }

    public Zawody podajZawód() {
        return ROLNIK;
    }
}
