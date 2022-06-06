package symulacja.produkty;

import symulacja.Symulacja;

public class Programy extends Produkt {
    public Programy(int ile, int poziom) {
        super(ile, poziom);
    }

    @Override
    public Symulacja.TypyProduktów podajTyp() {
        return Symulacja.TypyProduktów.PROGRAMY;
    }
}
