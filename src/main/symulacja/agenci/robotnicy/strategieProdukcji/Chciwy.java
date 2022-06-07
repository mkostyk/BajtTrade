package main.symulacja.agenci.robotnicy.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

public class Chciwy extends StrategiaProdukcji {
    public Chciwy(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public Produkt wyprodukuj() {
        PodsumowanieDnia ostatniDzień = robotnik.podajGiełdę().podajHistorięOstatnichDni(1)[0];

        double największyZysk = 0;
        Symulacja.TypyProduktów najlepszyProdukt = null;

        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            double zysk = ostatniDzień.podajŚredniąCenę(produkt) * robotnik.podajProduktywność(produkt);
            if (zysk > największyZysk) {
                największyZysk = zysk;
                najlepszyProdukt = produkt;
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyProdukt);
        int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(najlepszyProdukt)];

        return new Produkt(najlepszyProdukt, produktywność, poziom);
    }
}
