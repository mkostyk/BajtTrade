package main.symulacja.strategieRobotników.strategieProdukcji;

import com.squareup.moshi.Json;
import main.symulacja.utils.PodsumowanieDnia;
import main.symulacja.produkty.Produkt;

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

        double najlepszaCena = 0;
        Produkt najlepszyProdukt = null;

        for (PodsumowanieDnia dzień: dane) {
            for (TypyProduktów typ: TypyProduktów.values()) {
                int poziom = robotnik.podajPoziomyŚcieżek()[typ.ordinal()];
                Produkt produkt = new Produkt(typ, poziom);

                if (dzień.podajŚredniąCenę(produkt) >= najlepszaCena) {
                    najlepszaCena = dzień.podajŚredniąCenę(produkt);
                    najlepszyProdukt = produkt;
                }
            }
        }

        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
