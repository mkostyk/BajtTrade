package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;

public class Perspektywiczny extends StrategiaProdukcji {
    private final int ileDni;

    public Perspektywiczny(Robotnik robotnik, int ileDni) {
        super(robotnik);
        this.ileDni = ileDni;
    }

    @Override
    public Symulacja.TypyProduktów wyprodukuj() {
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

        return najlepszyProdukt;
    }
}
