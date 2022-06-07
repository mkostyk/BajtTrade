package symulacja.agenci;

import symulacja.Symulacja;
import symulacja.giełda.Giełda;
import symulacja.produkty.Produkt;

import java.util.ArrayList;

public abstract class Agent {
    protected double diamenty;
    protected ArrayList<Produkt> produkty;
    protected Giełda giełda;
    protected int agentID;

    public double ileDiamentów() {
        return diamenty;
    }
    public int ileProduktów(Symulacja.TypyProduktów produkt) {
        return 0; // TODO
    }

    public void dodajDiamenty (double ile) {
        diamenty += ile;
    }
    public void dodajProdukty (double ile, Symulacja.TypyProduktów produkt) {
        // TODO
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
