package symulacja.agenci.robotnicy.strategieProdukcji;

import symulacja.Symulacja;
import symulacja.agenci.robotnicy.Robotnik;
import symulacja.utils.PodsumowanieDnia;

import java.util.Arrays;

public class Średniak extends StrategiaProdukcji {
    private final int ileDni;

    public Średniak (Robotnik robotnik, int ileDni) {
        super(robotnik);
        this.ileDni = ileDni;
    }

    @Override
    public String wyprodukuj() {
        PodsumowanieDnia[] dane = robotnik.podajGiełdę().podajHistorięCen();
        assert (dane != null);

        int dzieńKońcowy = dane.length - 1;
        int dzieńStartowy = Math.max(dane.length - ileDni, 0);

        PodsumowanieDnia[] potrzebneDane = Arrays.copyOfRange(dane, dzieńStartowy, dzieńKońcowy);
        double najlepszaCena = 0;
        String najlepszyProdukt = "";

        for (PodsumowanieDnia dzień: potrzebneDane) {
            for (String produkt: Symulacja.Produkty) {
                if (dzień.podajŚredniąCenę(produkt) > najlepszaCena) {
                    najlepszaCena = dzień.podajŚredniąCenę(produkt);
                    najlepszyProdukt = produkt;
                }
            }
        }

        return najlepszyProdukt;
    }
}
