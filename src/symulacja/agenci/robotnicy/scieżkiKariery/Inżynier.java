package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import static symulacja.Symulacja.Zawody.INŻYNIER;

public class Inżynier extends ŚcieżkaKariery {
    public Inżynier() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, INŻYNIER);
    }
}
