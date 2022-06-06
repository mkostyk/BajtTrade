package symulacja.produkty;

import symulacja.Symulacja;

public class Ubrania extends Produkt {
    private int wytrzymałość;

    public Ubrania(int ile, int poziom) {
        super(ile, poziom);
        wytrzymałość = poziom * poziom;
    }

    @Override
    public Symulacja.TypyProduktów podajTyp() {
        return Symulacja.TypyProduktów.UBRANIA;
    }

    public boolean zużyj() {
        if (wytrzymałość <= 0) {
            return false;
        } else {
            wytrzymałość--;
            return true;
        }
    }
}
