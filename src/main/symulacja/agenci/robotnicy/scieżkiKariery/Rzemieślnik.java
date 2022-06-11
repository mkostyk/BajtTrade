package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.RZEMIEŚLNIK;

public class Rzemieślnik extends ŚcieżkaKariery {
    public Rzemieślnik(int poziom) {
        this.poziom = poziom;
    }

    public Symulacja.Zawody podajZawód() {
        return RZEMIEŚLNIK;
    }
}