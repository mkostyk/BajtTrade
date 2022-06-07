package main.symulacja.agenci.robotnicy.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;
import static main.symulacja.Symulacja.RNG;

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
