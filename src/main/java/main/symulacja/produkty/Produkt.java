package main.symulacja.produkty;

import main.Main;

public class Produkt {
    private final Main.TypyProduktów typ;
    private final int poziom;
    private final int wytrzymałość;

    public Produkt(Main.TypyProduktów typ, int poziom) {
        this.typ = typ;
        if (typ == Main.TypyProduktów.UBRANIA) {
            this.wytrzymałość = poziom * poziom;
        } else {
            this.wytrzymałość = 1;
        }

        if (!Main.PRODUKTY_Z_POZIOMEM.contains(typ)) {
            this.poziom = 1;
        } else {
            this.poziom = poziom;
        }
    }

    public Produkt(Main.TypyProduktów typ, int poziom, int wytrzymałość) {
        this.typ = typ;
        this.poziom = poziom;
        this.wytrzymałość = wytrzymałość;
    }

    public Main.TypyProduktów podajTyp() {
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
