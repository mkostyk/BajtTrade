package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;

public class Perspektywiczny extends StrategiaProdukcji {
    private int ileDni;

    public Perspektywiczny(Robotnik robotnik, int ileDni) {
        super(robotnik);
        this.ileDni = ileDni;
    }

    @Override
    public String wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięCen();
        assert (dane != null);

        int dzieńKońcowy = dane.length - 1;
        int dzieńStartowy = Math.max(dane.length - ileDni, 0);

        PodsumowanieDnia stareCeny = dane[dzieńStartowy];
        PodsumowanieDnia noweCeny = dane[dzieńKońcowy];

        double największyWzrost = -Symulacja.INFINITY;
        String najlepszyProdukt = "";

        for (String produkt: Symulacja.Produkty) {
            double wzrost = noweCeny.podajŚredniąCenę(produkt) - stareCeny.podajŚredniąCenę(produkt);
            if (wzrost > największyWzrost) {
                największyWzrost = wzrost;
                najlepszyProdukt = produkt;
            }
        }

        return najlepszyProdukt;
    }
}
