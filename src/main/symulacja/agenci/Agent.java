package main.symulacja.agenci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import static main.symulacja.Symulacja.TypyProduktów.*;

import java.util.TreeMap;

public abstract class Agent {
    protected TreeMap<Produkt, Double> produkty = new TreeMap<>(new KomparatorProduktów());
    protected Giełda giełda;
    protected int agentID;

    public double ileDiamentów() {
        return ileProduktów(new Produkt(DIAMENTY, 1));
    }

    public double ileProduktów(Produkt produkt) {
        if (produkty.get(produkt) == null) {
            return 0;
        }

        return produkty.get(produkt);
    }

    public void dodajDiamenty(double ile) {
        Produkt produkt = new Produkt(DIAMENTY, 1);
        dodajProdukty(ile, produkt);
    }

    public void dodajProdukty(double ile, Produkt produkt) {
        produkty.merge(produkt, ile, Double::sum);
    }

    public boolean zużyjProdukty(double ile, Produkt produkt) {
        if (produkty.get(produkt) == null || produkty.get(produkt) < ile) {
            produkty.put(produkt, 0.0);
            return false;
        } else {
            produkty.merge(produkt, -ile, Double::sum);
            return true;
        }
    }

    public Giełda podajGiełdę() {
        return giełda;
    }
    public int podajDzień() {
        return giełda.podajDzień();
    }
    public int podajID() {
        return agentID;
    };
}
