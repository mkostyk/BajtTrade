package symulacja.agenci;

import symulacja.Symulacja;
import symulacja.giełda.Giełda;
import symulacja.produkty.Produkt;

public abstract class Agent {
    private double diamenty; // TODO
    private Produkt[] produkty;
    private Giełda giełda;
    private int agentID;

    public double ileDiamentów() {
        return diamenty;
    }
    public int ileProduktów(Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        return produkty[id].ile();
    }

    public void dodajDiamenty (double ile) {
        diamenty += ile;
    }
    public void dodajProdukty (double ile, Symulacja.TypyProduktów produkt) {
        int id = Symulacja.ID_PRODUKTU.get(produkt);
        produkty[id].zmniejsz(ile);
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
