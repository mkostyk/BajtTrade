package symulacja.produkty;

import symulacja.Symulacja;

public class Jedzenie extends Produkt {
    public Jedzenie(int ile, int poziom) {
        super(ile, poziom);
    }

    @Override
    public Symulacja.TypyProduktów podajTyp() {
        return Symulacja.TypyProduktów.JEDZENIE;
    }
}
