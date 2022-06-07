package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.produkty.Produkt;
import symulacja.utils.PodsumowanieDnia;

import java.util.Arrays;

public class Średniak extends StrategiaProdukcji {
    private final int ileDni;

    public Średniak (Robotnik robotnik, int ileDni) {
        super(robotnik);
        this.ileDni = ileDni;
    }

    @Override
    public Produkt wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięOstatnichDni(ileDni);

        double najlepszaCena = 0;
        Symulacja.TypyProduktów najlepszyProdukt = null;

        for (PodsumowanieDnia dzień: dane) {
            for (Symulacja.TypyProduktów produkt: Symulacja.TypyProduktów.values()) {
                if (dzień.podajŚredniąCenę(produkt) > najlepszaCena) {
                    najlepszaCena = dzień.podajŚredniąCenę(produkt);
                    najlepszyProdukt = produkt;
                }
            }
        }

        int produktywność = robotnik.podajProduktywność(najlepszyProdukt);
        int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(najlepszyProdukt)];

        return new Produkt(najlepszyProdukt, produktywność, poziom);
    }
}
