package main.symulacja.giełda.oferty;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

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
    public Symulacja.TypyProduktów podajTyp() {
        return produkt.podajTyp();
    } // TODO - potencjalnie do wyjebania
    public int podajPoziom() {
        return produkt.podajPoziom();
    }

    public void zmniejszWielkość(int ile) {
        this.produkt.zmniejsz(ile);
    }

    public int typID() {
        return Symulacja.ID_PRODUKTU.get(produkt.podajTyp());
    }
    public Agent podajTwórcę() {return twórca;}
}
