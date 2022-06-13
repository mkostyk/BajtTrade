package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.utils.PodsumowanieDnia;
import main.symulacja.produkty.Produkt;

import static main.Main.TypyProduktów;

public class Chciwy extends StrategiaProdukcji {
    @Override
    public void wyprodukuj() {
        PodsumowanieDnia ostatniDzień = robotnik.podajGiełdę().podajHistorięOstatnichDni(1)[0];

        double największyZysk = 0;
        Produkt najlepszyProdukt = null;

        for (TypyProduktów typ: TypyProduktów.values()) {
            int poziom = robotnik.podajPoziomyŚcieżek()[typ.ordinal()];
            double zysk = ostatniDzień.podajŚredniąCenę(new Produkt(typ, poziom)) * robotnik.podajProduktywność(typ);
            if (zysk >= największyZysk) {
                największyZysk = zysk;
                najlepszyProdukt = new Produkt(typ, poziom);
            }
        }

        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());
        System.out.println(najlepszyProdukt + " " + produktywność);

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
