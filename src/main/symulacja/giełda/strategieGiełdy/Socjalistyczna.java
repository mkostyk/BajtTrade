package main.symulacja.giełda.strategieGiełdy;

import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.SocjalistycznyKomparatorOfertRobotników;
import main.symulacja.produkty.Produkt;

import java.util.Map;

public class Socjalistyczna extends Giełda {
    public Socjalistyczna(Map<Produkt, Double> cenyZerowe, int karaZaUbrania) {
        super(cenyZerowe, karaZaUbrania);
    }

    @Override
    public void posortujOferty() {
        ofertyKupnaRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
        ofertySprzedażyRobotników.sort(new SocjalistycznyKomparatorOfertRobotników());
    }

    @Override
    public String toString() {
        return "socjalistyczna";
    }
}
