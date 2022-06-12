package main.symulacja.agenci.spekulanci;

import main.Main;
import main.symulacja.Symulacja;
import main.symulacja.agenci.Agent;
import main.symulacja.giełda.Giełda;
import main.symulacja.giełda.oferty.OfertaSpekulanta;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public abstract class Spekulant extends Agent {
    protected Spekulant(int id, Map<String, Double> zasoby) {
        // TODO - zasoby
        this.id = id;
        this.giełda = giełda;
        this.produkty = Main.stwórzListęMapProduktów(zasoby); // TODO - wrzucić do Agenta
    }

    public abstract void wystawOfertęProduktu(Produkt produkt);

    public void wystawOferty() {
        for (int poziom = 1; poziom <= giełda.podajMaksymalnyPoziom(); poziom++) {
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                // TODO - nie podoba mi się to
                if (poziom > 1 && typ == Symulacja.TypyProduktów.JEDZENIE) {
                    continue;
                } else {
                    wystawOfertęProduktu(new Produkt(typ, poziom));
                }
            }
        }
    }

    public void dodajOfertęKupna(Produkt produkt, double cenaBazowa, double marża) {
        if (produkt.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }

        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, 100, cenaBazowa * (1 - marża), this);
        this.podajGiełdę().dodajOfertęKupnaSpekulanta(oferta);
    }

    public void dodajOfertęSprzedaży(Produkt produkt, double cenaBazowa, double marża) {
        if (produkt.podajTyp() == Symulacja.TypyProduktów.DIAMENTY) {
            return;
        }

        // TODO - cast bad
        OfertaSpekulanta oferta = new OfertaSpekulanta(produkt, (int) this.ileProduktów(produkt), cenaBazowa * (1 + marża), this);
        this.podajGiełdę().dodajOfertęSprzedażySpekulanta(oferta);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
