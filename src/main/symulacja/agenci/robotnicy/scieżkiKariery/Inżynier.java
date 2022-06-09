package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.INŻYNIER;

public class Inżynier extends ŚcieżkaKariery {
    public Inżynier() {
        poziom = 1;
    }

    public Symulacja.Zawody podajZawód() {
        return INŻYNIER;
    }
}
