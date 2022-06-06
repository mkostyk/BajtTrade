package symulacja.agenci.robotnicy.scieżkiKariery;

import symulacja.Symulacja;

import static symulacja.Symulacja.Zawody.ROLNIK;
import static symulacja.Symulacja.Zawody.RZEMIEŚLNIK;

public class Rzemieślnik extends ŚcieżkaKariery {
    public Rzemieślnik() {
        poziom = 1;
    }

    public int podajBonus(Symulacja.TypyProduktów produkt) {
        return super.podajBonus(produkt, RZEMIEŚLNIK);
    }
}