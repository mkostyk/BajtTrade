package main.symulacja.produkty;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.TypyProduktów.*;

public class Produkt {
    private Symulacja.TypyProduktów typ;
    private int ile;
    private int poziom;

    public Produkt(Symulacja.TypyProduktów typ, int ile, int poziom) {
        this.typ = typ;
        this.ile = ile;
        this.poziom = poziom;

        if (typ == JEDZENIE || typ == DIAMENTY) {
            this.poziom = 0;
        }
    }

    public Symulacja.TypyProduktów podajTyp() {
        return typ;
    }
    public int podajIle() {
        return ile;
    }
    public int podajPoziom() {
        return poziom;
    }

    public void zmniejsz(double ile) {
        this.ile -= ile;
    }
}
