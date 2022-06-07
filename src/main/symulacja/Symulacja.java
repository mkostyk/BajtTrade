package main.symulacja;

import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.giełda.Giełda;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import static main.symulacja.Symulacja.TypyProduktów.*;
import static main.symulacja.Symulacja.Zawody.*;

public class Symulacja {
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
            Map.entry(UBRANIA, 3),
            Map.entry(DIAMENTY, 4)
    );
    public static double INFINITY = 1e300;
    public static int MAX_POZIOM = Integer.MAX_VALUE;
    public static Random RNG = new Random();

    private ArrayList<Robotnik> robotnicy;
    private ArrayList<Spekulant> spekulanci;
    private Giełda giełda;

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
}
