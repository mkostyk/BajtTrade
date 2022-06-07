package symulacja.agenci.spekulanci;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.giełda.oferty.OfertaSpekulanta;
import symulacja.produkty.Produkt;

public abstract class Spekulant extends Agent {
    // TODO - poziomy to inne produkty
    public abstract void wystawOferty();

    public void dodajOfertęKupna(Symulacja.TypyProduktów typ, double cenaBazowa, double marża) {
        Produkt produkt = new Produkt(typ, 100, 0);
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, cenaBazowa * (1 - marża), this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    public void dodajOfertęSprzedaży(Symulacja.TypyProduktów typ, double cenaBazowa, double marża) {
        Produkt produkt = new Produkt(typ, this.ileProduktów(typ), 0);
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, cenaBazowa * (1 + marża), this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }
}
