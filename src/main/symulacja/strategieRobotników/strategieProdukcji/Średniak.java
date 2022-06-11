package main.symulacja.strategieRobotników.strategieProdukcji;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.produkty.Produkt;
import main.symulacja.utils.PodsumowanieDnia;

public class Średniak extends StrategiaProdukcji {
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
            for (Symulacja.TypyProduktów typ: Symulacja.TypyProduktów.values()) {
                int poziom = robotnik.podajPoziomyŚcieżek()[Symulacja.ID_PRODUKTU.get(typ)];
                if (dzień.podajŚredniąCenę(typ, poziom) > najlepszaCena) {
                    najlepszaCena = dzień.podajŚredniąCenę(typ, poziom);
                    najlepszyProdukt = new Produkt(typ, poziom);
                }
            }
        }

        assert najlepszyProdukt != null;
        int produktywność = robotnik.podajProduktywność(najlepszyProdukt.podajTyp());

        użyjProgramówIWystawProdukty(najlepszyProdukt, produktywność);
    }
}
