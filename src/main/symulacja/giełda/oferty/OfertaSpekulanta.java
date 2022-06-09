package main.symulacja.giełda.oferty;

import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

public class OfertaSpekulanta extends Oferta {
    private double cena;

    public OfertaSpekulanta (Produkt produkt, int ile, double cena, Agent twórca) {
        super(produkt, ile, twórca);
        this.cena = cena;
    }

    public double podajCenę() {
        return cena;
    }

    @Override
    public String toString() {
        return "OfertaSpekulanta{" +
                "produkt=" + produkt +
                ", ile=" + this.podajIle() +
                ", twórca=" + this.podajTwórcę() +
                ", cena=" + cena +
                '}';
    }
}
