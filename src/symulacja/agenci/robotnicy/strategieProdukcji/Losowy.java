package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.produkty.Produkt;

import static symulacja.Symulacja.ILE_PRODUKTÓW;
import static symulacja.Symulacja.RNG;

public class Losowy extends StrategiaProdukcji {

    public Losowy(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public Produkt wyprodukuj() {
        Symulacja.TypyProduktów typ = Symulacja.TypyProduktów.values()[RNG.nextInt(ILE_PRODUKTÓW)];
        int produktywność = robotnik.podajProduktywność(typ);
        int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(typ)];

        return new Produkt(typ, produktywność, poziom);
    }
}
