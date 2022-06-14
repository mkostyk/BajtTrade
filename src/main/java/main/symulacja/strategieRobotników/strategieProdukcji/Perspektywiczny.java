package main.symulacja.strategieRobotników.strategieProdukcji;

import com.squareup.moshi.Json;
import main.symulacja.utils.PodsumowanieDnia;

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
        TypyProduktów najlepszyTyp = null;

        for (TypyProduktów typ: TypyProduktów.values()) {
            double wzrost = noweCeny.podajŚredniąCenęTypu(typ) - stareCeny.podajŚredniąCenęTypu(typ);
            if (wzrost >= największyWzrost) {
                największyWzrost = wzrost;
                najlepszyTyp = typ;
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyTyp);
        użyjProgramówIWystawProdukty(najlepszyTyp, produktywność);
    }
}
