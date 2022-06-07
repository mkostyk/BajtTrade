package main.symulacja.giełda.oferty;

import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta (Produkt produkt, double cena, Agent twórca) {
        super(produkt, twórca);
        this.cena = cena;
    }

    public double podajCenę() {
        return cena;
    }
}
