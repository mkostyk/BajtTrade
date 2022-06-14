package main.symulacja.agenci.robotnicy.scieżkiKariery;

import static main.Main.Zawody;
import static main.Main.TypyProduktów;
import java.util.Objects;

public abstract class ŚcieżkaKariery {
    protected int poziom;

    public int podajBonus(TypyProduktów produkt) {
        if (Objects.equals(produkt.ordinal(), this.podajZawód().ordinal())) {
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

    public abstract Zawody podajZawód();
}
