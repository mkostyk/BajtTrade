package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja.TypyProduktów;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.produkty.Produkt;

public abstract class StrategiaProdukcji {
    public StrategiaProdukcji (Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    protected Robotnik robotnik;
    public abstract Produkt wyprodukuj();
}
