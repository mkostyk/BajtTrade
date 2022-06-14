package main.symulacja.strategieRobotników.strategieProdukcji;

import com.squareup.moshi.Json;
import main.symulacja.utils.PodsumowanieDnia;

import static main.Main.TypyProduktów;

public class Średniak extends StrategiaProdukcji {
    @Json(name = "historia_sredniej_produkcji")
    private final int ileDni;

    public Średniak (int ileDni) {
        this.ileDni = ileDni;
    }

    @Override
    public void wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(ileDni);

        double najlepszaŚrednia = 0;
        TypyProduktów najlepszyTyp = null;

        for (PodsumowanieDnia dzień: dane) {
            for (TypyProduktów typ: TypyProduktów.values()) {
                double średnia = dzień.podajŚredniąCenęTypu(typ);
                if (średnia >= najlepszaŚrednia) {
                    najlepszaŚrednia = średnia;
                    najlepszyTyp = typ;
                }
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyTyp);
        użyjProgramówIWystawProdukty(najlepszyTyp, produktywność);
    }
}
