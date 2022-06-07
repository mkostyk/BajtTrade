package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import java.util.Objects;

public abstract class ŚcieżkaKariery {
    protected int poziom;

    public int podajBonus(Symulacja.TypyProduktów produkt, Symulacja.Zawody zawód) {
        if (Objects.equals(Symulacja.ID_PRODUKTU.get(produkt), Symulacja.ID_KARIERY.get(zawód))) {
            return switch (poziom) {
                case 1 -> 50;
                case 2 -> 150;
                default -> 300 + 25 * (poziom - 3);
            };
        } else {
            return 0;
        }
    }

    public abstract int podajBonus(Symulacja.TypyProduktów produkt);

    public void dodajPoziom() {
        poziom++;
    }

    public int podajPoziom() {
        return poziom;
    }
}
