package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.GÓRNIK;

public class Górnik extends ŚcieżkaKariery {
    public Górnik() {
        poziom = 1;
    }

    public Symulacja.Zawody podajZawód() {
        return GÓRNIK;
    }
}
