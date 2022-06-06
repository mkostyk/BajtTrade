package symulacja.produkty;

import symulacja.Symulacja;

public class Narzędzia extends Produkt {
    public Narzędzia(int ile, int poziom) {
        super(ile, poziom);
    }

    @Override
    public Symulacja.TypyProduktów podajTyp() {
        return Symulacja.TypyProduktów.NARZĘDZIA;
    }
}
