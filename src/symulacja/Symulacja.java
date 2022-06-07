package symulacja;

import symulacja.agenci.robotnicy.Robotnik;
import symulacja.agenci.spekulanci.Spekulant;
import symulacja.giełda.Giełda;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static symulacja.Symulacja.TypyProduktów.*;
import static symulacja.Symulacja.Zawody.*;

public class Symulacja {
    // TODO - konstruktory klas abstrakcyjnych protected
    // TODO - produkty czy enum produktów? (oferty na pewno jako produkty - do poprawki)
    // TODO - konstruktory in general
    public static int ILE_PRODUKTÓW = 5;
    public static int ILE_ZAWODÓW = 5;
    public enum TypyProduktów {NARZĘDZIA, PROGRAMY, JEDZENIE, UBRANIA, DIAMENTY}
    public enum Zawody {INŻYNIER, PROGRAMISTA, ROLNIK, RZEMIEŚLNIK, GÓRNIK}

    public static Map <Zawody, Integer> ID_KARIERY = Map.ofEntries(
            Map.entry(INŻYNIER, 0),
            Map.entry(PROGRAMISTA, 1),
            Map.entry(ROLNIK, 2),
            Map.entry(RZEMIEŚLNIK, 3),
            Map.entry(GÓRNIK, 4)
            );

    public static Map <TypyProduktów, Integer> ID_PRODUKTU = Map.ofEntries(
            Map.entry(NARZĘDZIA, 0),
            Map.entry(PROGRAMY, 1),
            Map.entry(JEDZENIE, 2),
            Map.entry(UBRANIA, 3)
    );
    public static double INFINITY = 1e300;
    public static Random RNG = new Random();

    private ArrayList<Robotnik> robotnicy;
    private ArrayList<Spekulant> spekulanci;
    private Giełda giełda;

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

        for (Robotnik robotnik: robotnicy) {
            robotnik.zużyjPrzedmioty();
        }
    }

    public static void main(String[] args) {

    }
}
