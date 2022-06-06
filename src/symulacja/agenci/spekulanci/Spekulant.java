package symulacja.agenci.spekulanci;

import symulacja.Symulacja;
import symulacja.agenci.Agent;
import symulacja.giełda.oferty.OfertaSpekulanta;

public abstract class Spekulant extends Agent {
    // TODO - poziomy to inne produkty
    public abstract void wystawOferty();

    public void dodajOfertęKupna(Symulacja.TypyProduktów produkt, double cenaBazowa, double marża) {
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, 0, cenaBazowa * (1 - marża));
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    public void dodajOfertęSprzedaży(Symulacja.TypyProduktów produkt, double cenaBazowa, double marża) {
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, this.ileProduktów(produkt), 0, cenaBazowa * (1 + marża));
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }
}
