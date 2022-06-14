package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.Zawody.*;

public class Inżynier extends ŚcieżkaKariery {
    public Inżynier(int poziom) {
        this.poziom = poziom;
    }

    public Zawody podajZawód() {
        return INZYNIER;
    }
}
