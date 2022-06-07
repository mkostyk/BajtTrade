package main.symulacja.komparatory;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import java.util.Comparator;

public class KapitalistycznyKomparatorOfertRobotników implements Comparator<OfertaRobotnika> {
    public int compare(OfertaRobotnika pierwszaOferta, OfertaRobotnika drugaOferta) {
        // Sortowanie po typie produktu w ofercie
        if (pierwszaOferta.typID() > drugaOferta.typID()) {
            return 1;
        } else if (pierwszaOferta.typID() < drugaOferta.typID()){
            return -1;
        }

        // Sortowanie po diamentach
        if (pierwszaOferta.podajTwórcę().ileDiamentów() > drugaOferta.podajTwórcę().ileDiamentów()) {
            return 1;
        } else if (pierwszaOferta.podajTwórcę().ileDiamentów() < drugaOferta.podajTwórcę().ileDiamentów()) {
            return -1;
        }

        // Sortowanie po poziomie produktu w ofercie
        return Integer.compare(pierwszaOferta.podajPoziom(), drugaOferta.podajPoziom());
    }
}
