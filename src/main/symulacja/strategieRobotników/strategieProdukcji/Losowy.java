package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.produkty.Produkt;

import static main.Main.*;

public class Losowy extends StrategiaProdukcji {

    @Override
    public void wyprodukuj() {
        TypyProduktów typ = TypyProduktów.values()[RNG.nextInt(ILE_PRODUKTÓW)];
        int produktywność = robotnik.podajProduktywność(typ);
        int poziom = robotnik.podajPoziomyŚcieżek()[typ.ordinal()];

        użyjProgramówIWystawProdukty(new Produkt(typ, poziom), produktywność);
    }
}
