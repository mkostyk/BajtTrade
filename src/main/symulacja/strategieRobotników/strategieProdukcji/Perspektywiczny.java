package main.symulacja.strategieRobotników.strategieProdukcji;

import com.squareup.moshi.Json;
import main.symulacja.utils.PodsumowanieDnia;
import main.symulacja.produkty.Produkt;

import static main.Main.*;

public class Perspektywiczny extends StrategiaProdukcji {
    @Json(name = "historia_perspektywy")
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

        double największyWzrost = -INFINITY;
        Produkt najlepszyProdukt = null;

        for (TypyProduktów typ: TypyProduktów.values()) {
            int poziom = robotnik.podajPoziomyŚcieżek()[typ.ordinal()];
            Produkt produkt = new Produkt(typ, poziom);
            double wzrost = noweCeny.podajŚredniąCenę(produkt) - stareCeny.podajŚredniąCenę(produkt);
            if (wzrost >= największyWzrost) {
                największyWzrost = wzrost;
                najlepszyProdukt = new Produkt(typ, poziom);
            }
        }


        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
