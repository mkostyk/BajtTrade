package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.ROLNIK;

public class Rolnik extends ŚcieżkaKariery {
    public Rolnik() {
        poziom = 1;
    }

    public Symulacja.Zawody podajZawód() {
        return ROLNIK;
    }
}
