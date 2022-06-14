package main.symulacja.strategieRobotników.strategieProdukcji;

import static main.Main.*;

public class Losowy extends StrategiaProdukcji {

    @Override
    public void wyprodukuj() {
        TypyProduktów typ = TypyProduktów.values()[RNG.nextInt(ILE_PRODUKTÓW)];
        int produktywność = robotnik.podajProduktywność(typ);
        użyjProgramówIWystawProdukty(typ, produktywność);
    }
}
