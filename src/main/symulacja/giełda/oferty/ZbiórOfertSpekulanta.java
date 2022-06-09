package main.symulacja.giełda.oferty;

import main.symulacja.komparatory.KomparatorOfertKupnaSpekulantów;
import main.symulacja.komparatory.KomparatorOfertSprzedażySpekulantów;

import java.util.*;

import static main.symulacja.Symulacja.INFINITY;

public class ZbiórOfertSpekulanta {
    private ArrayList<OfertaSpekulanta> listaOfert = new ArrayList<>();

    public int znajdźNajlepsząOfertęKupna(OfertaRobotnika oferta) {
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, oferta.podajIle(), INFINITY, oferta.podajTwórcę());
        return Math.abs(Collections.binarySearch(listaOfert, tmp, new KomparatorOfertKupnaSpekulantów()) + 2); // TODO nie jestem pewien tego +2
    }

    public int znajdźNajlepsząOfertęSprzedaży(OfertaRobotnika oferta) {
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, oferta.podajIle(), 0, oferta.podajTwórcę());
        return Math.abs(Collections.binarySearch(listaOfert, tmp, new KomparatorOfertSprzedażySpekulantów()) + 2);
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

    public int size() {
        return listaOfert.size();
    }

    public ArrayList<OfertaSpekulanta> podajWszystkieOferty() {
        return listaOfert;
    }
}
