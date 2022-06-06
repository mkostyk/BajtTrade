package symulacja;

import java.util.Map;
import java.util.Random;

import static symulacja.Symulacja.TypyProduktów.*;
import static symulacja.Symulacja.Zawody.*;

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
            Map.entry(UBRANIA, 3)
    );
    public static double INFINITY = 1e300;
    public static Random RNG = new Random();

    public static void main(String[] args) {

    }
}
