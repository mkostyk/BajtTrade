package main.symulacja.utils;

import com.squareup.moshi.Json;
import main.symulacja.giełda.Giełda;

import java.util.Map;
import java.util.TreeMap;

import static main.Main.*;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class BazoweInformacjeOutput {
    @Json(name = "dzien")
    @SuppressWarnings("UnusedDeclaration")
    private final int dzień;
    @Json(name = "ceny_srednie")
    private final Map<String, Double> średnieCeny;
    @Json(name = "ceny_max")
    private final Map<String, Double> najwyższeCeny;
    @Json(name = "ceny_min")
    private final Map<String, Double> najniższeCeny;

    public BazoweInformacjeOutput(Giełda giełda) {
        dzień = giełda.podajDzień() - 1;
        średnieCeny = new TreeMap<>();
        najwyższeCeny = new TreeMap<>();
        najniższeCeny = new TreeMap<>();
        PodsumowanieDnia dane = giełda.podajHistorięOstatnichDni(1)[0];

        for (TypyProduktów typ: TypyProduktów.values()) {
            if (PRODUKTY_NA_GIEŁDZIE.contains(typ)) {
                średnieCeny.put(enumToString(typ), dane.podajŚredniąCenęTypu(typ));
                najwyższeCeny.put(enumToString(typ), dane.podajNajwyższąCenęTypu(typ));
                najniższeCeny.put(enumToString(typ), dane.podajNajniższąCenęTypu(typ));
            }
        }
    }
}
