package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.ROLNIK;

public class Rolnik extends ŚcieżkaKariery {
    public Rolnik(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return ROLNIK;
    }
}
