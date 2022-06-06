package symulacja.produkty;

import symulacja.Symulacja;

public abstract class Produkt {
    // TODO - jak diaxy osobno to może int?
    private int ile;
    private int poziom;

    public Produkt(int ile, int poziom) {
        this.ile = ile;
        this.poziom = poziom;
    }

    public abstract Symulacja.TypyProduktów podajTyp();
    public int ile() {
        return ile;
    }

    public void zmniejsz(double ile) {
        this.ile -= ile;
    }
}
