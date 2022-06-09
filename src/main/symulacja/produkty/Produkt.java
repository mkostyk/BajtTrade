package main.symulacja.produkty;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Produkt {
    private Symulacja.TypyProduktów typ;
    private int poziom;

    public Produkt(Symulacja.TypyProduktów typ, int poziom) {
        this.typ = typ;
        this.poziom = poziom;

        if (typ == JEDZENIE || typ == DIAMENTY) {
            this.poziom = 1;
        }
    }

    public Symulacja.TypyProduktów podajTyp() {
        return typ;
    }
    public int typID() {
        return Symulacja.ID_PRODUKTU.get(podajTyp());
    }
    public int podajPoziom() {
        return poziom;
    }


    @Override
    public String toString() {
        return "Produkt{" +
                "typ=" + typ +
                ", poziom=" + poziom +
                '}';
    }
}
