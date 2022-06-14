package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.Zawody.*;

public class Rzemieślnik extends ŚcieżkaKariery {
    public Rzemieślnik(int poziom) {
        this.poziom = poziom;
    }

    public Zawody podajZawód() {
        return RZEMIESLNIK;
    }
}