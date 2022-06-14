package main.symulacja.giełda.oferty;

import main.Main;
import main.symulacja.komparatory.KomparatorOfertKupnaSpekulantów;
import main.symulacja.komparatory.KomparatorOfertSprzedażySpekulantów;

import java.util.*;

/*
    Lista, ale z opcją dokonania binary search w ofertach spekulantów, kiedy mamy jako klucz ofertę robotnika.
 */
public class ZbiórOfertSpekulanta {
    private final List<OfertaSpekulanta> listaOfert = new ArrayList<>();

    public int znajdźNajlepsząOfertęKupna(OfertaRobotnika oferta) {
        // Cena INFINITY gwarantuje, że znajdziemy najwyższą cenę, za którą możemy sprzedać produkty.
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, oferta.podajIle(), Main.INFINITY, oferta.podajTwórcę());
        return Math.abs(Collections.binarySearch(listaOfert, tmp, new KomparatorOfertKupnaSpekulantów()) + 1);
    }

    public int znajdźNajlepsząOfertęSprzedaży(OfertaRobotnika oferta) {
        // Analogicznie cena 0 gwarantuje znalezienie najniższej ceny.
        OfertaSpekulanta tmp = new OfertaSpekulanta(oferta.produkt, oferta.podajIle(), 0, oferta.podajTwórcę());
        return Math.abs(Collections.binarySearch(listaOfert, tmp, new KomparatorOfertSprzedażySpekulantów()) + 1);
    }

    public OfertaSpekulanta podajOfertę(int pozycja) {
        return listaOfert.get(pozycja);
    }

    public void dodajOfertę(OfertaSpekulanta oferta) {
        listaOfert.add(oferta);
    }

    public void posortujOferty(Comparator<OfertaSpekulanta> comparator) {
        listaOfert.sort(comparator);
    }

    public int size() {
        return listaOfert.size();
    }

    public List<OfertaSpekulanta> podajWszystkieOferty() {
        return listaOfert;
    }
}
