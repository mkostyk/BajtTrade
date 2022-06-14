package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.Zawody.*;

public class Programista extends ŚcieżkaKariery {
    public Programista(int poziom) {
        this.poziom = poziom;
    }

    public Zawody podajZawód() {
        return PROGRAMISTA;
    }
}
