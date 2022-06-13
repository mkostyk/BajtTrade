package main.symulacja.agenci.spekulanci;

import main.Main;
import main.symulacja.agenci.Agent;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów;
import static main.Main.TypyProduktów.*;

import java.util.Map;

public abstract class Spekulant extends Agent {
    protected Spekulant(int id, Map<String, Double> zasoby) {
        super(id, zasoby);
    }

    protected abstract void wystawOfertęProduktu(Produkt produkt);

    public void wystawOferty() {
        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (TypyProduktów typ: TypyProduktów.values()) {
                if ((poziom > 1 && Main.PRODUKTY_Z_POZIOMEM.contains(typ)) || poziom == 1) {
                    wystawOfertęProduktu(new Produkt(typ, poziom));
                }
            }
        }
    }

    protected void dodajOfertęKupna(Produkt produkt, double cenaBazowa, double marża) {
        if (!Main.PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())) {
            return;
        }

        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, cenaBazowa * (1 - marża), this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    protected void dodajOfertęSprzedaży(Produkt produkt, double cenaBazowa, double marża) {
        if (!Main.PRODUKTY_NA_GIEŁDZIE.contains(produkt.podajTyp())  || (int) this.ileProduktów(produkt) == 0) {
            return;
        }

        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, (int) this.ileProduktów(produkt), cenaBazowa * (1 + marża), this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
