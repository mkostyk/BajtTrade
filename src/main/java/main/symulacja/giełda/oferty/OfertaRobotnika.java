package main.symulacja.giełda.oferty;

import main.Main;
import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

public class OfertaRobotnika extends Oferta {
    // Oferty sprzedaży
    public OfertaRobotnika(Produkt produkt, int ile, Agent twórca) {
        super(produkt, ile, twórca);
    }

    // Oferty kupna - zawsze maksymalny poziom
    public OfertaRobotnika(Main.TypyProduktów typ, int ile, Agent twórca) {
        super(new Produkt(typ, Main.MAX_POZIOM), ile, twórca);
    }
}
