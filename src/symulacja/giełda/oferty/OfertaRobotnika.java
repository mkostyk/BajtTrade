package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.produkty.Produkt;

public class OfertaRobotnika extends Oferta {
    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile, int poziom) {
        super(typ, ile, poziom);
    }

    public OfertaRobotnika(Symulacja.TypyProduktów typ, int ile) {
        super(typ, ile, 0);
    }
}
