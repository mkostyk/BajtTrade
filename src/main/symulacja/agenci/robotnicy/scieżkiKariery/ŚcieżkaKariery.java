package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import java.util.Objects;

public abstract class ŚcieżkaKariery {
    protected int poziom;

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        if (Objects.equals(Symulacja.ID_PRODUKTU.get(produkt), Symulacja.ID_KARIERY.get(this.podajZawód()))) {
            return switch (poziom) {
                case 1 -> 50;
                case 2 -> 150;
                default -> 300 + 25 * (poziom - 3);
            };
        } else {
            return 0;
        }
    }

    public void dodajPoziom() {
        poziom++;
    }

    public int podajPoziom() {
        return poziom;
    }

    public abstract Symulacja.Zawody podajZawód();

    @Override
    public String toString() {
        return "ŚcieżkaKariery{" +
                "typ=" + this.getClass().getSimpleName() + ", " +
                "poziom=" + poziom +
                '}';
    }
}
