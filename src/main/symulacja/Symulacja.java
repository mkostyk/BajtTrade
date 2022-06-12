package main.symulacja;

import main.Main;
import main.symulacja.agenci.robotnicy.Robotnik;
import main.symulacja.agenci.spekulanci.Spekulant;
import main.symulacja.fabryka.Fabryka;
import main.symulacja.giełda.Giełda;
import main.symulacja.produkty.Produkt;

import java.util.*;

import static main.symulacja.Symulacja.TypyProduktów.*;
import static main.symulacja.Symulacja.Zawody.*;

public class Symulacja {
    public static int DZIENNE_ZUŻYCIE_UBRAŃ = 100;
    public static int ILE_PRODUKTÓW = 5;
    public static int ILE_ZAWODÓW = 5;
    public enum TypyProduktów {NARZEDZIA, PROGRAMY, JEDZENIE, UBRANIA, DIAMENTY}
    public enum Zawody {INZYNIER, PROGRAMISTA, ROLNIK, RZEMIESLNIK, GORNIK}

    public static Map <Zawody, Integer> ID_KARIERY = Map.ofEntries(
            Map.entry(INZYNIER, 0),
            Map.entry(PROGRAMISTA, 1),
            Map.entry(ROLNIK, 2),
            Map.entry(RZEMIESLNIK, 3),
            Map.entry(GORNIK, 4)
            );

    public static Map <TypyProduktów, Integer> ID_PRODUKTU = Map.ofEntries(
            Map.entry(NARZEDZIA, 0),
            Map.entry(PROGRAMY, 1),
            Map.entry(JEDZENIE, 2),
            Map.entry(UBRANIA, 3),
            Map.entry(DIAMENTY, 4)
    );
    public static double INFINITY = 1e300;
    public static int MAX_POZIOM = Integer.MAX_VALUE;
    public static Random RNG = new Random();

    private final int długość;
    private final ArrayList<Robotnik> robotnicy;
    private final ArrayList<Spekulant> spekulanci;
    private final Giełda giełda;


    public Symulacja (int długosc, String giełda, int kara_za_brak_ubrań, Map<String, Double> ceny,
                      List<Robotnik> robotnicy, List<Spekulant> spekulanci) {
        // TODO
        this.długość = długosc;
        this.robotnicy = (ArrayList<Robotnik>) robotnicy;
        this.spekulanci = (ArrayList<Spekulant>) spekulanci;
        // TODO
        TreeMap<Produkt, Double> cenyZerowe = Main.stwórzMapęCen(ceny);
        //System.out.println(giełda);
        this.giełda = Fabryka.stwórzGiełdę(giełda, cenyZerowe, kara_za_brak_ubrań);

        for (Robotnik robotnik: this.robotnicy) {
            robotnik.ustawGiełdę(this.giełda);
        }

        for (Spekulant spekulant: this.spekulanci) {
            spekulant.ustawGiełdę(this.giełda);
        }
    }

    private void wypiszDzień() {
        System.out.println(this);
    }

    private void dzień() {
        // Robotnicy (pkt 1)
        // Wywołujemy przeżyjDzień() oraz usuwamy robotników jeśli nie udało im się przeżyć
        robotnicy.removeIf(robotnik -> !robotnik.przeżyjDzień());

        // Spekulanci (pkt 2)
        for (Spekulant spekulant: spekulanci) {
            spekulant.wystawOferty();
        }

        //giełda.wypisz();

        // Giełda (pkt 3 i 4)
        giełda.dopasujOferty();

        //giełda.wypisz();

        giełda.skupOferty();
        giełda.podsumujDzień();


        // Zużywanie przedmiotów (pkt 5)
        for (Robotnik robotnik: robotnicy) {
            robotnik.zużyjPrzedmioty();
        }

        wypiszDzień();
    }

    public void symuluj() {
        for (int i = 0; i < długość; i++) {
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
