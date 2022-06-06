package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.produkty.Produkt;

public class OfertaRobotnika extends Oferta {
    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile, int poziom, Agent twórca) {
        super(typ, ile, poziom, twórca);
    }

    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile, Agent twórca) {
        super(typ, ile, 0, twórca);
    }
}
