package main.symulacja.agenci;

import main.symulacja.giełda.Giełda;
import main.Main;
import main.symulacja.Symulacja;
import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów;
import static main.Main.TypyProduktów.*;

import java.util.List;
import java.util.Map;

public abstract class Agent {
    protected List<Map<Produkt, Double>> produkty;
    protected transient Giełda giełda;
    protected int id;

    protected Agent(int id, Map<String, Double> produkty) {
        this.id = id;
        this.produkty = Main.stwórzListęMapProduktów(produkty);
    }

    public void ustawGiełdę(Giełda giełda) {
        this.giełda = giełda;
    }

    public List<Map<Produkt, Double>> podajWszystkieProdukty() {
        return produkty;
    }

    public Map<Produkt, Double> podajProdukty(TypyProduktów typ) {
        return produkty.get(typ.ordinal());
    }

    // TODO - czy zostawić osobne funkcje na diamenty?

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
        if (produkty.get(produkt.typID()).get(produkt) == null || produkty.get(produkt.typID()).get(produkt) < ile) {
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

    /*public String produktyToString() {
        Map<String, Double[]> produkty = java.stwórzMapęTablicProduktów(this);
        StringBuilder sb = new StringBuilder();
        for(String nazwa: produkty.keySet()) {
            sb.append(nazwa)
        }
    }*/

    public Giełda podajGiełdę() {
        return giełda;
    }
    public int podajDzień() {
        return giełda.podajDzień();
    }
    public int podajID() {
        return id;
    }

    @Override
    public String toString() {
        return "Agent{" +
                "produkty=" + produkty +
                ", giełda=" + giełda +
                ", id=" + id +
                '}';
    }
}
