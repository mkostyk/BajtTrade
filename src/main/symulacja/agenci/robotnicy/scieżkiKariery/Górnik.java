package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.GORNIK;

public class Górnik extends ŚcieżkaKariery {
    public Górnik(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return GORNIK;
    }
}
