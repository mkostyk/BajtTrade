package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.INZYNIER;

public class Inżynier extends ŚcieżkaKariery {
    public Inżynier(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return INZYNIER;
    }
}
