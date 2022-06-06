package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import static symulacja.Symulacja.Zawody.PROGRAMISTA;

public class Programista extends ŚcieżkaKariery {
    public Programista() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, PROGRAMISTA);
    }
}
