package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;

import static symulacja.Symulacja.ILE_PRODUKTÓW;
import static symulacja.Symulacja.RNG;

public class Losowy extends StrategiaProdukcji {

    public Losowy(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public Symulacja.TypyProduktów wyprodukuj() {
        return Symulacja.TypyProduktów.values()[RNG.nextInt(ILE_PRODUKTÓW)];
    }
}
