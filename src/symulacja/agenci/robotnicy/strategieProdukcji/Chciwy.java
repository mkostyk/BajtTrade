package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;

public class Chciwy extends StrategiaProdukcji {
    public Chciwy(Robotnik robotnik) {
        super(robotnik);
    }

    @Override
    public String wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięCen();
        assert (dane != null);

        PodsumowanieDnia ostatniDzień = dane[dane.length - 1];
        double największyZysk = 0;
        String najlepszyProdukt = "";

        for (String produkt: Symulacja.Produkty) {
            double zysk = ostatniDzień.podajŚredniąCenę(produkt) * robotnik.podajProduktywność(produkt);
            if (zysk > największyZysk) {
                największyZysk = zysk;
                najlepszyProdukt = produkt;
            }
        }

        return najlepszyProdukt;
    }
}
