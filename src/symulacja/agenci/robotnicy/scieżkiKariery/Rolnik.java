package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import static symulacja.Symulacja.Zawody.ROLNIK;

public class Rolnik extends ŚcieżkaKariery {
    public Rolnik() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, ROLNIK);
    }
}
