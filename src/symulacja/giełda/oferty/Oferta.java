package symulacja.giełda.oferty;

import symulacja.Symulacja;

public abstract class Oferta {
    protected Symulacja.TypyProduktów typ;
    protected int ile;
    protected int poziom;

    public Oferta(Symulacja.TypyProduktów typ, int ile, int poziom) {
        this.typ = typ;
        this.ile = ile;
        this.poziom = poziom;
    }
}
