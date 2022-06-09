package main.symulacja.giełda.oferty;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

import static main.symulacja.Symulacja.MAX_POZIOM;

public class OfertaRobotnika extends Oferta {
    // Oferty sprzedaży
    public OfertaRobotnika(Produkt produkt, int ile, Agent twórca) {
        super(produkt, ile, twórca);
    }

    // Oferty kupna
    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile, Agent twórca) {
        super(new Produkt(typ, MAX_POZIOM), ile, twórca);
    }
}
