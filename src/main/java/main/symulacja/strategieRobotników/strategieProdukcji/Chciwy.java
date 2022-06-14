package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.utils.PodsumowanieDnia;

import static main.Main.TypyProduktów;

public class Chciwy extends StrategiaProdukcji {
    @Override
    public void wyprodukuj() {
        PodsumowanieDnia ostatniDzień = robotnik.podajGiełdę().podajHistorięOstatnichDni(1)[0];

        double największyZysk = 0;
        TypyProduktów najlepszyTyp = null;

        for (TypyProduktów typ: TypyProduktów.values()) {
            double zysk = ostatniDzień.podajŚredniąCenęTypu(typ) * robotnik.podajProduktywność(typ);
            if (zysk >= największyZysk) {
                największyZysk = zysk;
                najlepszyTyp = typ;
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyTyp);
        użyjProgramówIWystawProdukty(najlepszyTyp, produktywność);
    }
}
