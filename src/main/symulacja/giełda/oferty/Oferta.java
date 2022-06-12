package main.symulacja.giełda.oferty;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.produkty.Produkt;

public abstract class Oferta {
    protected Produkt produkt;
    private int ile;
    private final Agent twórca;

    protected Oferta(Produkt produkt, int ile, Agent twórca) {
        this.produkt = produkt;
        this.ile = ile;
        this.twórca = twórca;
    }

    protected Oferta(Produkt produkt, int ile) {
        this.produkt = produkt;
        this.ile = ile;
        this.twórca = null;
    }

    public int podajIle() {
        return ile;
    }

    public int podajPoziom() {
        return produkt.podajPoziom();
    }

    public Produkt podajProdukt() {
        return produkt;
    }

    public void zmniejszWielkość(int ile) {
        this.ile -= ile;
    }

    public int typID() {
        return Symulacja.ID_PRODUKTU.get(produkt.podajTyp());
    }

    public Agent podajTwórcę() {return twórca;}

    @Override
    public String toString() {
        assert twórca != null;
        return "Oferta{" +
                "produkt=" + produkt +
                "produktID=" + typID() +
                ", ile=" + ile +
                ", twórca=" + twórca.podajID() +
                '}';
    }
}
