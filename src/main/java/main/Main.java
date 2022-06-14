package main;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;
import main.symulacja.Symulacja;
import main.symulacja.adaptery.*;
import main.symulacja.agenci.Agent;
import main.symulacja.strategieRobotników.strategieKupna.*;
import main.symulacja.strategieRobotników.strategiePracy.*;
import main.symulacja.strategieRobotników.strategieProdukcji.*;

import main.symulacja.komparatory.KomparatorProduktów;
import main.symulacja.produkty.Produkt;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static main.Main.TypyProduktów.*;

public class Main {
    public static final int DZIENNE_ZUŻYCIE_UBRAŃ = 100;
    public static final int ILE_PRODUKTÓW = 5;
    public static final int ILE_ZAWODÓW = 5;
    public enum TypyProduktów {JEDZENIE, UBRANIA, NARZEDZIA, DIAMENTY, PROGRAMY}
    public enum Zawody {ROLNIK, RZEMIESLNIK, INZYNIER, GORNIK, PROGRAMISTA}

    // Produkty posiadające rozróżnienie na poziomy.
    public static final Set<TypyProduktów> PRODUKTY_Z_POZIOMEM = Set.of(
            UBRANIA, NARZEDZIA, PROGRAMY
    );

    // Produkty, przy których produkcji używamy programów komputerowych.
    public static final Set<TypyProduktów> PRODUKTY_Z_PROGRAMAMI = Set.of(
            UBRANIA, NARZEDZIA
    );

    // Produkty możliwe do wystawienia na giełdzie.
    public static final Set<TypyProduktów> PRODUKTY_NA_GIEŁDZIE = Set.of(
            JEDZENIE, UBRANIA, NARZEDZIA, PROGRAMY
    );

    public static final double INFINITY = 1e300;
    public static final int MAX_POZIOM = Integer.MAX_VALUE;
    public static final Random RNG = new Random();
    private static final Moshi moshi = new Moshi.Builder()
            .add(PolymorphicJsonAdapterFactory.of(StrategiaPracy.class, "typ")
                    .withSubtype(Okresowy.class, "okresowy")
                    .withSubtype(Oszczędny.class, "oszczedny")
                    .withSubtype(Pracuś.class, "pracus")
                    .withSubtype(Rozkładowy.class, "rozkladowy")
                    .withSubtype(Student.class, "student")
            )

            .add(PolymorphicJsonAdapterFactory.of(StrategiaKupna.class, "typ")
                    .withSubtype(Czyścioszek.class, "czyscioszek")
                    .withSubtype(Gadżeciarz.class, "gadzeciarz")
                    .withSubtype(Technofob.class, "technofob")
                    .withSubtype(Zmechanizowany.class, "zmechanizowany")
            )

            .add(PolymorphicJsonAdapterFactory.of(StrategiaProdukcji.class, "typ")
                    .withSubtype(Chciwy.class, "chciwy")
                    .withSubtype(Krótkowzroczny.class, "krotkowzroczny")
                    .withSubtype(Losowy.class, "losowy")
                    .withSubtype(Perspektywiczny.class, "perspektywiczny")
                    .withSubtype(Średniak.class, "sredniak")
            )

            .add(new SymulacjaAdapter())
            .add(new RobotnikAdapter())
            .add(new SpekulantAdapter())
            .build();

    public static final JsonAdapter<Symulacja> jsonAdapter = moshi.adapter(Symulacja.class);

    public static String enumToString(Object enumerator) {
        return enumerator.toString().toLowerCase(Locale.ROOT);
    }

    private static Double[] stwórzTablicęProduktów(Map<Produkt, Double> mapaCen) {
        if (mapaCen.size() == 0) {
            return new Double[0];
        } else {
            Produkt pierwszy = mapaCen.keySet().stream().findFirst().get();
            Double[] wynik = new Double[pierwszy.podajPoziom()];
            Arrays.fill(wynik, 0.0);

            for (Produkt produkt: mapaCen.keySet()) {
                if (mapaCen.get(produkt) != null) {
                    wynik[produkt.podajPoziom() - 1] += mapaCen.get(produkt);
                }
            }

            return wynik;
        }
    }

    public static Map<String, Double[]> stwórzMapęTablicProduktów(Agent właściciel) {
        Map<String, Double[]> wynik = new TreeMap<>();
        for (TypyProduktów typ: TypyProduktów.values()) {
            wynik.put(enumToString(typ), stwórzTablicęProduktów(właściciel.podajProdukty(typ)));
        }

        return wynik;
    }

    public static Map<Produkt, Double> stwórzMapęCen(Map<String, Double> mapa) {
        Map<Produkt, Double> wynik = new TreeMap<>(new KomparatorProduktów());

        for (TypyProduktów typ: TypyProduktów.values()) {
            wynik.put(new Produkt(typ, 1), mapa.get(enumToString(typ)));
        }

        return wynik;
    }

    public static List<Map<Produkt, Double>> stwórzListęMapProduktów(Map<String, Double> mapa) {
        List<Map<Produkt, Double>> wynik = new ArrayList<>();
        for (TypyProduktów typ: TypyProduktów.values()) {
            Map<Produkt, Double> mapaProduktu = new TreeMap<>(new KomparatorProduktów());
            mapaProduktu.put(new Produkt(typ, 1), mapa.get(enumToString(typ)));
            wynik.add(mapaProduktu);
        }

        return wynik;
    }

    private static String plikWejściowy = "";
    private static String plikWyjściowy = "";

    private static String wczytajPlik() {
        try(BufferedReader br = new BufferedReader(new FileReader(plikWejściowy))) {
            StringBuilder sb = new StringBuilder();
            String linia = br.readLine();

            while (linia != null) {
                sb.append(linia);
                sb.append(System.lineSeparator());
                linia = br.readLine();
            }

            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void wypiszDoPliku(ArrayList<String> listaJson) {
       try(PrintWriter pw = new PrintWriter(plikWyjściowy, StandardCharsets.UTF_8)) {
           pw.println(listaJson);
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args) throws IOException {
        plikWejściowy = args[0];
        plikWyjściowy = args[1];

        String json = wczytajPlik();
        assert json != null;

        Symulacja symulacja = jsonAdapter.fromJson(json);
        assert symulacja != null;
        symulacja.symuluj();
    }
}
