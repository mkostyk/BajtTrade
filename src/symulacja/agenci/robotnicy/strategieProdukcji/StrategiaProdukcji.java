package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.produkty.Produkt;
import symulacja.utils.PodsumowanieDnia;

public abstract class StrategiaProdukcji {
    public StrategiaProdukcji (Robotnik robotnik) {
        this.robotnik = robotnik;
    }

    protected Robotnik robotnik;
    public abstract String wyprodukuj();
}
