package main.symulacja.agenci;

import main.symulacja.giełda.Giełda;
import main.Main;
import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów;
import static main.Main.TypyProduktów.*;

import java.util.List;
import java.util.Map;

public abstract class Agent {
    protected final List<Map<Produkt, Double>> produkty;
    protected Giełda giełda;
    protected final int id;

    protected Agent(int id, Map<String, Double> produkty) {
        this.id = id;
        this.produkty = Main.stwórzListęMapProduktów(produkty);
    }

    public Giełda podajGiełdę() {
        return giełda;
    }
    public int podajDzień() {
        return giełda.podajDzień();
    }
    public int podajID() {
        return id;
    }

    public void ustawGiełdę(Giełda giełda) {
        this.giełda = giełda;
    }

    public Map<Produkt, Double> podajProdukty(TypyProduktów typ) {
        return produkty.get(typ.ordinal());
    }

    // Osobne funkcje do diamentów służą zwiększeniu czytelności kodu w niektórych miejscach,
    // jako że diamenty są zdecydowanie najczęściej używanym przedmiotem.

    public double ileDiamentów() {
        return ileProduktów(new Produkt(DIAMENTY, 1));
    }

    public double ileProduktów(Produkt produkt) {
        produkty.get(produkt.typID()).putIfAbsent(produkt, 0.0);
        return produkty.get(produkt.typID()).get(produkt);
    }

    public void dodajDiamenty(double ile) {
        Produkt produkt = new Produkt(DIAMENTY, 1);
        dodajProdukty(ile, produkt);
    }

    public void dodajProdukty(double ile, Produkt produkt) {
        produkty.get(produkt.typID()).merge(produkt, ile, Double::sum);
    }

    public void zużyjDiamenty(double ile) {
        Produkt produkt = new Produkt(DIAMENTY, 1);
        zużyjProdukty(ile, produkt);
    }

    public boolean zużyjProdukty(double ile, Produkt produkt) {
        if (ileProduktów(produkt) < ile) {
            produkty.get(produkt.typID()).put(produkt, 0.0);
            return false;
        } else {
            produkty.get(produkt.typID()).merge(produkt, -ile, Double::sum);
            return true;
        }
    }

    public void usuńZbędneProdukty() {
        for (TypyProduktów typ: TypyProduktów.values()) {
            produkty.get(typ.ordinal()).values().removeIf(value -> value <= 0);
        }
    }
}
