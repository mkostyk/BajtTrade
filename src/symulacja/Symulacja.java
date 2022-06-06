package symulacja;

import java.util.Map;

public class Symulacja {
    public static int ILE_PRODUKTÓW = 5;
    public static Map <String, Integer> ID_KARIERY = Map.ofEntries(
            Map.entry("Inżynier", 0),
            Map.entry("Programista", 1),
            Map.entry("Rolnik", 2),
            Map.entry("Rzemieślnik", 3),
            Map.entry("Górnik", 4)
            );
    public static Map <String, Integer> ID_PRODUKTU = Map.ofEntries(
            Map.entry("Narzędzia", 0),
            Map.entry("Programy", 1),
            Map.entry("Jedzenie", 2),
            Map.entry("Ubrania", 3)
    );
    // TODO - enum
    public static String[] Produkty = {"Jedzenie", "Programy", "Narzędzia", "Ubrania"};
    public static double INFINITY = 1e300;
}
