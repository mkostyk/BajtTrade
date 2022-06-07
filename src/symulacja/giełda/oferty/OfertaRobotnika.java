package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.produkty.Produkt;

public class OfertaRobotnika extends Oferta {
    // Oferty sprzedaży
    public OfertaRobotnika(Produkt produkt, Agent twórca) {
        super(produkt, twórca);
    }

    // Oferty kupna
    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile, Agent twórca) {
        super(new Produkt(typ, ile, 0), twórca);
    }
}
