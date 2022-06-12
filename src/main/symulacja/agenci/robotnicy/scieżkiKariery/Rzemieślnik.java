package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.RZEMIESLNIK;

public class Rzemieślnik extends ŚcieżkaKariery {
    public Rzemieślnik(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return RZEMIESLNIK;
    }
}