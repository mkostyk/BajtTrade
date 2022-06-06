package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta (Symulacja.TypyProduktów typ, int ile, int poziom, double cena, Agent twórca) {
        super(typ, ile, poziom, twórca);
        this.cena = cena;
    }

    public double cena() {
        return cena;
    }
}
