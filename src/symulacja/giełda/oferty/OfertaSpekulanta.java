package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.produkty.Produkt;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta (Produkt produkt, double cena, Agent twórca) {
        super(produkt, twórca);
        this.cena = cena;
    }

    public double cena() {
        return cena;
    }
}
