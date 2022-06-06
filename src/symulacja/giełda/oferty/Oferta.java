package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;

public abstract class Oferta {
    protected Symulacja.TypyProduktów typ;
    protected int ile;
    protected int poziom;
    private Agent twórca;

    public Oferta(Symulacja.TypyProduktów typ, int ile, int poziom, Agent twórca) {
        this.typ = typ;
        this.ile = ile;
        this.poziom = poziom;
        this.twórca = twórca;
    }

    public int ile() {
        return ile;
    }

    public int typID() {
        return Symulacja.ID_PRODUKTU.get(typ);
    }
}
