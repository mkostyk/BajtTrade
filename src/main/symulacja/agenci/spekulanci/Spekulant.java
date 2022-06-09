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
    // TODO - poziomy to inne produkty
    public abstract void wystawOferty();

    // TODO - fajnie jakby to jednak zabierało zasoby
    public void dodajOfertęKupna(Symulacja.TypyProduktów typ, int poziom, double cenaBazowa, double marża) {
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }
        // TODO - testing
        Produkt produkt = new Produkt(typ, poziom);
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, cenaBazowa * (1 - marża), this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    public void dodajOfertęSprzedaży(Symulacja.TypyProduktów typ, int poziom, double cenaBazowa, double marża) {
        if (typ == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }
        //Produkt produkt = new Produkt(typ, this.ileProduktów(typ), 0);
        // TODO - testing
        Produkt produkt = new Produkt(typ, poziom);
        // TODO - testing, zamienić 200 na this.ileProduktów(typ)
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 200, cenaBazowa * (1 + marża), this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }
}
