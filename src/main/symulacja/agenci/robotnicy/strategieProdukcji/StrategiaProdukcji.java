package main.symulacja.agenci.robotnicy.strategieProdukcji;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;

public abstract class StrategiaProdukcji {
    public StrategiaProdukcji (Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    protected Robotnik robotnik;
    public abstract Produkt wyprodukuj();
}
