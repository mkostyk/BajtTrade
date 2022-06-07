package symulacja.giełda.oferty;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.produkty.Produkt;

public abstract class Oferta {
    protected Produkt produkt;
    private Agent twórca;

    public Oferta(Produkt produkt, Agent twórca) {
        this.produkt = produkt;
        this.twórca = twórca;
    }

    public int podajIle() {
        return produkt.podajIle();
    }

    public int typID() {
        return Symulacja.ID_PRODUKTU.get(produkt.podajTyp());
    }
}
