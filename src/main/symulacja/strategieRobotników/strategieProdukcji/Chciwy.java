package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

public class Chciwy extends StrategiaProdukcji {
    @Override
    public void wyprodukuj() {
        PodsumowanieDnia ostatniDzień = robotnik.podajGiełdę().podajHistorięOstatnichDni(1)[0];

        double największyZysk = 0;
        Produkt najlepszyProdukt = null;

        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(typ)];
            double zysk = ostatniDzień.podajŚredniąCenę(typ, poziom) * robotnik.podajProduktywność(typ);
            if (zysk > największyZysk) {
                największyZysk = zysk;
                najlepszyProdukt = new Produkt(typ, poziom);
            }
        }

        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
