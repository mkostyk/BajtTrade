package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import static symulacja.Symulacja.Zawody.GÓRNIK;

public class Górnik extends ŚcieżkaKariery {
    public Górnik() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, GÓRNIK);
    }
}
