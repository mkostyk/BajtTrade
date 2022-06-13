package main.symulacja.produkty;

import static main.Main.PRODUKTY_Z_POZIOMEM;
import static main.Main.TypyProduktów;

public class Produkt {
    private final TypyProduktów typ;
    private final int poziom;
    private final int wytrzymałość;

    public Produkt(TypyProduktów typ, int poziom) {
        this.typ = typ;
        if (typ == TypyProduktów.UBRANIA) {
            this.wytrzymałość = poziom * poziom;
        } else {
            this.wytrzymałość = 1;
        }

        if (!PRODUKTY_Z_POZIOMEM.contains(typ)) {
            this.poziom = 1;
        } else {
            this.poziom = poziom;
        }
    }

    public Produkt(TypyProduktów typ, int poziom, int wytrzymałość) {
        this.typ = typ;
        this.poziom = poziom;
        this.wytrzymałość = wytrzymałość;
    }

    public TypyProduktów podajTyp() {
        return typ;
    }
    public int typID() {
        return podajTyp().ordinal();
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
