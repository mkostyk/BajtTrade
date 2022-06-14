package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KapitalistycznyKomparatorOfertRobotników;
import main.symulacja.komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Zrównoważona extends Giełda {
    public Zrównoważona(Map<Produkt, Double> cenyZerowe, int karaZaUbrania) {
        super(cenyZerowe, karaZaUbrania);
    }

    @Override
    protected void posortujOferty() {
        if (this.podajDzień() % 2 == 1) {
            ofertyKupnaRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
            ofertySprzedażyRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
        } else {
            ofertyKupnaRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
            ofertySprzedażyRobotników.sort(new KapitalistycznyKomparatorOfertRobotników());
        }
    }

    @Override
    public String toString() {
        return "zrownowazona";
    }
}
