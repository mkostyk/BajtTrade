package main.symulacja.agenci.spekulanci;

import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.giełda.Giełda;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.produkty.Produkt;

public abstract class Spekulant extends Agent {
    protected Spekulant(Giełda giełda) {
        this.giełda = giełda;
    }
    public abstract void wystawOferty();

    public void dodajOfertęKupna(Symulacja.TypyProduktów typ, int poziom, double cenaBazowa, double marża) {
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }

        Produkt produkt = new Produkt(typ, poziom);
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, cenaBazowa * (1 - marża), this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    public void dodajOfertęSprzedaży(Symulacja.TypyProduktów typ, int poziom, double cenaBazowa, double marża) {
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }

        Produkt produkt = new Produkt(typ, poziom);
        // TODO - cast bad
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, (int) this.ileProduktów(produkt), cenaBazowa * (1 + marża), this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }
}
