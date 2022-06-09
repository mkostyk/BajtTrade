package main.symulacja.produkty;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Produkt {
    private final Symulacja.TypyProduktów typ;
    private final int poziom;
    private final int wytrzymałość;

    // TODO - osobne klasy?
    public Produkt(Symulacja.TypyProduktów typ, int poziom) {
        this.typ = typ;
        if (typ == UBRANIA) {
            this.wytrzymałość = poziom * poziom;
        } else {
            this.wytrzymałość = 1;
        }

        if (typ == JEDZENIE || typ == DIAMENTY) {
            this.poziom = 1;
        } else {
            this.poziom = poziom;
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
    public int podajWytrzymałość() {
        return wytrzymałość;
    }


    @Override
    public String toString() {
        return "Produkt{" +
                "typ=" + typ +
                ", poziom=" + poziom +
                '}';
    }
}
