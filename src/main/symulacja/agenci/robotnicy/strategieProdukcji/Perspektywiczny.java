package main.symulacja.agenci.robotnicy.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

public class Perspektywiczny extends StrategiaProdukcji {
    private final int ileDni;

    public Perspektywiczny(Robotnik robotnik, int ileDni) {
        super(robotnik);
        this.ileDni = ileDni;
    }

    @Override
    public Produkt wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(ileDni);

        int dzieńKońcowy = dane.length;
        int dzieńStartowy = Math.min(dane.length, ileDni);

        PodsumowanieDnia stareCeny = dane[dzieńStartowy - 1];
        PodsumowanieDnia noweCeny = dane[dzieńKońcowy - 1];

        double największyWzrost = -Symulacja.INFINITY;
        Symulacja.TypyProduktów najlepszyProdukt = null;

        for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
            double wzrost = noweCeny.podajŚredniąCenę(produkt) - stareCeny.podajŚredniąCenę(produkt);
            if (wzrost > największyWzrost) {
                największyWzrost = wzrost;
                najlepszyProdukt = produkt;
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyProdukt);
        int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(najlepszyProdukt)];

        return new Produkt(najlepszyProdukt, produktywność, poziom);
    }
}
