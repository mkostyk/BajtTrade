package main.symulacja.agenci.spekulanci;

import main.Main;
import main.symulacja.agenci.Agent;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów;

import java.util.Map;

public abstract class Spekulant extends Agent {
    protected Spekulant(int id, Map<String, Double> zasoby) {
        super(id, zasoby);
    }

    protected abstract void wystawOfertęProduktu(Produkt produkt);

    public void usuńSprzedaneProdukty(double ile, Produkt produkt) {
        zużyjProdukty(ile, produkt);
    }

    public void wystawOferty() {
        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (TypyProduktów typ: TypyProduktów.values()) {
                if ((poziom > 1 && Main.PRODUKTY_Z_POZIOMEM.contains(typ)) || poziom == 1) {
                    wystawOfertęProduktu(new Produkt(typ, poziom));
                }
            }
        }
    }

    protected void dodajOfertęKupna(Produkt produkt, double cena) {
        if (!Main.PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            return;
        }

        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, cena, this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    protected void dodajOfertęSprzedaży(Produkt produkt, double cena) {
        if (!Main.PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())  || (int) this.ileProduktów(produkt) == 0) {
            return;
        }

        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, (int) this.ileProduktów(produkt), cena, this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }
}
