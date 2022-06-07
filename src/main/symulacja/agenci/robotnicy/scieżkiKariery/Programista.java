package main.symulacja.agenci.robotnicy.scieżkiKariery;

import main.symulacja.Symulacja;

import static main.symulacja.Symulacja.Zawody.PROGRAMISTA;

public class Programista extends ŚcieżkaKariery {
    public Programista() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, PROGRAMISTA);
    }
}
