package main.symulacja;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.Giełda;
import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import java.util.*;

import static main.symulacja.Symulacja.TypyProduktów.*;
import static main.symulacja.Symulacja.Zawody.*;

public class Symulacja {
    public static int DZIENNE_ZUŻYCIE_UBRAŃ = 100;
    public static int ILE_PRODUKTÓW = 5;
    public static int ILE_ZAWODÓW = 5;
    public enum TypyProduktów {NARZĘDZIA, PROGRAMY, JEDZENIE, UBRANIA, DIAMENTY}
    public enum Zawody {INZYNIER, PROGRAMISTA, ROLNIK, RZEMIESLNIK, GORNIK}

    public static Map <Zawody, Integer> ID_KARIERY = Map.ofEntries(
            Map.entry(INZYNIER, 0),
            Map.entry(PROGRAMISTA, 1),
            Map.entry(ROLNIK, 2),
            Map.entry(RZEMIESLNIK, 3),
            Map.entry(GORNIK, 4)
            );

    public static Map <TypyProduktów, Integer> ID_PRODUKTU = Map.ofEntries(
            Map.entry(NARZĘDZIA, 0),
            Map.entry(PROGRAMY, 1),
            Map.entry(JEDZENIE, 2),
            Map.entry(UBRANIA, 3),
            Map.entry(DIAMENTY, 4)
    );
    public static double INFINITY = 1e300;
    public static int MAX_POZIOM = Integer.MAX_VALUE;
    public static Random RNG = new Random();

    private int długość;
    private ArrayList<Robotnik> robotnicy;
    private ArrayList<Spekulant> spekulanci;
    private Giełda giełda;



    public Symulacja (int długosc, String giełda, int kara_za_brak_ubrań, Map<String, Double> ceny,
                      List<Robotnik> robotnicy, List<Spekulant> spekulanci) {
        // TODO
        this.długość = długosc;
        this.robotnicy = (ArrayList<Robotnik>) robotnicy;
        this.spekulanci = (ArrayList<Spekulant>) spekulanci;
        // TODO
        TreeMap<Produkt, Double> cenyZerowe = new TreeMap<Produkt, Double>(new KomparatorProduktów());
        //System.out.println(giełda);
        this.giełda = Fabryka.stwórzGiełdę(giełda, cenyZerowe, kara_za_brak_ubrań);
    }

    private void wypiszDzień() {
        // TODO
    }

    private void dzień() {
        // Robotnicy (pkt 1)
        for (Robotnik robotnik: robotnicy) {
            if (!robotnik.przeżyjDzień()) {
                robotnicy.remove(robotnik);
            }
        }

        // Spekulanci (pkt 2)
        for (Spekulant spekulant: spekulanci) {
            spekulant.wystawOferty();
        }

        // Giełda (pkt 3 i 4)
        giełda.dopasujOferty();
        giełda.skupOferty();
        giełda.podsumujDzień();

        // Zużywanie przedmiotów (pkt 5)
        for (Robotnik robotnik: robotnicy) {
            robotnik.zużyjPrzedmioty();
        }

        wypiszDzień();
    }

    public void symuluj(int dni) {
        for (int i = 0; i < dni; i++) {
            dzień();
        }
    }

    @Override
    public String toString() {
        return "Symulacja{" +
                "długość=" + długość +
                ", robotnicy=" + robotnicy +
                ", spekulanci=" + spekulanci +
                ", giełda=" + giełda +
                '}';
    }
}
