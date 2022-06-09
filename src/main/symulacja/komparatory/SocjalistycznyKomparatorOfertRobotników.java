package main.symulacja.komparatory;

import main.symulacja.giełda.oferty.OfertaRobotnika;

import java.util.Comparator;

public class SocjalistycznyKomparatorOfertRobotników implements Comparator<OfertaRobotnika> {
    public int compare(OfertaRobotnika pierwszaOferta, OfertaRobotnika drugaOferta) {
        // Sortowanie po diamentach - rosnąco
        if (pierwszaOferta.podajTwórcę().ileDiamentów() > drugaOferta.podajTwórcę().ileDiamentów()) {
            return 1;
        } else if (pierwszaOferta.podajTwórcę().ileDiamentów() < drugaOferta.podajTwórcę().ileDiamentów()) {
            return -1;
        }

        // Sortowanie po typie produktu w ofercie - rosnąco
        if (pierwszaOferta.typID() > drugaOferta.typID()) {
            return 1;
        } else if (pierwszaOferta.typID() < drugaOferta.typID()){
            return -1;
        }

        // Sortowanie po poziomie produktu w ofercie - malejąco
        return Integer.compare(pierwszaOferta.podajPoziom(), drugaOferta.podajPoziom());
    }
}
