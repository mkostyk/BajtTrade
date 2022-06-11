package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.PROGRAMISTA;

public class Programista extends ŚcieżkaKariery {
    public Programista(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return PROGRAMISTA;
    }
}
