package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

public class Perspektywiczny extends StrategiaProdukcji {
    private final int ileDni;

    public Perspektywiczny(int ileDni) {
        this.ileDni = ileDni;
    }

    @Override
    public void wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(ileDni);

        int dzieńKońcowy = dane.length;
        int dzieńStartowy = Math.min(dane.length, ileDni);

        PodsumowanieDnia stareCeny = dane[dzieńStartowy - 1];
        PodsumowanieDnia noweCeny = dane[dzieńKońcowy - 1];

        double największyWzrost = -Symulacja.INFINITY;
        Produkt najlepszyProdukt = null;

        for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
            int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(typ)];
            double wzrost = noweCeny.podajŚredniąCenę(typ, poziom) - stareCeny.podajŚredniąCenę(typ, poziom);
            if (wzrost > największyWzrost) {
                największyWzrost = wzrost;
                najlepszyProdukt = new Produkt(typ, poziom);
            }
        }


        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
