package main.symulacja.agenci;

import main.symulacja.Symulacja;
import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;
import static main.symulacja.Symulacja.TypyProduktów.*;

import java.util.ArrayList;
import java.util.TreeMap;

public abstract class Agent {
    protected ArrayList<TreeMap<Produkt, Double>> produkty = new ArrayList<>(); // TODO - FIX
    protected Giełda giełda;
    protected int id;

    public TreeMap<Produkt, Double> podajProdukty(Symulacja.TypyProduktów typ) {
        return produkty.get(Symulacja.ID_PRODUKTU.get(typ));
    }

    public double ileDiamentów() {
        return ileProduktów(new Produkt(DIAMENTY, 1));
    }

    public double ileProduktów(Produkt produkt) {
        if (produkty.get(produkt.typID()).get(produkt) == null) {
            return 0;
        }

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
        if (produkty.get(produkt.typID()).get(produkt) == null || produkty.get(produkt.typID()).get(produkt) < ile) {
            produkty.get(produkt.typID()).put(produkt, 0.0);
            return false;
        } else {
            produkty.get(produkt.typID()).merge(produkt, -ile, Double::sum);
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
        return id;
    };
}
