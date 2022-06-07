package main.symulacja.giełda.oferty;

import main.symulacja.komparatory.KomparatorOfertKupnaSpekulantów;
import main.symulacja.komparatory.KomparatorOfertSprzedażySpekulantów;

import java.util.*;

import static main.symulacja.Symulacja.INFINITY;

public class ZbiórOfertSpekulanta {
    private ArrayList<OfertaSpekulanta> listaOfert = new ArrayList<OfertaSpekulanta>();

    public int znajdźNajlepsząOfertęKupna(OfertaRobotnika oferta) {
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, INFINITY, oferta.podajTwórcę());
        return Collections.binarySearch(listaOfert, tmp, new KomparatorOfertKupnaSpekulantów());
    }

    public int znajdźNajlepsząOfertęSprzedaży(OfertaRobotnika oferta) {
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, 0, oferta.podajTwórcę());
        return Collections.binarySearch(listaOfert, tmp, new KomparatorOfertSprzedażySpekulantów());
    }

    public OfertaSpekulanta podajOfertę(int pozycja) {
        return listaOfert.get(pozycja);
    }

    public void dodajOfertę(OfertaSpekulanta oferta) {
        listaOfert.add(oferta);
    }

    public void posortujOferty() {
        listaOfert.sort(new KomparatorOfertKupnaSpekulantów());
    }
}
