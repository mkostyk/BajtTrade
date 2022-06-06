package symulacja.giełda.oferty;

import symulacja.Symulacja;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta (Symulacja.TypyProduktów typ, int ile, int poziom, double cena) {
        super(typ, ile, poziom);
        this.cena = cena;
    }
}
