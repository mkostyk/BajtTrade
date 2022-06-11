package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;

import static main.symulacja.Symulacja.ILE_PRODUKTÓW;
import static main.symulacja.Symulacja.RNG;

public class Losowy extends StrategiaProdukcji {

    @Override
    public void wyprodukuj() {
        Symulacja.TypyProduktów typ = Symulacja.TypyProduktów.values()[RNG.nextInt(ILE_PRODUKTÓW)];
        int produktywność = robotnik.podajProduktywność(typ);
        int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(typ)];

        użyjProgramówIWystawProdukty(new Produkt(typ, poziom), produktywność);
    }
}
