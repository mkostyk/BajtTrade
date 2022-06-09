package main;

import main.symulacja.Symulacja;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.Giełda;
import main.symulacja.giełda.oferty.OfertaRobotnika;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;
import main.symulacja.strategieRobotników.strategieKariery.StrategiaKariery;
import main.symulacja.strategieRobotników.strategieKupna.StrategiaKupna;
import main.symulacja.strategieRobotników.strategiePracy.StrategiaPracy;
import main.symulacja.strategieRobotników.strategieProdukcji.StrategiaProdukcji;

import java.util.TreeMap;

import static main.symulacja.Symulacja.TypyProduktów.*;
import static main.symulacja.Symulacja.Zawody.*;

public class Main {
    // TODO - konstruktory klas abstrakcyjnych protected
    // TODO - produkty czy enum produktów?
    // TODO - konstruktory in general
    // TODO - JSON
    // TODO - zgadzające się nazwy parametrów
    // TODO - kolejność
    public static void main(String[] args) {
        TreeMap<Produkt, Double> ceny = new TreeMap<>(new KomparatorProduktów());
        ceny.put(new Produkt(NARZĘDZIA, 1), 2.0);
        ceny.put(new Produkt(PROGRAMY, 1), 1.3);
        ceny.put(new Produkt(JEDZENIE, 1), 0.5);
        ceny.put(new Produkt(UBRANIA, 1), 5.3);

        Giełda giełda = Fabryka.stwórzGiełdę("kapitalistyczna", ceny);
        giełda.ustawDzień(1);

        StrategiaKariery s1 = Fabryka.stwórzStrategięKariery("konserwatysta");
        StrategiaKupna s2 = Fabryka.stwórzStrategięKupna("czyścioszek");
        StrategiaPracy s3 = Fabryka.stwórzStrategięPracy("oszczędny", 1500);
        StrategiaProdukcji s4 = Fabryka.stwórzStrategięProdukcji("losowy");

        Robotnik robol1 = new Robotnik(ROLNIK, s1, s2, s3, s4, 1, giełda);

        OfertaRobotnika ofertaJedzenie = new OfertaRobotnika(JEDZENIE, 100, robol1);
        OfertaRobotnika ofertaUbrania = new OfertaRobotnika(UBRANIA, 100, robol1);

        //giełda.dodajOfertęKupnaRobotnika(ofertaJedzenie);
        //giełda.dodajOfertęKupnaRobotnika(ofertaUbrania);
        //giełda.wypisz();

        Robotnik robol2 = new Robotnik(ROLNIK, s1, s2, s3, s4, 2, giełda);
        robol2.dodajDiamenty(100);
        robol2.przeżyjDzień();

        giełda.posortujOferty();
        //giełda.wypisz();

        Spekulant spekulant = Fabryka.stwórzSpekulanta("średni", 11, giełda);

        spekulant.dodajProdukty(50, new Produkt(JEDZENIE, 1));
        spekulant.dodajProdukty(200, new Produkt(UBRANIA, 1));
        spekulant.dodajProdukty(200, new Produkt(NARZĘDZIA, 1));
        spekulant.dodajProdukty(200, new Produkt(PROGRAMY, 1));

        spekulant.dodajDiamenty(1000);
        spekulant.wystawOferty();

        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());

        giełda.dopasujOferty();
        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());

        giełda.skupOferty();
        giełda.wypisz();
        System.out.println();
        System.out.println(robol2.ileDiamentów() + " " + spekulant.ileDiamentów());
    }
}
